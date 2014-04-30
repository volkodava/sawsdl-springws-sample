package com.samples.services.endpoints;

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.samples.services.PurchaseOrderService;
import com.samples.webservices.purchaseorder.PurchaseOrder;
import com.samples.webservices.purchaseorderservice.PurchaseOrderRequest;
import com.samples.webservices.purchaseorderservice.PurchaseOrderResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

/**
 * The Class PurchaseOrderService.
 */
@Endpoint
public class PurchaseOrderServiceEndpoint implements InitializingBean {

    protected Logger logger = Logger.getLogger(getClass());
    private static final String TARGET_NAMESPACE = "http://com/samples/webservices/purchaseorderservice";

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    /**
     * Gets the account details.
     *
     * @param request
     * @return the account details
     */
    @PayloadRoot(localPart = "PurchaseOrderRequest", namespace = TARGET_NAMESPACE)
    public @ResponsePayload
    PurchaseOrderResponse getPurchaseOrder(@RequestPayload PurchaseOrderRequest request) {
        logger.log(Priority.INFO, "Request: " + request);
        PurchaseOrderResponse response = new PurchaseOrderResponse();

        /* call Spring injected service implementation to retrieve purchase order data */
        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrder(request.getPurchaseOrderId());
        response.setPurchaseOrder(purchaseOrder);
        return response;
    }

    public void setPurchaseOrderService(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(purchaseOrderService);
    }
}
