package br.com.apache.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

public class StringProducerFactoryConfig {

    // KafkaProperties é uma classe auxiliar que contém as propriedades de configuração do Kafka
    private final KafkaProperties properties;

    // Construtor da classe, inicializa o KafkaProperties
    public StringProducerFactoryConfig(KafkaProperties properties) {
        this.properties = properties;
    }

    // Este método cria um ProducerFactory que é responsável por criar os Produtores Kafka
    // A configuração para o produtor é definida aqui
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        var configs = new HashMap<String, Object>();
        // O servidor Kafka para se conectar
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        // A classe serializadora para chaves. Determina como transformar os objetos de chave em bytes
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // A classe serializadora para valores. Determina como transformar os objetos de valor em bytes
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Cria uma nova fábrica de produtores com a configuração
        return new DefaultKafkaProducerFactory<>(configs);
    }

    // Este método cria um KafkaTemplate que encapsula uma instância de Produtor e fornece métodos de conveniência para enviar dados para tópicos Kafka
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate (ProducerFactory<String, String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

}