package br.com.apache.kafka.services;

import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service // Anotação que indica que esta classe é um serviço no contexto do Spring
public class StringProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate; // Injeção do KafkaTemplate para enviar mensagens

    // Construtor da classe, inicializa o KafkaTemplate
    public StringProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Método para enviar uma mensagem para o tópico Kafka
    public void send(final String message) {
        // Envia a mensagem para o tópico e retorna um CompletableFuture
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("str-topic", message);
        // Adiciona um callback para ser chamado quando a mensagem for enviada com sucesso ou se ocorrer um erro
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                // Se não houve erro, chama o método para tratar o sucesso
                handleSuccess(result , message );
            }
            else {
                // Se houve erro, chama o método para tratar o erro
                handleFailure(ex , message);
            }
        });
    }

    // Método para tratar o sucesso do envio da mensagem
    private void handleSuccess(SendResult<String, String> result,String data) {
        // Extrai a mensagem do resultado
        JSONObject jsonObject = new JSONObject(result.getProducerRecord().value());
        String message = jsonObject.getString("message");

        // Imprime a mensagem, a partição e o offset
        System.out.printf("Sent message=[%s] Partition=[%d] Offset=[%d]%n",
                message,
                result.getRecordMetadata().partition(),
                result.getRecordMetadata().offset());
    }

    // Método para tratar o erro no envio da mensagem
    private void handleFailure(Throwable ex, String data) {
        // Imprime a mensagem de erro
        System.out.println("Unable to send message=[" + data + "] due to : " + ex.getMessage());
    }

}