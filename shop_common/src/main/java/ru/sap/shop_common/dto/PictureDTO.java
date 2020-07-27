package ru.sap.shop_common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.sap.database.model.Picture;

@EqualsAndHashCode
@Getter
@Setter
public class PictureDTO {
    private Long id;

    public PictureDTO() {
    }

    public PictureDTO(Picture picture) {
        this.id = picture.getId();
    }
}
