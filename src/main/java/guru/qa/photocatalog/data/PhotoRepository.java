package guru.qa.photocatalog.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface PhotoRepository extends JpaRepository<PhotoEntity, UUID> {
}
