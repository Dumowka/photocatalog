package guru.qa.photocatalog.service;

import guru.qa.photocatalog.data.PhotoEntity;
import guru.qa.photocatalog.data.PhotoRepository;
import guru.qa.photocatalog.domain.Photo;
import guru.qa.photocatalog.domain.graphql.PhotoGql;
import guru.qa.photocatalog.domain.graphql.PhotoInputGql;
import guru.qa.photocatalog.ex.PhotoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

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
    public List<PhotoGql> allGqlPhotos() {
        return photoRepository.findAll().stream().map(photoEntity ->
                new PhotoGql(
                        photoEntity.getId(),
                        photoEntity.getDescription(),
                        photoEntity.getLastModifyDate(),
                        photoEntity.getContent() != null ? new String(photoEntity.getContent()) : ""
                )
        ).toList();
    }

    @Override
    public Page<PhotoGql> allGqlPhotos(Pageable pageable) {
        return photoRepository.findAll(pageable)
                .map(photoEntity ->
                        new PhotoGql(
                                photoEntity.getId(),
                                photoEntity.getDescription(),
                                photoEntity.getLastModifyDate(),
                                photoEntity.getContent() != null ? new String(photoEntity.getContent()) : ""
                        )
                );
    }

    @Override
    public Photo photoByDescription(String description) {
        return null;
    }

    @Override
    public Photo photoById(String id) {
        return photoRepository.findById(UUID.fromString(id))
                .map(photoEntity ->
                        new Photo(
                                photoEntity.getDescription(),
                                photoEntity.getLastModifyDate(),
                                photoEntity.getContent() != null ? new String(photoEntity.getContent()) : ""
                        )
                ).orElseThrow(PhotoNotFoundException::new);
    }

    @Override
    public PhotoGql photoGqlById(String id) {
        return photoRepository.findById(UUID.fromString(id))
                .map(photoEntity ->
                        new PhotoGql(
                                photoEntity.getId(),
                                photoEntity.getDescription(),
                                photoEntity.getLastModifyDate(),
                                photoEntity.getContent() != null ? new String(photoEntity.getContent()) : ""
                        )
                ).orElseThrow(PhotoNotFoundException::new);
    }

    @Override
    public Photo addPhoto(Photo photo) {
        return null;
    }

    @Override
    public PhotoGql addPhotoGql(PhotoInputGql photo) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setDescription(photo.description());
        photoEntity.setLastModifyDate(new Date());
        photoEntity.setContent(photo.content().getBytes());
        PhotoEntity saved = photoRepository.save(photoEntity);
        return new PhotoGql(
                saved.getId(),
                saved.getDescription(),
                saved.getLastModifyDate(),
                saved.getContent() != null ? new String(saved.getContent()) : ""
        );
    }
}
