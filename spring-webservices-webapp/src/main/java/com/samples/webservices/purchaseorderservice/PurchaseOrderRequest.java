//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.21 at 11:49:26 PM EET 
//


package com.samples.webservices.purchaseorderservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.samples.webservices.purchaseorder.PurchaseOrderId;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PurchaseOrderId" type="{http://purchaseorder.webservices.samples.com}PurchaseOrderId"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "purchaseOrderId"
})
@XmlRootElement(name = "PurchaseOrderRequest")
public class PurchaseOrderRequest {

    @XmlElement(name = "PurchaseOrderId", required = true)
    protected PurchaseOrderId purchaseOrderId;

    /**
     * Gets the value of the purchaseOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseOrderId }
     *     
     */
    public PurchaseOrderId getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * Sets the value of the purchaseOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseOrderId }
     *     
     */
    public void setPurchaseOrderId(PurchaseOrderId value) {
        this.purchaseOrderId = value;
    }

}
