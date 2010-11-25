/**
 * MapicontactsldapsoapLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.kuppe.zarafa2asterisk.gen;

public class MapicontactsldapsoapLocator extends org.apache.axis.client.Service implements org.kuppe.zarafa2asterisk.gen.Mapicontactsldapsoap {

    public MapicontactsldapsoapLocator() {
    }


    public MapicontactsldapsoapLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MapicontactsldapsoapLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for mapicontactsldapsoapPort
    private java.lang.String mapicontactsldapsoapPort_address = "http://zodiac.mkce.de/zarafa2ldap/mapiContacts.php";

    public java.lang.String getmapicontactsldapsoapPortAddress() {
        return mapicontactsldapsoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String mapicontactsldapsoapPortWSDDServiceName = "mapicontactsldapsoapPort";

    public java.lang.String getmapicontactsldapsoapPortWSDDServiceName() {
        return mapicontactsldapsoapPortWSDDServiceName;
    }

    public void setmapicontactsldapsoapPortWSDDServiceName(java.lang.String name) {
        mapicontactsldapsoapPortWSDDServiceName = name;
    }

    public org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapPortType getmapicontactsldapsoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(mapicontactsldapsoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getmapicontactsldapsoapPort(endpoint);
    }

    public org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapPortType getmapicontactsldapsoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapBindingStub _stub = new org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapBindingStub(portAddress, this);
            _stub.setPortName(getmapicontactsldapsoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setmapicontactsldapsoapPortEndpointAddress(java.lang.String address) {
        mapicontactsldapsoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapBindingStub _stub = new org.kuppe.zarafa2asterisk.gen.MapicontactsldapsoapBindingStub(new java.net.URL(mapicontactsldapsoapPort_address), this);
                _stub.setPortName(getmapicontactsldapsoapPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("mapicontactsldapsoapPort".equals(inputPortName)) {
            return getmapicontactsldapsoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost/mapicontactsldapsoap", "mapicontactsldapsoap");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost/mapicontactsldapsoap", "mapicontactsldapsoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("mapicontactsldapsoapPort".equals(portName)) {
            setmapicontactsldapsoapPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
