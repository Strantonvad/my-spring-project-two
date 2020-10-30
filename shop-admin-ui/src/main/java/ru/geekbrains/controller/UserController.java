package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controller.dto.UserDto;
import ru.geekbrains.repo.RoleRepository;
import ru.geekbrains.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final RoleRepository roleRepository;

    private final UserService userService;

    @Autowired
    public UserController(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleRepository.findAll());
        return "user";
    }

    @GetMapping("/user/create")
    public String createUser(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roleRepository.findAll());
        return "user";
    }

    @PostMapping("/user")
    public String updateUser(@Valid UserDto user,
                             Model model,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Users");

        if (bindingResult.hasErrors()) {
            return "user";
        }

        try {
            userService.save(user);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating user", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (user.getId() == null) {
                return "redirect:/user/create";
            }
            return "redirect:/user/" + user.getId() + "/edit";
        }
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Users");
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/roles")
    public String rolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        return "index";
    }
}
