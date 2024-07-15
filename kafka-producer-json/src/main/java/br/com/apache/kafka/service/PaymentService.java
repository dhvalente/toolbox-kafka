package br.com.apache.kafka.service;

import br.com.apache.kafka.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}
