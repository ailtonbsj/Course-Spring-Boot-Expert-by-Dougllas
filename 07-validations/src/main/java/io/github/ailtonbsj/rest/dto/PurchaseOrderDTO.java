package io.github.ailtonbsj.rest.dto;

import io.github.ailtonbsj.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseOrderDTO {
    @NotNull(message = "Client code is required!")
    private Integer client;

    @NotNull(message = "Total price is required!")
    private BigDecimal total;

    @NotEmptyList
    List<ItemOrderDTO> items;
}
