package br.com.vinicius.Cash_Register.repositories;

import br.com.vinicius.Cash_Register.entities.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Long> {
}
