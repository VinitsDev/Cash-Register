package br.com.vinicius.Cash_Register.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CashRegisterOpenRequest {

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal openingAmount;

}
