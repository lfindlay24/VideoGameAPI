package org.openapitools.businessLayer;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "videogameapiwithkafka-kafka-1:9092")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic offersCreatedTopic() {
        return new NewTopic("offersCreated", 1, (short) 1);
    }

    @Bean
    public NewTopic offersAcceptedTopic() {
        return new NewTopic("offersAccepted", 1, (short) 1);
    }

    @Bean
    public NewTopic offersDeclinedTopic() {
        return new NewTopic("offersDeclined", 1, (short) 1);
    }

    @Bean
    public NewTopic usersTopic() {
        return new NewTopic("users", 1, (short) 1);
    }

}

