package io.github.ailtonbsj.localizacao.service;

import io.github.ailtonbsj.localizacao.domain.entity.City;
import io.github.ailtonbsj.localizacao.domain.repository.CityRepository;
import org.springframework.stereotype.Service;

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
        repository.findByNameLikeCase("%PORTO%").forEach(System.out::println);
    }

    public void listCitiesByPopulation() {
        repository.findByPopulation(78787900L).forEach(System.out::println);
    }

    public void listCitiesByNumbers() {
        repository.findByPopulationLessThan(2000000L).forEach(System.out::println);
    }
}
