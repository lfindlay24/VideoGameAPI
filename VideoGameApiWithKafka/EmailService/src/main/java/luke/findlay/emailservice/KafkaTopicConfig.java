package luke.findlay.emailservice;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    /*
    Configures topics to be created on EmailService startup.
    Creates topics for users, offersCreated, offersAccepted, and offersDeclined
    Kafka Admin configures connection to kafka
     */

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

