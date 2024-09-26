package com.example.pereira.controller;

import com.example.pereira.dtos.PaymentDto;
import com.example.pereira.model.PaymentModel;
import com.example.pereira.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<PaymentModel> createPayment(@RequestBody PaymentDto dto, UriComponentsBuilder uriBuilder) {
        return Optional.of(dto)
                .map(PaymentModel::new)
                .map(service::createPayment)
                .map(payment -> buildResponseEntity(payment, uriBuilder))
                .orElseThrow(() -> new RuntimeException("Falha ao criar payment"));
    }

    private ResponseEntity<PaymentModel> buildResponseEntity(PaymentModel payment, UriComponentsBuilder uriBuilder) {
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(endereco).body(payment);
    }
    @GetMapping
    public Page<PaymentDto> pagi(Pageable paginar) {
        return Optional.ofNullable(paginar)
                .map(service::page)
                .orElse(Page.empty());
    }
    @GetMapping("/list")

    public List<PaymentModel> listTodos(@RequestParam(required = false) PaymentModel paymentModel) {
        return service.listarTodos(paymentModel);
    }



    @PutMapping("/{id}")
    public ResponseEntity<PaymentModel> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDto dto) {
        return Optional.of(dto)
                .map(d -> service.atualizar(d, id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity<List<PaymentModel>> delete(@PathVariable("id") Long id, List<PaymentModel> payments) {
        return payments.stream()
                .anyMatch(payment -> payment.getId().equals(id))
                ? ResponseEntity.ok(payments.stream()
                .filter(payment -> !payment.getId().equals(id))
                .collect(Collectors.toList()))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


}
