package br.com.apache.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;

@Configuration
public class KafkaAdminConfig {

    // Propriedades do Kafka
    public KafkaProperties properties;

    public KafkaAdminConfig(KafkaProperties properties) {
        this.properties = properties;
    }

    // Método que cria um KafkaAdmin, que é usado para operações administrativas no Kafka
    @Bean
    public KafkaAdmin kafkaAdmin() {
        var configs = new HashMap<String, Object>();
        // Configura o servidor Kafka para se conectar
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        return new KafkaAdmin(configs);
    }

    // Método que cria os tópicos no Kafka
    @Bean
    public KafkaAdmin.NewTopics topics(){
        return new KafkaAdmin.NewTopics(
                // Cria um novo tópico chamado "str-topic" com 2 partições e 1 réplica
                TopicBuilder.name("payment-topic").partitions(1).replicas(1).build()
        );
    }
}