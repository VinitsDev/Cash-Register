package br.com.vinicius.Cash_Register.dtos;


import br.com.vinicius.Cash_Register.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SaleResponse {

    private Long id;
    private BigDecimal totalPrice;
    private LocalDateTime date;
    private PaymentMethod paymentMethod;
    private List<SaleItemResponse> items;

}
