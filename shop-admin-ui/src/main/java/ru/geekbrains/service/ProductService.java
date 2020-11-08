package ru.geekbrains.service;

import ru.geekbrains.controller.dto.ProductDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(ProductDto productDto) throws IOException;

    List<ProductDto> findAll();

    Optional<ProductDto> findById(Long id);

    void deleteById(Long id);

}
