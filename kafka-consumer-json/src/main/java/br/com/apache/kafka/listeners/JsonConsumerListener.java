package br.com.apache.kafka.listeners;

import br.com.apache.kafka.model.Payment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class JsonConsumerListener {

    @KafkaListener(topics = "payment-topic", containerFactory = "jsonContainerFactory", groupId = "create-group")
    public void consumeValidMessage(Payment payment) {
        System.out.println("Consumed Json message: " + payment.toString());
    }
}
