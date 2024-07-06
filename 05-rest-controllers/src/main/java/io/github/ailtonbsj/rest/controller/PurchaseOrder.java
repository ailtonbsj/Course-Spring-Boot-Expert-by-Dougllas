package io.github.ailtonbsj.rest.controller;

import io.github.ailtonbsj.service.PurchaseOrderService;

public class PurchaseOrder {
    private final PurchaseOrderService service;

    public PurchaseOrder(PurchaseOrderService service) {
        this.service = service;
    }
}
