package br.com.vinicius.Cash_Register.controllers;

import br.com.vinicius.Cash_Register.dtos.SaleRequest;
import br.com.vinicius.Cash_Register.dtos.SaleResponse;
import br.com.vinicius.Cash_Register.entities.Sale;
import br.com.vinicius.Cash_Register.services.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@Validated
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @Operation(summary = "Creates a new sale")
    public ResponseEntity<SaleResponse> create(@Valid @RequestBody SaleRequest request) {
        SaleResponse saleResponse = saleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleResponse);
    }

    @GetMapping
    @Operation(summary = "Returns a list with all sales")
    public ResponseEntity<List<SaleResponse>> list() {
        List<SaleResponse> responses = saleService.list();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("{id}")
    @Operation(summary = "Returns a specific sale")
    public ResponseEntity<SaleResponse> findSale(@PathVariable Long id) {
        SaleResponse saleResponse = saleService.findSale(id);
        return ResponseEntity.ok(saleResponse);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletes a specific sale")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
