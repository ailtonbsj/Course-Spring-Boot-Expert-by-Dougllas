package io.github.ailtonbsj.domain.repositories;

import io.github.ailtonbsj.domain.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrders extends JpaRepository<ItemOrder,Integer> {
}
