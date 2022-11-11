package org.budget.tracker.budgetapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.budget.tracker.budgetapp.messaging.domain.Expense;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
class ExpenseDeserializer implements Deserializer<Expense> {

    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Expense deserialize(String topic, byte[] data) {
        ObjectMapper mapper = KafkaJsonSerializer.getCustomConfigMapper();

        Expense expense;
        try {
            expense = mapper.readValue(data, Expense.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return expense;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}