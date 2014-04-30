package com.samples.client;

import com.samples.ws.PurchaseOrderId;
import com.samples.ws.PurchaseOrderRequest;
import com.samples.ws.PurchaseOrderResponse;
import com.samples.ws.PurchaseOrderService;
import com.samples.ws.PurchaseOrderServices;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PurchaseOrderServiceClient {

    public static void main(String[] args) {
        System.out.println("Start test ...");

        String globalProductIdentifier = "1234";
        String globalVendorIdentifier = "5678";

        PurchaseOrderServices purchaseOrderServices = new PurchaseOrderServices();
        PurchaseOrderService purchaseOrderService = purchaseOrderServices.getPort(PurchaseOrderService.class);
        PurchaseOrderRequest purchaseOrderRequest = new PurchaseOrderRequest();
        PurchaseOrderId purchaseOrderIdRequest = new PurchaseOrderId();
        purchaseOrderIdRequest.setGlobalProductIdentifier(globalProductIdentifier);
        purchaseOrderIdRequest.setGlobalVendorIdentifier(globalVendorIdentifier);
        purchaseOrderRequest.setPurchaseOrderId(purchaseOrderIdRequest);

        PurchaseOrderResponse purchaseOrderResponse = purchaseOrderService.purchaseOrder(purchaseOrderRequest);
        PurchaseOrderId purchaseOrderIdResponse = purchaseOrderResponse.getPurchaseOrder().getPurchaseOrderId();
        String globalProductIdentifierResponse = purchaseOrderIdResponse.getGlobalProductIdentifier();
        String globalVendorIdentifierResponse = purchaseOrderIdResponse.getGlobalVendorIdentifier();

        assertThat(globalProductIdentifier, equalTo(globalProductIdentifierResponse));
        assertThat(globalVendorIdentifier, equalTo(globalVendorIdentifierResponse));

        System.out.println("Test successfully finished.");
    }
}
