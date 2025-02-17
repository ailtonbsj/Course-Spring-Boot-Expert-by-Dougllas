package io.github.ailtonbsj.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationItemOrderDTO {
    private String descriptionProduct;
    private BigDecimal unitPrice;
    private Integer amount;
}
