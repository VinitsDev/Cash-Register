package br.com.vinicius.Cash_Register.repositories;

import br.com.vinicius.Cash_Register.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
