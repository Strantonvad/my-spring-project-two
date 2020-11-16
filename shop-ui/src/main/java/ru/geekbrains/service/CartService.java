package ru.geekbrains.service;

import ru.geekbrains.service.model.LineItem;
import ru.geekbrains.controller.dto.ProductDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface CartService extends Serializable {

    void addProductQty(ProductDto productDto, String color, String material, int qty);

    void removeProductQty(ProductDto productDto, String color, String material, int qty);

    void removeProduct(LineItem lineItem);

    List<LineItem> getLineItems();

    BigDecimal getSubTotal();

    void updateCart(LineItem lineItem);
}
