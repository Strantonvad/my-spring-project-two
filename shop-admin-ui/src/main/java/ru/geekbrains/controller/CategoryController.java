package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.model.Category;
import ru.geekbrains.repo.CategoryRepository;

@Controller
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public String categoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @GetMapping("/category/create")
    public String createCategory(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new Category());
        return "category";
    }

    @GetMapping("/category/{id}/edit")
    public String editCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryRepository.findById(id).orElseThrow(NotFoundException::new));
        return "category";
    }

    @DeleteMapping("/category/{id}/delete")
    public String deleteCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Categories");
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }

    @PostMapping("/category")
    public String updateCategory(Model model, RedirectAttributes redirectAttributes, Category category) {
        model.addAttribute("activePage", "Categories");

        try {
            categoryRepository.save(category);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating category: ", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (category.getId() == null) {
                return "redirect:/category/create";
            }
            return "redirect:/category/" + category.getId() + "/edit";
        }
        return "redirect:/categories";
    }
}
