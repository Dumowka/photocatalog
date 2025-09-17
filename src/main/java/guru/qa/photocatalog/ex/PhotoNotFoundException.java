package guru.qa.photocatalog.ex;

import guru.qa.photocatalog.domain.Photo;

public class PhotoNotFoundException extends RuntimeException {
    public PhotoNotFoundException() {}

    public PhotoNotFoundException(String message) {
        super(message);
    }
}
