package br.com.vinicius.Cash_Register.dtos;

import br.com.vinicius.Cash_Register.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaleRequest {

    @NotNull
    private Long cashRegisterId;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private List<SaleItemRequest> items;

}
