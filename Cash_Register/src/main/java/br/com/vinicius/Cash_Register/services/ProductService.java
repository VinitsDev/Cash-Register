package br.com.vinicius.Cash_Register.services;

import br.com.vinicius.Cash_Register.dtos.ProductRequest;
import br.com.vinicius.Cash_Register.entities.Product;
import br.com.vinicius.Cash_Register.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(ProductRequest request){
        Product product = new Product(null, request.getName(),
                request.getPrice(), request.getQuantity(), true);
        return productRepository.save(product);
    }

    public List<Product> list(){
        return productRepository.findAll(Sort.by("price").ascending());
    }

    public void delete (Long id){
         productRepository.deleteById(id);
    }

    public Product update(Product product){
        return productRepository.save(product);
    }

}
