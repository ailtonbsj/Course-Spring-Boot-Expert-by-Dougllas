package io.github.ailtonbsj.localizacao;

import io.github.ailtonbsj.localizacao.domain.entity.City;
import io.github.ailtonbsj.localizacao.domain.repository.CityRepository;
import io.github.ailtonbsj.localizacao.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CityService service;

	@Override
	public void run(String... args) throws Exception {
		service.listCities();
		service.listCitiesByName();
		service.listCitiesByPopulation();
		service.listCitiesByNameLike();
		service.listCityByNameLikeNotCase();
		service.listCitiesByNumbers();
		service.listCitiesAndSort();
		System.out.println("----- PAGING -----");
		int page = -1;
		int total = 0;
		do {
			page++;
			total = service.listCitiesByPages(page);
		} while (page < total - 1);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}
}
