package ru.geekbrains.service;

import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAll();

    Optional<ProductDto> findById(Long id);
}
