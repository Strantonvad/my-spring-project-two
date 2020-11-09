package ru.geekbrains.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.model.Brand;
import ru.geekbrains.model.Category;
import ru.geekbrains.model.Picture;
import ru.geekbrains.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long id;

    private String title;

    private BigDecimal cost;

    private Brand brand;

    private Category category;

    private List<Long> pictureIds;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.pictureIds = product.getPictures().stream()
            .map(Picture::getId).collect(Collectors.toList());
    }
}
