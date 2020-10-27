package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.model.Brand;
import ru.geekbrains.model.Product;
import ru.geekbrains.persist.repo.BrandRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/brands")
public class BrandController {

    @Autowired
    BrandRepository brandRepository;

    public String brandPage(Model model) {
        List<Brand> allBrands = brandRepository.findAll();
        model.addAttribute("brands", allBrands);

        return "brands";
    }
}
