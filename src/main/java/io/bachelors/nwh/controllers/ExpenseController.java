package io.bachelors.nwh.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.bachelors.nwh.entity.Expense;
import io.bachelors.nwh.repository.ExpenseRepo;
import io.bachelors.nwh.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    @Autowired
    private ExpenseRepo expenseRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping()
    public Map<String, Object> createExpenseHandler(@RequestBody Expense expense) {
        String userId = getUserId();

        expense.setUserId(userId);
        expenseRepo.save(expense);

        return Collections.singletonMap("message", "Expense created");
    }

    @GetMapping()
    public Map<String, Object> getExpensesByUserIdHandler(@RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        String userId = getUserId();

        if (startDate == null || endDate == null) {
            return Collections.singletonMap("expenses", expenseRepo.findByUserId(userId));
        } else {
            return Collections.singletonMap("expenses",
                    expenseRepo.findByUserIdAndDateBetween(userId, startDate, endDate));
        }
    }

    @GetMapping("/month")
    public Map<String, Object> getExpensesByUserIdAndMonthHandler() {
        String userId = getUserId();
        String startDate = LocalDate.now().withDayOfMonth(1).toString();
        String endDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).toString();

        return Collections.singletonMap("expenses", expenseRepo.findByUserIdAndDateBetween(userId, startDate, endDate));
    }

    @DeleteMapping()
    public Map<String, Object> deleteExpenseHandler(@RequestParam(required = true) String id) {
        expenseRepo.deleteById(id);

        return Collections.singletonMap("message", "Expense deleted");
    }

    private String getUserId() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepo.findByEmail(email).get().getId();
    }

}
