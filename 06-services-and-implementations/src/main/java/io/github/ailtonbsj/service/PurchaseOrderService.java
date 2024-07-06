package io.github.ailtonbsj.service;

import io.github.ailtonbsj.domain.entity.PurchaseOrder;
import io.github.ailtonbsj.rest.dto.PurchaseOrderDTO;

public interface PurchaseOrderService {
    PurchaseOrder save(PurchaseOrderDTO dto);
}
