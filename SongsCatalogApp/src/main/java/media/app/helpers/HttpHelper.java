package media.app.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpHelper {

    public static ResponseEntity<?> getHttpResponseEntity (String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
