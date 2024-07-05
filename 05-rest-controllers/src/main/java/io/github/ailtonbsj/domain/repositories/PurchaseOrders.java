package io.github.ailtonbsj.domain.repositories;

import io.github.ailtonbsj.domain.entity.Client;
import io.github.ailtonbsj.domain.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrders extends JpaRepository<PurchaseOrder,Integer> {
    List<PurchaseOrder> findByClient(Client client);
}
