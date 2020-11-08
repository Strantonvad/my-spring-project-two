package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.repo.BrandRepository;
import ru.geekbrains.repo.CategoryRepository;
import ru.geekbrains.service.ProductService;

import java.util.List;

@Controller
public class MainController {

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    private final ProductService productService;

    @Autowired
    public MainController(CategoryRepository categoryRepository,
                          BrandRepository brandRepository,
                          ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productService = productService;
    }

    @RequestMapping("/")
    public String indexPage(Model model) {
        List<ProductDto> productList = productService.findAll();

        model.addAttribute("activePage", "None");
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());

        productList.forEach(productDto -> {
            productDto.hasPicture = productDto.getPictures() != null;
        });

        model.addAttribute("products", productList);

        return "index";
    }
}
