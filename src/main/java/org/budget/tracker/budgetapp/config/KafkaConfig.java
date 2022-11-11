package org.budget.tracker.budgetapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.budget.tracker.budgetapp.messaging.domain.Expense;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

  @Bean
  public ConsumerFactory<String, Expense> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "3.90.91.129:9092");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group-1");
//    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 5000);
    props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 5000);
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Expense.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Expense> kafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, Expense> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.getContainerProperties().setIdleBetweenPolls(5000L);
    factory.setBatchListener(false);
    return factory;
  }


}
