package com.example.pereira.dtos;

import com.example.pereira.enus.Status;
import com.example.pereira.model.PaymentModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

        private Long id;
        private BigDecimal valor;
        private String nome;
        private String numero;
        private String expiracao;
        private String codigo;
        private Status status;
        private Long formaDePagamentoId;
        private Long pedidoId;


    public PaymentDto(PaymentModel payment) {
        this.nome = payment.getNome();
        this.numero = payment.getNumero();
        this.codigo = payment.getCodigo();
        this.expiracao = payment.getExpiracao();
        this.status = payment.getStatus();
        this.formaDePagamentoId = payment.getFormaDePagamentoId();
        this.pedidoId = payment.getPedidoId();
        this.valor = payment.getValor();
    }
}

