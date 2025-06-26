package br.com.vinicius.Cash_Register.repositories;

import br.com.vinicius.Cash_Register.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
