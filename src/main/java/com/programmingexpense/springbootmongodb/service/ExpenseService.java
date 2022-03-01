package com.programmingexpense.springbootmongodb.service;

import com.programmingexpense.springbootmongodb.handler.exception.ValidationException;
import com.programmingexpense.springbootmongodb.handler.response.ApiResponseCodeImpl;
import com.programmingexpense.springbootmongodb.model.Expense;
import com.programmingexpense.springbootmongodb.mongo.constants.HttpStatus;
import com.programmingexpense.springbootmongodb.mongo.service.MongoService;
import com.programmingexpense.springbootmongodb.utils.ValidationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.programmingexpense.springbootmongodb.constants.ApplicationConstants.*;
import static com.programmingexpense.springbootmongodb.constants.CommonConstants.ZERO;

@Service
public class ExpenseService {
    private static final Logger LOG = LogManager.getLogger();
    @Autowired
    private MongoService mongoService;

    public Document addExpense(Expense expense) {
        try {
            Document document = new Document();
            document.put("Name", expense.getExpenseName());
            document.put("Category", expense.getExpenseCategory());
            document.put("Amount", expense.getExpenseAmount());
            mongoService.create(EXPENSE, document);
            return new Document(_ID, expense);
        } catch (Exception e) {
            LOG.error("error in create expense : ", e);
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), " Unable to create video Analysis, please try after sometime");
        }

    }

    public Document updateExpense(Expense expense, String id) {
        Document matchQuery = new Document();
        matchQuery.put(ID, id);
        Document expenses = mongoService.findById(EXPENSE, id, new Document(_ID, 0));

        if (ValidationUtils.isNullOrEmpty(expenses)) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), ApiResponseCodeImpl.VIDEO_ANALYTICS_DOES_NOT_EXIST_WITH_THIS_ID.getMessage());
        }
        try {
            expenses.put("Id", expense.getId());
            expenses.put("ExpenseName", expense.getExpenseName());
            expenses.put("Category", expense.getExpenseCategory());
            expenses.put("Amount", expense.getExpenseAmount());

            mongoService.update(EXPENSE, matchQuery, expenses, null, null, null, false);
            return new Document("id", id);
        } catch (Exception e) {
            LOG.error("error in update expense : ", e);
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), " Unable to update expense, please try after sometime");
        }
    }

    public List<Document> getAllExpense() {
        try {
            return mongoService.findAll(EXPENSE, new Document());
        } catch (Exception e) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), ApiResponseCodeImpl.EXPENSE_DOES_NOT_WITH_THIS_ID.getMessage());
        }
    }

    public List getExpenseByName(String name) {
        try {
            Document projection = new Document();
            projection.put(ID, name);
            Document matchQuery = new Document();
            matchQuery.put(ID, ZERO);
            return mongoService.findList(EXPENSE, matchQuery, projection);
        } catch (Exception e) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), ApiResponseCodeImpl.INVALID_PATH.name());
        }
    }

    public void deleteExpense(String id) {
        if (ValidationUtils.isNullOrEmpty(mongoService.findById(EXPENSE, String.valueOf(new Document(ID, id)), new Document(_ID, ZERO)))) {
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), ApiResponseCodeImpl.VIDEO_ANALYTICS_DOES_NOT_EXIST_WITH_THIS_ID.getMessage());
        }
        try {
            mongoService.deleteById(EXPENSE, id);
        } catch (Exception e) {
            LOG.error("error in delete video Analytics : ", e);
            throw new ValidationException(HttpStatus.BAD_REQUEST.value(), " Unable to delete video Analytics, please try after sometime");
        }

    }

}
