package com.example.pereira.repository;

import com.example.pereira.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentModel,Long> {
}
