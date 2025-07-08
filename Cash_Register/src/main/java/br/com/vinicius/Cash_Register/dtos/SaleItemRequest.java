package br.com.vinicius.Cash_Register.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleItemRequest {

    @NotNull
    private Long productId;

    @NotNull
    @Min(value=0)
    private Integer quantity;

}
