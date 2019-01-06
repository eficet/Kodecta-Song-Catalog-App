package media.app.controller;

import media.app.helpers.HttpHelper;
import media.app.model.Artist;
import media.app.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    /**
     * This Function Returns all Artist
     *
     * @return List of Artists
     */
    @GetMapping(value = "/artist")
    public ResponseEntity<?> getArtists() {
        try {
            List<Artist> data = artistRepository.findAll();
            return HttpHelper.getHttpResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    /**
     * This function returns Artist by his ID
     *
     * @param id
     * @return provider object
     */
    @GetMapping(value = "/artist/{id}")
    public ResponseEntity<?> getArtistById(@PathVariable Long id) {
        try {
            Optional<Artist> artist = artistRepository.findById(id);
            return HttpHelper.getHttpResponseEntity(artist, HttpStatus.OK);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function Adds new Artist
     *
     * @param artist Atrist object from request body
     * @return the added artist
     */
    @PostMapping(value = "/artist")
    public ResponseEntity<?> postArtist(@RequestBody Artist artist) {
        try {
            artistRepository.save(artist);
            return HttpHelper.getHttpResponseEntity(artist, HttpStatus.CREATED);
        } catch (Exception e) {

            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * This Function updates the Artist in the database
     *
     * @param id     id of artist
     * @param artist Artist object
     * @return Updated Object
     */

    @PutMapping(value = "/artist/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable Long id, @RequestBody Artist artist) {
        try {
            Artist myArtist = artistRepository.getOne(id);
            myArtist.setName(artist.getName());
            myArtist.setCountry(artist.getCountry());
            myArtist.setAge(artist.getAge());
            artistRepository.save(myArtist);

            return HttpHelper.getHttpResponseEntity(myArtist, HttpStatus.OK);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}
