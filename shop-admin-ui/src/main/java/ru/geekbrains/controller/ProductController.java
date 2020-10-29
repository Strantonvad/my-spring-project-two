package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.model.Product;
import ru.geekbrains.repo.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    public String productPage(Model model) {
        List<Product> allProducts = productRepository.findAll();
        model.addAttribute("products", allProducts);

        return "products";
    }
}
