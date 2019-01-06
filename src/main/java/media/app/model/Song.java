package media.app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Song {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long songId;
    private String songName;
    private String genre;
    private LocalDateTime createdAt;
    private LocalDate publishingDate;

    //allows related data to get updated
    @ManyToOne
    @JoinColumn(name = "artist_Id",referencedColumnName = "artistId")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "provider_Id",referencedColumnName = "providerId")
    private Provider provider;

    public Song(){

    }

    public Song(Long songId, String songName, String genre, Long artistId) {
        this.songId = songId;
        this.songName = songName;
        this.genre = genre;
        this.artist = new Artist(artistId,"",0,"");
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }
}
