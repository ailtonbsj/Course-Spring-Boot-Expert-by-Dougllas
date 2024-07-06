package io.github.ailtonbsj.rest.controller;

import io.github.ailtonbsj.domain.entity.PurchaseOrder;
import io.github.ailtonbsj.rest.dto.PurchaseOrderDTO;
import io.github.ailtonbsj.service.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/")
public class PurchaseOrderController {
    private PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PurchaseOrderDTO dto){
        PurchaseOrder purchaseOrder = service.save(dto);
        return purchaseOrder.getId();
    }
}
