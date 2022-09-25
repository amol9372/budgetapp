package org.budget.tracker.budgetapp.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.budget.tracker.budgetapp.app.Expense;
import org.budget.tracker.budgetapp.builder.ExpenseBuilder;
import org.budget.tracker.budgetapp.config.KafkaConfig;
import org.budget.tracker.budgetapp.config.KafkaJsonSerializer;
import org.budget.tracker.budgetapp.db.JExpense;
import org.budget.tracker.budgetapp.db.JGroup;
import org.budget.tracker.budgetapp.repository.ExpenseJpaRepository;
import org.budget.tracker.budgetapp.repository.GroupJpaRepository;
import org.budget.tracker.budgetapp.rest.request.CreateExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    GroupJpaRepository groupJpaRepository;

    @Autowired
    ExpenseJpaRepository expenseJpaRepository;

//    @Autowired
//    KafkaProducer<String, JExpense> producer;

    @Override
    public void createExpense(CreateExpenseRequest request) {
        // Fetch group
        JGroup jGroup = null;
        if (StringUtils.hasLength(request.getGroup())) {
            jGroup = groupJpaRepository.findByName(request.getGroup());
        }

        JExpense expense = ExpenseBuilder.with(request, jGroup);
        JExpense savedExpense = expenseJpaRepository.save(expense);

        // push data in kafka -> elasticsearch
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());
        try (KafkaProducer<String, CreateExpenseRequest> kafkaProducer = new KafkaProducer<>(props)) {
            ProducerRecord<String, CreateExpenseRequest> record = new ProducerRecord<>("my-topic", String.valueOf(savedExpense.getId()) ,request );
            kafkaProducer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.println(metadata.partition());
                    System.out.println(metadata.topic());
                } else {
                    System.out.println(exception.getMessage());
                }
            });
        }
    }

    @Override
    public List<Expense> getExpenses() {

        // fetch expenses from Index
        return null;
    }


}
