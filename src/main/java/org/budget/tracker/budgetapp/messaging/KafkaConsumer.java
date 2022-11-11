package org.budget.tracker.budgetapp.messaging;

import org.budget.tracker.budgetapp.messaging.domain.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

  private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "my-topic", groupId = "consumer-group-1")
  public void consume(Expense expense, @Header(KafkaHeaders.OFFSET) String offset) {

    // log.info("Incoming expense event ::: {}", record.value());
    // Expense expense = KafkaJsonSerializer.getCustomConfigMapper().convertValue(record.value(),
    // Expense.class);

    log.info("Incoming expense event ::: {}", expense.toString());
  }
}
