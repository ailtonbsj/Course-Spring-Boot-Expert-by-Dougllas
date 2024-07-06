package io.github.ailtonbsj.service.impl;

import io.github.ailtonbsj.domain.entity.Client;
import io.github.ailtonbsj.domain.entity.ItemOrder;
import io.github.ailtonbsj.domain.entity.Product;
import io.github.ailtonbsj.domain.entity.PurchaseOrder;
import io.github.ailtonbsj.domain.repositories.Clients;
import io.github.ailtonbsj.domain.repositories.ItemOrders;
import io.github.ailtonbsj.domain.repositories.Products;
import io.github.ailtonbsj.domain.repositories.PurchaseOrders;
import io.github.ailtonbsj.exception.BussinessRoleException;
import io.github.ailtonbsj.rest.dto.ItemOrderDTO;
import io.github.ailtonbsj.rest.dto.PurchaseOrderDTO;
import io.github.ailtonbsj.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrders repository;
    private final Clients clientsRepository;
    private final Products productsRepository;
    private final ItemOrders itemOrderRepository;

    @Override
    @Transactional
    public PurchaseOrder save(PurchaseOrderDTO dto) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        Client client = clientsRepository.findById(dto.getClient())
                .orElseThrow(() -> new BussinessRoleException("Client code invalid!"));

        List<ItemOrder> itemOrders = convertItems(purchaseOrder, dto.getItems());
        itemOrderRepository.saveAll(itemOrders);

        purchaseOrder.setClient(client);
        purchaseOrder.setItems(itemOrders);
        purchaseOrder.setTotal(dto.getTotal());
        purchaseOrder.setOrderDate(LocalDate.now());

        repository.save(purchaseOrder);
        return purchaseOrder;
    }

    private List<ItemOrder> convertItems(
            PurchaseOrder purchaseOrder, List<ItemOrderDTO> items){
        if(items.isEmpty())
            throw new BussinessRoleException("Order without items!");
        return items.stream().map(dto -> {
            Integer productId = dto.getProduct();
            Product product = productsRepository.findById(productId)
                .orElseThrow(() ->
                    new BussinessRoleException("Product code invalid!"));

            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setAmount(dto.getAmount());
            itemOrder.setOrders(purchaseOrder);
            itemOrder.setProducts(product);
            return itemOrder;
        }).collect(Collectors.toList());
    }
}
