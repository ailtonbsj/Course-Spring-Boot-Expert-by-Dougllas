package io.github.ailtonbsj.domain.repositories;

import io.github.ailtonbsj.domain.entity.Client;
import io.github.ailtonbsj.domain.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrders extends JpaRepository<PurchaseOrder,Integer> {
    List<PurchaseOrder> findByClient(Client client);

    @Query("select p from PurchaseOrder p left join fetch p.items where p.id = :id")
    Optional<PurchaseOrder> findByIdFetchItems(@Param("id") Integer id);
}
