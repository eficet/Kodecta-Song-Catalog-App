package media.app.controller;

import media.app.helpers.HttpHelper;
import media.app.helpers.SongMappingHelper;
import media.app.helpers.StringHelper;
import media.app.model.*;
import media.app.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import media.app.helpers.Helper;

@CrossOrigin
@RequestMapping(value = "/api")
@RestController
public class SongController {

    @Autowired
    private SongRepository songRepository;


//                            ***************** Methods for getting Songs ***************

    /**
     * This function returns list of  all songs.
     *
     * @return the list of all songs
     */
    @GetMapping(value = "/songs")
    public ResponseEntity<?> getAll() {
        try {
            List<Song> songList = songRepository.findAll();
            if (!songList.isEmpty())


                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);

            return HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_IN_DATABASE, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This function retrieves one song by its ID.
     *
     * @param id the id of the song
     * @return the song object
     */
    @GetMapping(value = "/songs/{id}")
    public ResponseEntity<?> getSongById(@PathVariable Long id) {
        try {
            Optional<Song> song = songRepository.findById(id);
            song.orElseThrow(() -> new RuntimeException("The song doesnt exist"));
            return new ResponseEntity<>(song, HttpStatus.OK);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    //                            ******************* Searching methods *******************

    /**
     * This function Searches for songs By Artist Name
     *
     * @param name Artist Name
     * @return List of songs
     */
    @GetMapping(value = "/songs/artist/{name}")
    public ResponseEntity<?> getByArtistName(@PathVariable String name) {
        try {
            if (!Helper.isNullOrEmpty(name)) {
                List<Song> songList = songRepository.findByArtistNameIgnoreCase(name);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_BY_NAME_IN_DATABASE, HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            } else
                return HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function searches for songs by Genre
     *
     * @param genre genre of the song
     * @return List of the selected songs
     */
    @GetMapping(value = "/songs/genre/{genre}")
    public ResponseEntity<?> getByGenre(@PathVariable String genre) {
        try {
            if (!Helper.isNullOrEmpty(genre)) {
                List<Song> songList = songRepository.findByGenreIgnoreCase(genre);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_BY_GENRE_IN_DATABASE, HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            }
            return new ResponseEntity<>(StringHelper.FIELD_NAME_NULL_OR_EMPTY, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function searches for songs by Publisher name
     *
     * @param provider provider nasm
     * @return List of songs
     */
    @GetMapping(value = "/songs/provider/{provider}")
    public ResponseEntity<?> getByPublisher(@PathVariable String provider) {
        try {
            if (!Helper.isNullOrEmpty(provider)) {
                List<Song> songList = songRepository.findByProviderProviderNameIgnoreCase(provider);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_BY_NAME_IN_DATABASE, HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            } else
                return HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    /**
     * This function search for songs by songName
     *
     * @param name Song name
     * @return List of Songs
     */
    @GetMapping(value = "/songs/name/{name}")
    public ResponseEntity<?> getBySongName(@PathVariable String name) {
        try {
            if (!Helper.isNullOrEmpty(name)) {
                List<Song> songList = songRepository.findBySongNameIgnoreCase(name);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_BY_NAME_IN_DATABASE, HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            } else
                return HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function searches for songs by Artist Country
     *
     * @param country Country Name
     * @return List of songs
     */
    @GetMapping("/songs/country/{country}")
    public ResponseEntity<?> getByCountry(@PathVariable String country) {
        try {
            if (!Helper.isNullOrEmpty(country)) {
                List<Song> songList = songRepository.findByArtistCountry(country);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_BY_NAME_IN_DATABASE, HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            } else
                return HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    //                             *************** Adding and Updating methods ***********

    /**
     * This function is Adding a new Song
     *
     * @param songMappingHelper from the request body songMappingHelper Object
     * @return The new added Object
     */
    @PostMapping(value = "/songs")
    public ResponseEntity<?> createSong(@RequestBody SongMappingHelper songMappingHelper) {

        try {

            if (!Helper.isNullOrEmpty(songMappingHelper)) {
                //setting the values of song helper to Song object to be saved to the database
                Song song = new Song(songMappingHelper.getSongId(), songMappingHelper.getSongName(), songMappingHelper.getGenre(), songMappingHelper.getArtistId());

                //mapping artistId as foriegn key
                //I could also add providerId as a parameter in the constructor of Song but i wanted to try another way without it
                song.setProvider(new Provider(songMappingHelper.getProviderId(), ""));

                // setting the date and time to the current time
                song.setCreatedAt(LocalDateTime.now());
                song.setPublishingDate(songMappingHelper.getPublishingDate());
                songRepository.save(song);
                return HttpHelper.getHttpResponseEntity(song, HttpStatus.CREATED);
            } else
                return HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param songMappingHelper
     * @param id                The id of the song to be changed
     * @return Returns HttpStatus Created
     */
    @PutMapping(value = "/songs/{id}")
    public ResponseEntity<?> updateSong(@RequestBody SongMappingHelper songMappingHelper, @PathVariable Long id) {

        try {
            Song song1 = songRepository.getOne(id);
            song1.setArtist(new Artist(songMappingHelper.getArtistId(), "", 0, ""));
            song1.setProvider(new Provider(songMappingHelper.getProviderId(), ""));
            song1.setSongName(songMappingHelper.getSongName());
            song1.setGenre(songMappingHelper.getGenre());
            song1.setCreatedAt(songMappingHelper.getCreatedAt());
            song1.setPublishingDate(songMappingHelper.getPublishingDate());
            songRepository.save(song1);
            return HttpHelper.getHttpResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //                                    ******************* Deleting Song method *****************

    /**
     * This function Deletes a song
     *
     * @param id the ID of the song
     * @return HttpStatus NO_CONTENT
     */
    @DeleteMapping(value = "/songs/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        try {
            songRepository.deleteById(id);
            return HttpHelper.getHttpResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
