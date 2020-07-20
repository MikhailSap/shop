package ru.sap.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sap.database.model.Picture;

@Repository
public interface PictureRepo extends JpaRepository<Picture, Long> {
}
