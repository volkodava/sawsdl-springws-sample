package com.samples.services;

import com.samples.webservices.purchaseorder.PurchaseOrder;
import com.samples.webservices.purchaseorder.PurchaseOrderId;
import org.springframework.stereotype.Service;

/**
 * The Class PurchaseOrderService.
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    /**
     * Gets the purchase order.
     *
     * @param id the purchase order id
     * @return the purchase order
     */
    public PurchaseOrder getPurchaseOrder(PurchaseOrderId id) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseOrderId(id);
        purchaseOrder.setOrderQuantity(10);
        purchaseOrder.setProductCost(20);

        return purchaseOrder;
    }
}
