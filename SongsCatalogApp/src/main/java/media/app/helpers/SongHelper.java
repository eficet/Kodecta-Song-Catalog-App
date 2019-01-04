package media.app.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SongHelper {
    private Long _songId;
    private String _songName;
    private String _genre;
    private Long _artistId;
    private Long _providerId;
    private LocalDate _publishingDate;
    private LocalDateTime _createdAt;

    public Long get_songId() {
        return _songId;
    }

    public void set_songId(Long _songId) {
        this._songId = _songId;
    }

    public String get_songName() {
        return _songName;
    }

    public void set_songName(String _songName) {
        this._songName = _songName;
    }

    public String get_genre() {
        return _genre;
    }

    public void set_genre(String _genre) {
        this._genre = _genre;
    }

    public Long get_artistId() {
        return _artistId;
    }

    public void set_artistId(Long _artistId) {
        this._artistId = _artistId;
    }

    public Long get_providerId() {
        return _providerId;
    }

    public void set_providerId(Long _providerId) {
        this._providerId = _providerId;
    }

    public LocalDate get_publishingDate() {
        return _publishingDate;
    }

    public void set_publishingDate(LocalDate _publishingDate) {
        this._publishingDate = _publishingDate;
    }

    public LocalDateTime get_createdAt() {
        return _createdAt;
    }

    public void set_createdAt(LocalDateTime _createdAt) {
        this._createdAt = _createdAt;
    }
}
