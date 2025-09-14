package guru.qa.photocatalog.service;

import guru.qa.photocatalog.data.PhotoRepository;
import guru.qa.photocatalog.domain.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
                    photoEntity.getContent() != null ? new String(photoEntity.getContent()): ""
            )
        ).toList();
    }

    @Override
    public Photo photoByDescription(String description) {
        return null;
    }
}
