package br.com.apache.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.Serializable;
import java.util.HashMap;

@Configuration
public class JsonProducerConfig {

    private final KafkaProperties properties;

    public JsonProducerConfig(KafkaProperties properties) {
        this.properties = properties;
    }
  /*  @Bean
    public ProducerFactory jsonProducerFactory(){
        var configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(configs, new StringSerializer(), new JsonSerializer());
    }*/

    public ProducerFactory<String, Serializable> jsonProducerFactory(){
        var configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, Serializable>(configs, new StringSerializer(), new JsonSerializer<Serializable>());
    }

    // Este método cria um KafkaTemplate que encapsula uma instância de Produtor e fornece métodos de conveniência para enviar dados para tópicos Kafka
    @Bean
    public KafkaTemplate<String, Serializable> jsonKafkaTemplate (){
        return new KafkaTemplate<>(jsonProducerFactory());
    }
}
