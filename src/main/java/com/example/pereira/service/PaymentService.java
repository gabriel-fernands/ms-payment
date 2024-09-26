package com.example.pereira.service;

import com.example.pereira.dtos.PaymentDto;
import com.example.pereira.enus.Status;
import com.example.pereira.model.PaymentModel;
import com.example.pereira.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }
    public PaymentModel createPayment(PaymentModel dto) {
        return Optional.of(dto)
                .map(PaymentModel::new)
                .map(repository::save)
                .map(response -> setStatus(response, 0))
                .orElseThrow(() -> new RuntimeException("Failed to create payment"));
    }

    private PaymentModel setStatus(PaymentModel payment, int statusValue) {
        payment.setStatus(Status.devolveStatus(statusValue));
        return payment;
    }
    public Page<PaymentDto> page(Pageable pagina) {
        return Optional.ofNullable(pagina)
                .map(repository::findAll)
                .map(page -> page.map(PaymentDto::new))
                .orElse(Page.empty());
    }
  public List<PaymentModel>listarTodos(PaymentModel paymentModel){
      var sort =   Sort.by("nome").ascending().and(
                Sort.by("status").descending());
        return repository.findAll(sort);
  }
    public PaymentModel atualizar(PaymentDto dto, Long id) {
        return Optional.of(dto)
                .map(PaymentModel::new)
                .map(paymentModel -> {
                    paymentModel.setId(id);
                    return paymentModel;
                })
                .map(repository::save)
                .orElseThrow(() -> new RuntimeException("Failed to create payment"));
    }
  public void delete(Long id){
        repository.deleteById(id);
  }

  public Optional<PaymentModel> listarPorId(Long id){
        return repository.findById(id);
  }


}
