package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.model.User;
import ru.geekbrains.persist.repo.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String adminPage(Model model, @RequestParam(value = "name", required = false) String name) {

        List<User> allUsers;
        if (name == null || name.isEmpty()) {
            allUsers = userRepository.findAll();
        } else {
            allUsers = userRepository.findByNameLike("%" + name + "%");
        }
        model.addAttribute("users", allUsers);
        return "users";
    }
}
