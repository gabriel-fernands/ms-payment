package com.example.pereira.model;

import com.example.pereira.dtos.PaymentDto;
import com.example.pereira.enus.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        @Positive
        private BigDecimal valor;

        @NotBlank
        @Size(max=100)
        private String nome;

        @NotBlank
        @Size(max=19)
        private String numero;

        @NotBlank
        @Size(max=7)
        private String expiracao;

        @NotBlank
        @Size(min=3, max=3)
        private String codigo;

        @NotNull
        @Enumerated(EnumType.STRING)
        private Status status;

        @NotNull
        private Long pedidoId;

        @NotNull
        private Long formaDePagamentoId;


        public PaymentModel(PaymentDto dto) {
                this.nome = dto.getNome();
                this.numero = dto.getNumero();
                this.expiracao = dto.getExpiracao();
                this.codigo = dto.getCodigo();
                this.status = dto.getStatus();
                this.valor = dto.getValor();
                this.formaDePagamentoId = dto.getFormaDePagamentoId();
                this.pedidoId = dto.getPedidoId();
        }


        public PaymentModel(PaymentModel dto) {
                this.nome = dto.getNome();
                this.numero = dto.getNumero();
                this.expiracao = dto.getExpiracao();
                this.codigo = dto.getCodigo();
                this.status = dto.getStatus();
                this.valor = dto.getValor();
                this.formaDePagamentoId = dto.getFormaDePagamentoId();
                this.pedidoId = dto.getPedidoId();
        }
}



