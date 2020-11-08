package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.model.Picture;
import ru.geekbrains.model.PictureData;
import ru.geekbrains.model.Product;
import ru.geekbrains.repo.ProductRepository;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
            .map(ProductDto::new)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }
}
