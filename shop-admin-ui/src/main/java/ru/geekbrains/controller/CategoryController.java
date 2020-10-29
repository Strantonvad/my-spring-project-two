package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.model.Category;
import ru.geekbrains.repo.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    public String categoryPage(Model model) {
        List<Category> allCategories = categoryRepository.findAll();
        model.addAttribute("categories", allCategories);

        return "categories";
    }
}
