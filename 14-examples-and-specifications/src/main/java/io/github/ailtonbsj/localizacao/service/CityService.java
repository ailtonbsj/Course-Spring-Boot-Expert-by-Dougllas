package io.github.ailtonbsj.localizacao.service;

import io.github.ailtonbsj.localizacao.domain.entity.City;
import io.github.ailtonbsj.localizacao.domain.repository.CityRepository;
import static io.github.ailtonbsj.localizacao.domain.repository.specs.CitySpecs.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public void saveCity(){
        var city = new City(1L, "São Paulo", 12396372L);
        repository.save(city);
    }

    public void listCities() {
        repository.findAll().forEach(System.out::println);
    }

    public void listCitiesByName() {
        repository.findByName("Porto Velho").forEach(System.out::println);
    }

    public void listCitiesByNameLike() {
        repository.findByNameLike("Porto%").forEach(System.out::println);
    }

    public void listCityByNameLikeNotCase() {
        repository.findByNameLikeNotCase("%PORTO%").forEach(System.out::println);
    }

    public void listCitiesAndSort() {
        repository.findByNameLikeNotCase("%%%%",Sort.by("name", "population"))
                .forEach(System.out::println);
    }

    public void listCitiesByPopulation() {
        repository.findByPopulation(78787900L).forEach(System.out::println);
    }

    public void listCitiesByNumbers() {
        repository.findByPopulationLessThan(2000000L).forEach(System.out::println);
    }

    public int listCitiesByPages(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 3);
        Page<City> page = repository.findAll(pageable);
        System.out.println("Página: " + (pageNumber+1) + " / " + page.getTotalPages());
        page.forEach(System.out::println);
        return page.getTotalPages();
    }

    public List<City> dynamicFilter(City city) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase("name")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<City> example = Example.of(city, matcher);
        return repository.findAll(example);
    }

    public void listCitiesSpec(){
        repository.findAll(
                nameEquals("São Paulo").and(populationGreaterThan(100))
        ).forEach(System.out::println);
    }
}
