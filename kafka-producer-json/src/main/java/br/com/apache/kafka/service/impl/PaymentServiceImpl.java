package br.com.apache.kafka.service.impl;

import br.com.apache.kafka.model.Payment;
import br.com.apache.kafka.service.PaymentService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    public PaymentServiceImpl(KafkaTemplate<String, Serializable> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendPayment(Payment payment) {
        System.out.printf("PAYMENT_SERVICE_IMPL ::: Sent payment=[%s] ID=[%d] IDUser=[%d]%n",
                payment.getCardNumber(),
                payment.getId(),
                payment.getIdUser());

        kafkaTemplate.send("payment-topic", payment);
    }


}
