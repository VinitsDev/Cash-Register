package br.com.vinicius.Cash_Register.repositories;

import br.com.vinicius.Cash_Register.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
