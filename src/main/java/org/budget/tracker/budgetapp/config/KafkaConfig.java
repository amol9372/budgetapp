package org.budget.tracker.budgetapp.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.budget.tracker.budgetapp.messaging.domain.Expense;
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
public class KafkaConfig {

  @Value("${kafka.bootstrap}")
  private String bootstrapServers;

  @Value("${kafka.user}")
  private String user;

  @Value("${kafka.password}")
  private String password;

  @Bean
  public ConsumerFactory<String, Expense> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group-1");
    //    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    //    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 5000);
    props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 5000);

    props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
    props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
    props.put(
        SaslConfigs.SASL_JAAS_CONFIG,
        "org.apache.kafka.common.security.plain.PlainLoginModule required "
            + "username='"
            + user
            + "'"
            + " password='"
            + password
            + "';");
    return new DefaultKafkaConsumerFactory<>(
        props, new StringDeserializer(), new JsonDeserializer<>(Expense.class));
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
