/*******************************************************************************
 * Copyright (c) 2010 Markus Alexander Kuppe.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Markus Alexander Kuppe (ecf-dev_eclipse.org <at> lemmster <dot> de) - initial API and implementation
 ******************************************************************************/
package org.kuppe.zarafa2asterisk;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;
import org.kuppe.zarafa2asterisk.gen.Contact;
import org.kuppe.zarafa2asterisk.gen.Folders;
import org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapBindingStub;
import org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapLocator;
import org.kuppe.zarafa2asterisk.gen.Users;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class CallerIdAgiScript extends BaseAgiScript {

	private static final String DEFAULT_COUNTRY = System.getProperty(
			"org.kuppe.zarafa2asterisk.defaultCountr", "DE");
	private static PhoneNumberUtil PNU = PhoneNumberUtil.getInstance();

	private static List<Contact> CONTACTS;

	public synchronized static List<Contact> getContacts() throws AgiException {
		try {
			if (CONTACTS == null) {
				CONTACTS = new ArrayList<Contact>();

				// read the soap url from the properties
				String url = System
						.getProperty("org.kuppe.zarafa2asterisk.soapUrl", "http://localhost/zarafa2ldap/mapiContacts.php");

				// open a connection to the mapi webservice
				final MapicontactsldapsoapLocator serviceLocator = new MapicontactsldapsoapLocator();
				serviceLocator.setmapicontactsldapsoapPortEndpointAddress(url);
				final MapicontactsldapsoapBindingStub mapi = (MapicontactsldapsoapBindingStub) serviceLocator
						.getmapicontactsldapsoapPort();

				// read all private contacts of all users
				Users[] users = mapi.getPrivateContactFolders();
				for (int i = 0; i < users.length; i++) {
					Users user = users[i];
					Contact[] contact = user.getContacts();
					List<Contact> list = Arrays.asList(contact);
					CONTACTS.addAll(list);
				}

				// read all public contacts
				Folders[] folders = mapi.getPublicContactFolders();
				// there might be no public folders defined
				if (folders != null) {
					for (int i = 0; i < folders.length; i++) {
						Folders folder = folders[i];
						Contact[] contact = folder.getContacts();
						List<Contact> list = Arrays.asList(contact);
						CONTACTS.addAll(list);
					}
				}
			}
			return CONTACTS;

		} catch (ServiceException se) {
			throw new AgiException(se.getMessage(), se);
		} catch (RemoteException e) {
			throw new AgiException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.asteriskjava.fastagi.AgiScript#service(org.asteriskjava.fastagi.
	 * AgiRequest, org.asteriskjava.fastagi.AgiChannel)
	 */
	public void service(final AgiRequest request, final AgiChannel channel)
			throws AgiException {

		// get the caller Id Number we are trying to match
		final PhoneNumber incomingPhoneNumber = convertToPhoneNumber(request
				.getCallerIdNumber());

		// get all contacts
		final List<Contact> contacts = getContacts();

		for (final Iterator<Contact> iterator = contacts.iterator(); iterator
				.hasNext();) {
			final Contact contact = iterator.next();
			if (matchesAnyPhoneNumber(incomingPhoneNumber, contact)) {
				setCallerId(contact.getDisplayName());
				break;
			}
		}
	}

	// decides if the given callerid matches any of the number associated with
	// contact
	private boolean matchesAnyPhoneNumber(
			final PhoneNumber incomingPhoneNumber, final Contact contact)
			throws AgiException {
		final List<PhoneNumber> numbers = getNumbers(contact);
		for (Iterator<PhoneNumber> iterator = numbers.iterator(); iterator
				.hasNext();) {
			PhoneNumber phoneNumber = iterator.next();
			if (incomingPhoneNumber.exactlySameAs(phoneNumber)) {
				return true;
			}
		}
		// eventually return false
		return false;
	}

	private List<PhoneNumber> getNumbers(final Contact aContact)
			throws AgiException {
		final List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();

		// harvest all possible numbers we have for the contact
		String number = aContact.getTelephoneNumber();
		if (number != null && !"".equals(number)) {
			numbers.add(convertToPhoneNumber(number));
		}
		number = aContact.getHomePhone();
		if (number != null && !"".equals(number)) {
			numbers.add(convertToPhoneNumber(number));
		}
		number = aContact.getMobile();
		if (number != null && !"".equals(number)) {
			numbers.add(convertToPhoneNumber(number));
		}
		number = aContact.getSecretary();
		if (number != null && !"".equals(number)) {
			numbers.add(convertToPhoneNumber(number));
		}
		number = aContact.getFacsimileTelephoneNumber();
		if (number != null && !"".equals(number)) {
			numbers.add(convertToPhoneNumber(number));
		}

		return numbers;
	}

	private PhoneNumber convertToPhoneNumber(final String aNumber)
			throws AgiException {
		try {
			return PNU.parse(aNumber, DEFAULT_COUNTRY);
		} catch (NumberParseException e) {
			throw new AgiException(e.getMessage(), e);
		}
	}
}
