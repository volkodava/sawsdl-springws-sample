package com.samples.services;

import com.samples.webservices.purchaseorder.PurchaseOrder;
import com.samples.webservices.purchaseorder.PurchaseOrderId;

/**
 * The Interface PurchaseOrderService.
 */
public interface PurchaseOrderService {

    /**
     * Gets the purchase order.
     *
     * @param id the purchase order id
     * @return the purchase order
     */
    PurchaseOrder getPurchaseOrder(PurchaseOrderId id);
}
