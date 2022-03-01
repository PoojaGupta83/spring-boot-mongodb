package com.programmingexpense.springbootmongodb.controller;

import com.programmingexpense.springbootmongodb.model.Expense;
import com.programmingexpense.springbootmongodb.service.ExpenseService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Autowired
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense, @PathVariable("id") String id) {
        expenseService.updateExpense(expense, id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAllExpense() {
        return ResponseEntity.ok(expenseService.getAllExpense());
    }

    @GetMapping("/{name}")
    public ResponseEntity getExpenseByName(@PathVariable String name) {
        return ResponseEntity.ok(expenseService.getExpenseByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


//    @GetMapping("/stats")
//    public List<Document> stats(@RequestParam long duration) {
//        return meterService.getStats(duration);
//    }
//
//    @GetMapping("/counters")
//    public HashMap<String, Object> counters() {
//        return meterService.getCounters();
//    }
//
//    @GetMapping("/meters")
//    public List<Document> getMeterList(@RequestParam int offset, @RequestParam int max) {
//        return meterService.getMeterList(offset, max);
//    }
//
//    @GetMapping("/meters/{deviceId}")
//    public Document getMeterById(@PathVariable String deviceId) {
//        return meterService.getMeterById(deviceId);
//    }
//
////    @GetMapping("/alarms")
////    public List<Document> getAlarms() {
////        return meterService.getAlarms();
////    }
//
//    @GetMapping("/meterDetails/{deviceId}")
//    public List<Document> getMeterDetailsById(@PathVariable("deviceId") String deviceId, @RequestParam long duration) {
//        return meterService.getMeterByDeviceId(deviceId, duration);
//    }

}