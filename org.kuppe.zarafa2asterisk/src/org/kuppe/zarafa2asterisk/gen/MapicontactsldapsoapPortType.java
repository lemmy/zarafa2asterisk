/**
 * MapicontactsldapsoapPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.kuppe.zarafa2asterisk.gen;

public interface MapicontactsldapsoapPortType extends java.rmi.Remote {

    /**
     * Returns all users and their private contacts
     */
    public org.kuppe.zarafa2asterisk.gen.Users[] getPrivateContactFolders() throws java.rmi.RemoteException;

    /**
     * Returns all public folders and their contacts
     */
    public org.kuppe.zarafa2asterisk.gen.Folders[] getPublicContactFolders() throws java.rmi.RemoteException;
}
