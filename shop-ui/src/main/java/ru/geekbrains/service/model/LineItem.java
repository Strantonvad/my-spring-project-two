package ru.geekbrains.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.controller.dto.ProductDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
public class LineItem implements Serializable {

    private Long productId;

    private ProductDto productDto;

    private Integer qty;

    private String color;

    private String size;

    public LineItem(ProductDto productDto, String color, String size) {
        this.productId = productDto.getId();
        this.productDto = productDto;
        this.color = color;
        this.size = size;
    }

    @JsonIgnore
    public BigDecimal getTotal() {
        return productDto.getCost().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return productId.equals(lineItem.productId) &&
            Objects.equals(emptyToNull(color), emptyToNull(lineItem.color)) &&
            Objects.equals(emptyToNull(size), emptyToNull(lineItem.size));
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, color, size);
    }

    private static String emptyToNull(String val) {
        return val == null ? "" : val;
    }
}