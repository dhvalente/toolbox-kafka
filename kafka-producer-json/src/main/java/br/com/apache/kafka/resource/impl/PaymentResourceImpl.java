package br.com.apache.kafka.resource.impl;

import br.com.apache.kafka.model.Payment;
import br.com.apache.kafka.resource.PaymentResource;
import br.com.apache.kafka.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payments")
public class PaymentResourceImpl implements PaymentResource {
    private final PaymentService paymentService;

public PaymentResourceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public ResponseEntity<Payment> payment(Payment payment) {
        paymentService.sendPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
