package luke.findlay.emailservice;

import org.openapitools.model.Offer;
import org.openapitools.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class newKafkaConsumerConfig {
    /*
    The Current File for the consumer config
    Each consumer factory configures a consumer for a User and an Offer respectively
    ConcurrentKafkaListenerContainerFactory initializes an actual listener to be used in the EmailService
     */

    @Value("videogameapiwithkafka-kafka-1:9092")
    private String kafkaAddress;

    @Value("user-email-consumer")
    private String groupId;

    @Bean
    public ConsumerFactory<String, User> consumerFactory() {
        JsonDeserializer<User> payloadJsonDeserializer = new JsonDeserializer<>();
        payloadJsonDeserializer.addTrustedPackages("*");
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        //props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, User.class);
        //props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), payloadJsonDeserializer);
    }

    @Bean
    public ConsumerFactory<String, Offer> offerConsumerFactory() {
        JsonDeserializer<Offer> payloadJsonDeserializer = new JsonDeserializer<>();
        payloadJsonDeserializer.addTrustedPackages("*");
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "offer-email-consumer");
        //props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, User.class);
        //props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), payloadJsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Offer> offerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Offer> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(offerConsumerFactory());
        return factory;
    }
}