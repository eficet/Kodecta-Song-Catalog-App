package media.app.helpers;

import media.app.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class HttpHelper {

    public static ResponseEntity<?> getHttpResponseEntity (HttpStatus status) {
        return new ResponseEntity<>(status);
    }

    public static ResponseEntity<?> getHttpResponseEntity (String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }

    public static ResponseEntity<?> getHttpResponseEntity (Song song, HttpStatus status) {
        return new ResponseEntity<>(song, status);
    }
    public static ResponseEntity<?> getHttpResponseEntity (List<Song> songList, HttpStatus status) {
        return new ResponseEntity<>(songList, status);
    }
}
