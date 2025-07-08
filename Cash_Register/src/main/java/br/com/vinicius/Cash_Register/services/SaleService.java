package br.com.vinicius.Cash_Register.services;

import br.com.vinicius.Cash_Register.dtos.SaleItemRequest;
import br.com.vinicius.Cash_Register.dtos.SaleItemResponse;
import br.com.vinicius.Cash_Register.dtos.SaleRequest;
import br.com.vinicius.Cash_Register.dtos.SaleResponse;
import br.com.vinicius.Cash_Register.entities.CashRegister;
import br.com.vinicius.Cash_Register.entities.Product;
import br.com.vinicius.Cash_Register.entities.Sale;
import br.com.vinicius.Cash_Register.entities.SaleItem;
import br.com.vinicius.Cash_Register.repositories.CashRegisterRepository;
import br.com.vinicius.Cash_Register.repositories.ProductRepository;
import br.com.vinicius.Cash_Register.repositories.SaleItemRepository;
import br.com.vinicius.Cash_Register.repositories.SaleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CashRegisterRepository cashRegisterRepository;
    private final ProductRepository productRepository;

    public SaleService(
            SaleRepository saleRepository,
            CashRegisterRepository cashRegisterRepository,
            ProductRepository productRepository
    ) {
        this.saleRepository = saleRepository;
        this.cashRegisterRepository = cashRegisterRepository;
        this.productRepository = productRepository;
    }

    public SaleResponse create(SaleRequest request) {

        CashRegister cashRegister = cashRegisterRepository.findById(request.getCashRegisterId())
                .orElseThrow(() -> new RuntimeException("Caixa não encontrado."));

        if (!cashRegister.getActive()) {
            throw new RuntimeException("Caixa está fechado.");
        }

        Sale sale = new Sale();
        sale.setCashRegister(cashRegister);
        sale.setPaymentMethod(request.getPaymentMethod());
        sale.setDate(LocalDateTime.now());

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<SaleItem> saleItems = new ArrayList<>();

        for (SaleItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(product);
            saleItem.setQuantity(itemRequest.getQuantity());
            saleItem.setSale(sale);

            saleItems.add(saleItem);
            totalPrice = totalPrice.add(subtotal);
        }

        sale.setTotalPrice(totalPrice);
        sale.setItems(saleItems);

        Sale savedSale = saleRepository.save(sale);

        return toResponse(savedSale);
    }

    public List<SaleResponse> list() {
        return saleRepository.findAll(Sort.by("date").ascending())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public SaleResponse findSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        return toResponse(sale);
    }

    public void delete(Long id) {
        saleRepository.deleteById(id);
    }


    private SaleResponse toResponse(Sale sale) {
        SaleResponse response = new SaleResponse();
        response.setId(sale.getId());
        response.setTotalPrice(sale.getTotalPrice());
        response.setDate(sale.getDate());
        response.setPaymentMethod(sale.getPaymentMethod());
        response.setId(sale.getCashRegister().getId());

        List<SaleItemResponse> itemResponses = sale.getItems()
                .stream()
                .map(item -> {
                    SaleItemResponse itemResp = new SaleItemResponse();
                    itemResp.setId(item.getId());
                    itemResp.setProductId(item.getProduct().getId());
                    itemResp.setProductName(item.getProduct().getName());
                    itemResp.setProductPrice(item.getProduct().getPrice());
                    itemResp.setQuantity(item.getQuantity());
                    return itemResp;
                })
                .collect(Collectors.toList());

        response.setItems(itemResponses);
        return response;
    }
}
