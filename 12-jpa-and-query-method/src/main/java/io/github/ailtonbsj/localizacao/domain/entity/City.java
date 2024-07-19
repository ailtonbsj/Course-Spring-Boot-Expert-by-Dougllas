package io.github.ailtonbsj.localizacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @Column(name = "id_city")
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(name = "qt_population")
    private Long population;
}
