package ru.geekbrains.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.model.Picture;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PictureDto implements Serializable {

    private Long id;

    private String name;

    private String contentType;

    public PictureDto(Picture picture) {
        this.id = picture.getId();
        this.name = picture.getName();
        this.contentType = picture.getContentType();
    }
}
