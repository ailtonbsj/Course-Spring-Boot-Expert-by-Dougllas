package io.github.ailtonbsj.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseOrderDTO {
    private Integer client;
    private BigDecimal total;
    List<ItemOrderDTO> items;
}
