package io.github.ailtonbsj.service.impl;

import io.github.ailtonbsj.domain.repositories.PurchaseOrders;
import io.github.ailtonbsj.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private PurchaseOrders repository;

    public PurchaseOrderServiceImpl(PurchaseOrders repository) {
        this.repository = repository;
    }
}
