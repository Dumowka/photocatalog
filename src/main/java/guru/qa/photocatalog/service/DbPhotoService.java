package guru.qa.photocatalog.service;

import guru.qa.photocatalog.data.PhotoRepository;
import guru.qa.photocatalog.domain.Photo;
import guru.qa.photocatalog.ex.PhotoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class DbPhotoService implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public DbPhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public List<Photo> allPhotos() {
        return photoRepository.findAll().stream().map(photoEntity ->
                new Photo(
                        photoEntity.getDescription(),
                        photoEntity.getLastModifyDate(),
                        photoEntity.getContent() != null ? new String(photoEntity.getContent()) : ""
                )
        ).toList();
    }

    @Override
    public Photo photoByDescription(String description) {
        return null;
    }

    @Override
    public Photo byId(String id) {
        return photoRepository.findById(UUID.fromString(id))
                .map(photoEntity ->
                        new Photo(
                                photoEntity.getDescription(),
                                photoEntity.getLastModifyDate(),
                                photoEntity.getContent() != null ? new String(photoEntity.getContent()) : ""
                        )
                ).orElseThrow(PhotoNotFoundException::new);
    }
}
