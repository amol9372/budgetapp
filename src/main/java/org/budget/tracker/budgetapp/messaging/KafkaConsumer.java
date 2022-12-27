package org.budget.tracker.budgetapp.messaging;

import org.apache.logging.log4j.LogManager;
import org.budget.tracker.budgetapp.db.JBudget;
import org.budget.tracker.budgetapp.db.JCategoryBudget;
import org.budget.tracker.budgetapp.messaging.domain.Expense;
import org.budget.tracker.budgetapp.repository.BudgetJpaRepository;
import org.budget.tracker.budgetapp.repository.CategoryBudgetJpaRepository;
import org.budget.tracker.budgetapp.repository.CategoryJpaRepository;
import org.budget.tracker.budgetapp.repository.UserCategoryJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {

  private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(this.getClass());

  @Autowired
  CategoryBudgetJpaRepository categoryBudgetJpaRepository;

  @Autowired
  CategoryJpaRepository categoryJpaRepository;

  @Autowired
  UserCategoryJpaRepository userCategoryJpaRepository;

  @Autowired
  BudgetJpaRepository budgetJpaRepository;


  private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "my-topic", groupId = "consumer-group-1")
  public void consume(Expense expense, @Header(KafkaHeaders.OFFSET) String offset) {

    log.info("Incoming expense event ::: {}", expense.toString());
    updateBudgetCategory(expense);
  }

  public void updateBudgetCategory(Expense expense){

    log.info("Updating Budget for Category ::: {}", expense.getCategory());

    JCategoryBudget jCategoryBudget = categoryBudgetJpaRepository.findById(expense.getCategoryId()).get();
    var used = jCategoryBudget.getUsed() - expense.getCost().doubleValue();
    // jCategoryBudget.setAllocated(expense.getAllocated().doubleValue());
    jCategoryBudget.setUsed(used);
    categoryBudgetJpaRepository.save(jCategoryBudget);

//    Optional<JBudget> jBudget = budgetJpaRepository.findById(expense.getBudgetId());
//    jBudget.ifPresent(
//            budget -> {
//              budget.setMoneyAvailable(budget.getMoneyAvailable() - jCategoryBudget.getAllocated());
//              budgetJpaRepository.save(budget);
//            });

  }
}
