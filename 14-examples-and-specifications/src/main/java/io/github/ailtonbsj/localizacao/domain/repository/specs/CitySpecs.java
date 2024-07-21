package io.github.ailtonbsj.localizacao.domain.repository.specs;

import io.github.ailtonbsj.localizacao.domain.entity.City;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public abstract class CitySpecs {
    public static Specification<City> propertyEqual(String prop, Object val) {
        return (root, query, cb) -> cb.equal(root.get(prop), val);
    }

    public static Specification<City> idEqual(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<City> nameEquals(String name) {
        return (root, query, cb) -> cb.equal(root.get("name"), name);
    }

    public static Specification<City> populationGreaterThan(Long val) {
        return (root, query, cb) -> cb.greaterThan(root.get("population"), val);
    }

    public static Specification<City> populationBetween(Long min, Long max) {
        return (root, query, cb) -> cb.between(root.get("population"), min, max);
    }

    public static Specification<City> nameLike(String name) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("name")), ("%" + name + "%").toUpperCase());
    }
}
