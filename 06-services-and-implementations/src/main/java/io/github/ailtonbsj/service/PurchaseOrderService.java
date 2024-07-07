package io.github.ailtonbsj.service;

import io.github.ailtonbsj.domain.entity.PurchaseOrder;
import io.github.ailtonbsj.domain.enums.StatusOrder;
import io.github.ailtonbsj.rest.dto.PurchaseOrderDTO;

import java.util.Optional;

public interface PurchaseOrderService {
    PurchaseOrder save(PurchaseOrderDTO dto);
    Optional<PurchaseOrder> getFullOrder(Integer id);
    void updateStatusOrder(Integer id, StatusOrder statusOrder);
}
