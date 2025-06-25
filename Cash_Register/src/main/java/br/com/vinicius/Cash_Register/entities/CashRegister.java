package br.com.vinicius.Cash_Register.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_registers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private BigDecimal openingAmount;
    private BigDecimal closingAmount;
    private Boolean active;

}
