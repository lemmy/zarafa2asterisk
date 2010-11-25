/**
 * Folders.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.kuppe.zarafa2asterisk.gen;

public class Folders  implements java.io.Serializable {
    private java.lang.String foldername;

    private org.kuppe.zarafa2asterisk.gen.Contact[] contacts;

    public Folders() {
    }

    public Folders(
           java.lang.String foldername,
           org.kuppe.zarafa2asterisk.gen.Contact[] contacts) {
           this.foldername = foldername;
           this.contacts = contacts;
    }


    /**
     * Gets the foldername value for this Folders.
     * 
     * @return foldername
     */
    public java.lang.String getFoldername() {
        return foldername;
    }


    /**
     * Sets the foldername value for this Folders.
     * 
     * @param foldername
     */
    public void setFoldername(java.lang.String foldername) {
        this.foldername = foldername;
    }


    /**
     * Gets the contacts value for this Folders.
     * 
     * @return contacts
     */
    public org.kuppe.zarafa2asterisk.gen.Contact[] getContacts() {
        return contacts;
    }


    /**
     * Sets the contacts value for this Folders.
     * 
     * @param contacts
     */
    public void setContacts(org.kuppe.zarafa2asterisk.gen.Contact[] contacts) {
        this.contacts = contacts;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Folders)) return false;
        Folders other = (Folders) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.foldername==null && other.getFoldername()==null) || 
             (this.foldername!=null &&
              this.foldername.equals(other.getFoldername()))) &&
            ((this.contacts==null && other.getContacts()==null) || 
             (this.contacts!=null &&
              java.util.Arrays.equals(this.contacts, other.getContacts())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getFoldername() != null) {
            _hashCode += getFoldername().hashCode();
        }
        if (getContacts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContacts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContacts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Folders.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/mapicontactsldapsoap", "folders"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foldername");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foldername"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contacts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contacts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/mapicontactsldapsoap", "contact"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
