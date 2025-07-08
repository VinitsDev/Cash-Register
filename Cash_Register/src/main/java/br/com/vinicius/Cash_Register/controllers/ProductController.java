package br.com.vinicius.Cash_Register.controllers;

import br.com.vinicius.Cash_Register.dtos.ProductRequest;
import br.com.vinicius.Cash_Register.entities.Product;
import br.com.vinicius.Cash_Register.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Creates a new product")
    public ResponseEntity<Product> create (@Valid @RequestBody ProductRequest request){
        Product product = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping
    @Operation(summary = "Updates a existing product")
    public ResponseEntity<Product> update(@RequestBody Product product){
        productService.update(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    @Operation(summary = "Returns a list with all products")
    public List<Product> list (){
        return productService.list();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletes an existing product")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
