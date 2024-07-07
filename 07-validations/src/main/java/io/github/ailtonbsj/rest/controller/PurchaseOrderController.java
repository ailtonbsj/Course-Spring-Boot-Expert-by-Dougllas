package io.github.ailtonbsj.rest.controller;

import io.github.ailtonbsj.domain.entity.ItemOrder;
import io.github.ailtonbsj.domain.entity.PurchaseOrder;
import io.github.ailtonbsj.domain.enums.StatusOrder;
import io.github.ailtonbsj.rest.dto.InformationItemOrderDTO;
import io.github.ailtonbsj.rest.dto.InformationOrderDTO;
import io.github.ailtonbsj.rest.dto.PurchaseOrderDTO;
import io.github.ailtonbsj.rest.dto.StatusOrderDTO;
import io.github.ailtonbsj.service.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders/")
public class PurchaseOrderController {
    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PurchaseOrderDTO dto){
        PurchaseOrder purchaseOrder = service.save(dto);
        return purchaseOrder.getId();
    }

    @GetMapping("{id}")
    public InformationOrderDTO getById(@PathVariable Integer id){
        return service.getFullOrder(id)
                .map(this::convertOrderToOrderDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody StatusOrderDTO dto){
        service.updateStatusOrder(id, StatusOrder.valueOf(dto.getNewStatus()));
    }

    private InformationOrderDTO convertOrderToOrderDTO(PurchaseOrder purchaseOrder){
        return InformationOrderDTO.builder()
                .code(purchaseOrder.getId().toString())
                .orderDate(purchaseOrder.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(purchaseOrder.getClient().getCpf())
                .nameClient(purchaseOrder.getClient().getName())
                .total(purchaseOrder.getTotal())
                .items(convertItemsOrderToItemsOrderDTO(purchaseOrder.getItems()))
                .status(purchaseOrder.getStatus().name())
                .build();
    }

    private List<InformationItemOrderDTO> convertItemsOrderToItemsOrderDTO(List<ItemOrder> items){
        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }
        return items.stream().map(item ->
            InformationItemOrderDTO.builder()
                    .descriptionProduct(item.getProduct().getDescription())
                    .unitPrice(item.getProduct().getUnitPrice())
                    .amount(item.getAmount())
                    .build()
        ).collect(Collectors.toList());
    }
}
