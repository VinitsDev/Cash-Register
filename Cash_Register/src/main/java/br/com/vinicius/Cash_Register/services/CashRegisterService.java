package br.com.vinicius.Cash_Register.services;

import br.com.vinicius.Cash_Register.dtos.CashRegisterCloseRequest;
import br.com.vinicius.Cash_Register.dtos.CashRegisterOpenRequest;
import br.com.vinicius.Cash_Register.entities.CashRegister;
import br.com.vinicius.Cash_Register.repositories.CashRegisterRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CashRegisterService {

    private CashRegisterRepository cashRegisterRepository;

    public CashRegisterService(CashRegisterRepository cashRegisterRepository) {
        this.cashRegisterRepository = cashRegisterRepository;
    }

    public CashRegister openedCashRegister(CashRegisterOpenRequest request){
        CashRegister cashRegister = new CashRegister(
                null,
                LocalDateTime.now(),
                null,
                request.getOpeningAmount(),
                null,
                true);
        return cashRegisterRepository.save(cashRegister);
    }

    public CashRegister closedCashRegister(Long id, CashRegisterCloseRequest request){
        CashRegister cashRegister = cashRegisterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Caixa não encontrado com id: " + id));
        cashRegister.setClosedAt(LocalDateTime.now());
        cashRegister.setClosingAmount(request.getClosingAmount());
        cashRegister.setActive(false);
        return cashRegisterRepository.save(cashRegister);
    }

    public List<CashRegister> list(){
        return cashRegisterRepository.findAll(Sort.by("active").ascending());
    }

}
