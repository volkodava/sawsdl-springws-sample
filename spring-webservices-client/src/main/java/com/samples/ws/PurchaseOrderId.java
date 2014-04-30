
package com.samples.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PurchaseOrderId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PurchaseOrderId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="globalProductIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="globalVendorIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurchaseOrderId", propOrder = {
    "globalProductIdentifier",
    "globalVendorIdentifier"
})
public class PurchaseOrderId {

    @XmlElement(required = true, nillable = true)
    protected String globalProductIdentifier;
    @XmlElement(required = true, nillable = true)
    protected String globalVendorIdentifier;

    /**
     * Gets the value of the globalProductIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalProductIdentifier() {
        return globalProductIdentifier;
    }

    /**
     * Sets the value of the globalProductIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalProductIdentifier(String value) {
        this.globalProductIdentifier = value;
    }

    /**
     * Gets the value of the globalVendorIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalVendorIdentifier() {
        return globalVendorIdentifier;
    }

    /**
     * Sets the value of the globalVendorIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalVendorIdentifier(String value) {
        this.globalVendorIdentifier = value;
    }

}
