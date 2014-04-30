
package com.samples.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PurchaseOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PurchaseOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="purchaseOrderId" type="{http://purchaseorder.webservices.samples.com}PurchaseOrderId"/>
 *         &lt;element name="orderQuantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="productCost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurchaseOrder", propOrder = {
    "purchaseOrderId",
    "orderQuantity",
    "productCost"
})
public class PurchaseOrder {

    @XmlElement(required = true)
    protected PurchaseOrderId purchaseOrderId;
    protected int orderQuantity;
    protected int productCost;

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

    /**
     * Gets the value of the orderQuantity property.
     * 
     */
    public int getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * Sets the value of the orderQuantity property.
     * 
     */
    public void setOrderQuantity(int value) {
        this.orderQuantity = value;
    }

    /**
     * Gets the value of the productCost property.
     * 
     */
    public int getProductCost() {
        return productCost;
    }

    /**
     * Sets the value of the productCost property.
     * 
     */
    public void setProductCost(int value) {
        this.productCost = value;
    }

}
