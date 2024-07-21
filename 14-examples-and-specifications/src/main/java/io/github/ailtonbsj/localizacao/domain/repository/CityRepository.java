package io.github.ailtonbsj.localizacao.domain.repository;

import io.github.ailtonbsj.localizacao.domain.entity.City;
import io.github.ailtonbsj.localizacao.domain.repository.projections.CityProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByName(String name);

    List<City> findByPopulation(Long population);

    Iterable<City> findByNameLike(String s);

    @Query("select c from City c where lower(c.name) like lower(?1)")
    Iterable<City> findByNameLikeNotCase(String porto);

    @Query("select c from City c where lower(c.name) like lower(?1)")
    Iterable<City> findByNameLikeNotCase(String porto, Sort sort);

    List<City> findByPopulationLessThan(Long population);

    Page<City> findAll(Pageable pageable);

    List<City> findAll(Specification<City> spec);

    @Query(nativeQuery = true,
        value = "select * from tb_city as c where c.name = :name")
    List<City> findByNameSqlNative(@Param("name") String name);

    @Query(nativeQuery = true,
            value = "select c.name from tb_city as c where c.name = :name")
    List<String> findByNameSqlSingle(@Param("name") String name);

    @Query(nativeQuery = true,
            value = "select c.id_city as id, c.name from tb_city as c where c.name = :name")
    List<CityProjection> findByNameSqlProjection(@Param("name") String name);

}
