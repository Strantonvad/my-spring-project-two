package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.model.Picture;
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

    private final PictureService pictureService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PictureService pictureService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
    }

    @Override
    @Transactional
    public void save(ProductDto productDto) throws IOException {
        Product product = (productDto.getId() != null) ? productRepository.findById(productDto.getId())
            .orElseThrow(NotFoundException::new) : new Product();
        product.setTitle(productDto.getTitle());
        product.setCategory(productDto.getCategory());
        product.setBrand(productDto.getBrand());
        product.setCost(productDto.getCost());
        if (productDto.getNewPictures() != null) {
            for (MultipartFile newPicture : productDto.getNewPictures()) {
                logger.info("Product: {}, file: {}, size: {}.", product.getId(),
                    newPicture.getOriginalFilename(), newPicture.getSize());

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(
                    newPicture.getOriginalFilename(),
                    newPicture.getContentType(),
                    pictureService.createPictureData(newPicture.getBytes())));
            }
        }
        productRepository.save(product);
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

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
