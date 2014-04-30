
package com.samples.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
@XmlRootElement(name = "PurchaseOrderRequest", namespace = "http://com/samples/webservices/purchaseorderservice")
public class PurchaseOrderRequest {

    @XmlElement(name = "PurchaseOrderId", namespace = "http://com/samples/webservices/purchaseorderservice", required = true)
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
