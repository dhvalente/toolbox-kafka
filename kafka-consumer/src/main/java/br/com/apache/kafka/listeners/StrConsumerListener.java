package br.com.apache.kafka.listeners;

import br.com.apache.kafka.custom.StrConsumerCustomListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class StrConsumerListener {

    // Método que recebe mensagens do tópico "str-topic"
    @StrConsumerCustomListener(groupId = "group-1")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    // Método que recebe mensagens do tópico "str-topic"
    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "str-topic", partitions = {"0,1"})
    }, containerFactory = "strContainerFactory")
    public void consumePartition(String message) {
        System.out.println("Consumed message on partition: " + message);
    }

    @KafkaListener(topics = "str-topic", containerFactory = "validStrContainerFactory", groupId = "group-2")
    public void consumeValidMessage(String message) {
        System.out.println("Consumed valid message: " + message);
    }
}
