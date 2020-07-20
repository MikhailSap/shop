package ru.sap.service;

import org.springframework.stereotype.Service;
import ru.sap.database.model.Picture;
import ru.sap.database.repo.PictureRepo;

import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService{
    private PictureRepo pictureRepo;

    public PictureServiceImpl(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    @Override
    public Optional<Picture> getPictureById(Long id) {
        return pictureRepo.findById(id);
    }
}
