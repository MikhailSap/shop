package ru.sap.service;

import ru.sap.database.model.Picture;

import java.util.Optional;

public interface PictureService {
    Optional<Picture> getPictureById(Long id);
}
