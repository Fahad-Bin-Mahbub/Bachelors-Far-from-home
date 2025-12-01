package io.bachelors.nwh.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bachelors.nwh.entity.Mess;
import io.bachelors.nwh.entity.User;
import io.bachelors.nwh.repository.MessRepo;
import io.bachelors.nwh.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/mess")
public class MessController {

    @Autowired
    private MessRepo messRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping()
    public Map<String, Object> createMessHandler(@RequestBody Mess mess) {
        User user = getUser();

        mess.getMembers().add(user.getId());
        messRepo.save(mess);

        user.setMessId(mess.getId());
        userRepo.save(user);

        return Collections.singletonMap("message", "Mess created");
    }

    @PostMapping("/join/{id}")
    public Map<String, Object> joinMessHandler(@PathVariable String id) {
        User user = getUser();

        Mess mess = messRepo.findById(id).get();
        mess.getMembers().add(user.getId());
        messRepo.save(mess);

        user.setMessId(mess.getId());
        userRepo.save(user);

        return Collections.singletonMap("message", "Mess joined");
    }

    @PostMapping("/leave")
    public Map<String, Object> leaveMessHandler() {
        User user = getUser();

        Mess mess = messRepo.findById(user.getMessId()).get();
        mess.getMembers().remove(user.getId());
        messRepo.save(mess);

        user.setMessId(null);
        userRepo.save(user);

        return Collections.singletonMap("message", "Mess left");
    }

    @GetMapping()
    public Map<String, Object> getMessByIdHandler() {
        User user = getUser();

        return Collections.singletonMap("mess", messRepo.findById(user.getMessId()).get());
    }

    private User getUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepo.findByEmail(email).get();
    }
}
