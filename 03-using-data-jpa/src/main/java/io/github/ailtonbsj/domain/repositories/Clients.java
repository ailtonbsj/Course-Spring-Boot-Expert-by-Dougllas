package io.github.ailtonbsj.domain.repositories;

import io.github.ailtonbsj.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clients extends JpaRepository<Client, Integer> {
    List<Client> findByNameLike(String name);

    @Query("select c from Client c where c.name like :name")
    List<Client> getAllByName(@Param("name") String name);

    @Query(value = "select * from client c where c.name like '%:name%'", nativeQuery = true)
    List<Client> getAllByNameNative(@Param("name") String name);

    boolean existsByName(String name);

    void deleteByName(String name);

    @Query("delete from Client c where c.name = :name")
    @Modifying
    void removeUsingName(String name);
}
