package io.github.ailtonbsj.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemOrderDTO {
    private Integer product;
    private Integer amount;
}
