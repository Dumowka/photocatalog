package guru.qa.photocatalog.service;

import guru.qa.photocatalog.domain.Photo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class InMemoryPhotoService implements PhotoService {
    @Override
    public List<Photo> allPhotos() {
        return List.of(
                new Photo(
                        "Фото 1", new Date(), ""
                ),
                new Photo(
                        "Фото 2", new Date(), ""
                )
        );
    }

    @Override
    public Photo photoByDescription(String description) {
        return null;
    }
}
