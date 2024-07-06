package io.github.ailtonbsj.domain.repositories;

import io.github.ailtonbsj.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Products extends JpaRepository<Product,Integer> {
}
