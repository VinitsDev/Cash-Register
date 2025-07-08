package br.com.vinicius.Cash_Register.controllers;

import br.com.vinicius.Cash_Register.dtos.CashRegisterCloseRequest;
import br.com.vinicius.Cash_Register.dtos.CashRegisterOpenRequest;
import br.com.vinicius.Cash_Register.entities.CashRegister;
import br.com.vinicius.Cash_Register.services.CashRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cash_registers")
@Validated
public class CashRegisterController {

    private final CashRegisterService cashRegisterService;

    public CashRegisterController(CashRegisterService cashRegisterService) {
        this.cashRegisterService = cashRegisterService;
    }

    @PostMapping
    @Operation(summary = "Opens a cash register")
    public ResponseEntity<CashRegister> openedCashRegister(@Valid @RequestBody CashRegisterOpenRequest request){
        CashRegister cashRegister = cashRegisterService.openedCashRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cashRegister);
    }

    @PutMapping("{id}")
    @Operation(summary = "Closes a cash register")
    public ResponseEntity<CashRegister> closedCashRegister(@PathVariable Long id, @Valid @RequestBody CashRegisterCloseRequest request){
        CashRegister cashRegister = cashRegisterService.closedCashRegister(id, request);
        return ResponseEntity.ok(cashRegister);
    }

    @GetMapping
    @Operation(summary = "Returns a list with all cash registers")
    public List<CashRegister> list(){
        return cashRegisterService.list();
    }
}
