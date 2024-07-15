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
    @NotNull(message = "{field.client-code.required}")
    private Integer client;

    @NotNull(message = "{field.total-order.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{field.items-order.required}")
    List<ItemOrderDTO> items;
}
