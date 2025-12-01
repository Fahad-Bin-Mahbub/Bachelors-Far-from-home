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

import io.bachelors.nwh.entity.Earning;
import io.bachelors.nwh.repository.EarningRepo;
import io.bachelors.nwh.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/earning")
public class EarningController {

    @Autowired
    private EarningRepo earningRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping()
    public Map<String, Object> createEarningHandler(@RequestBody Earning earning) {
        String userId = getUserId();

        earning.setUserId(userId);
        earningRepo.save(earning);

        return Collections.singletonMap("message", "Earning created");
    }

    @GetMapping()
    public Map<String, Object> getEarningsByUserIdHandler(@RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        String userId = getUserId();

        if (startDate == null || endDate == null) {
            return Collections.singletonMap("expenses", earningRepo.findByUserId(userId));
        } else {
            return Collections.singletonMap("expenses",
                    earningRepo.findByUserIdAndDateBetween(userId, startDate, endDate));
        }
    }

    @GetMapping("/month")
    public Map<String, Object> getEarningsByUserIdAndMonthHandler() {
        String userId = getUserId();
        String startDate = LocalDate.now().withDayOfMonth(1).toString();
        String endDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).toString();

        return Collections.singletonMap("expenses", earningRepo.findByUserIdAndDateBetween(userId, startDate, endDate));
    }

    @DeleteMapping()
    public Map<String, Object> deleteEarningHandler(@RequestParam(required = true) String earningId) {
        earningRepo.deleteById(earningId);

        return Collections.singletonMap("message", "Earning deleted");
    }

    private String getUserId() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepo.findByEmail(email).get().getId();
    }

}
