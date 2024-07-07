package io.github.ailtonbsj.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationOrderDTO {
    private  String code;
    private String cpf;
    private String nameClient;
    private BigDecimal total;
    private String orderDate;
    private String status;
    private List<InformationItemOrderDTO> items;
}
