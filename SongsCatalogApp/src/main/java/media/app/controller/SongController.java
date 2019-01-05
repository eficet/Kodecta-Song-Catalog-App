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

    private Helper helper= new Helper();

//                            ***************** Methods for getting Songs ***************

    /**
     * This function returns list of  all songs.
     * @return the list of all songs
     */
    @GetMapping(value = "/songs")
    public ResponseEntity<?> getAll(){
        try{
            List<Song> songList=songRepository.findAll();
            if(!songList.isEmpty())
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            return HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_IN_DATABASE, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return HttpHelper.getHttpResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This function retrieves one song by its ID.
     * @param id the id of the song
     * @return the song object
     */
    @GetMapping(value = "/songs/{id}")
    public ResponseEntity<?> getSongById(@PathVariable Long id){
        try {
            Optional<Song> song=songRepository.findById(id);
            song.orElseThrow(()->new RuntimeException("The song doesnt exist"));
            return new ResponseEntity<>(song,HttpStatus.OK);
        }
        catch (Exception e){
         return HttpHelper.getHttpResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    //                            ******************* Searching methods *******************

    // Search for songs By Artist Name
    @GetMapping(value = "/songs/artist/{name}")
    public ResponseEntity<?> getByArtistName(@PathVariable String name){
        try {
            if(!helper.isNullOrEmpty(name)) {
                List<Song> songList = songRepository.findByArtistNameIgnoreCase(name);
                if (songList.isEmpty())
                    return  HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_BY_ARTIST_NAME_IN_DATABASE,HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            }
            else return  HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // search for songs by Genre
    @GetMapping(value = "/songs/genre/{genre}")
    public ResponseEntity<?> getByGenre(@PathVariable String genre){
        try {
            if(!helper.isNullOrEmpty(genre)) {
                List<Song> songList = songRepository.findByGenreIgnoreCase(genre);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity(StringHelper.NO_SONGS_BY_GENRE_IN_DATABASE,HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            }
            return new ResponseEntity<>(StringHelper.FIELD_NAME_NULL_OR_EMPTY,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return  HttpHelper.getHttpResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // search for songs by Publisher name
    @GetMapping(value = "/songs/provider/{provider}")
    public ResponseEntity<?> getByPublisher(@PathVariable String provider){
        try {
            if(!helper.isNullOrEmpty(provider)) {
                List<Song> songList = songRepository.findByProviderProviderNameIgnoreCase(provider);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity("No Songs found with the given Artist Name",HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            }
            else return HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    // search for songs by songName
    @GetMapping(value = "/songs/name/{name}")
    public ResponseEntity<?> getBySongName(@PathVariable String name){
        try {
            if(!helper.isNullOrEmpty(name)) {
                List<Song> songList = songRepository.findBySongNameIgnoreCase(name);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity("No Songs found with the given Song Name",HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            }
            else return HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/songs/artist/country/{country}")
    public ResponseEntity<?> getByCountry(@PathVariable String country){
        try {
            if(!helper.isNullOrEmpty(country)) {
                List<Song> songList = songRepository.findByArtistCountry(country);
                if (songList.isEmpty())
                    return HttpHelper.getHttpResponseEntity("No Songs found with the given Country",HttpStatus.NOT_FOUND);
                return HttpHelper.getHttpResponseEntity(songList, HttpStatus.OK);
            }
            else return HttpHelper.getHttpResponseEntity(StringHelper.FIELD_NAME_NULL_OR_EMPTY,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    //                             *************** Adding and Updating methods ***********

    @PostMapping(value = "/songs")
    public ResponseEntity<?> createSong(@RequestBody SongMappingHelper songMappingHelper){

        try{

        if(!helper.isNullOrEmpty(songMappingHelper)){
        //setting the values of song helper to Song object to be saved to the database
        Song song = new Song(songMappingHelper.get_songId(), songMappingHelper.get_songName(), songMappingHelper.get_genre(), songMappingHelper.get_artistId());

        //mapping artistId as foriegn key
        //I could also add providerId as a parameter in the constructor of Song but i wanted to try another way without it
        song.setProvider(new Provider(songMappingHelper.get_providerId(),""));

        // setting the date and time to the current time
        song.setCreatedAt(LocalDateTime.now());
        song.setPublishingDate(songMappingHelper.get_publishingDate());
        songRepository.save(song);
        return HttpHelper.getHttpResponseEntity(song,HttpStatus.CREATED);
        }
        else return HttpHelper.getHttpResponseEntity("You have empty fields or null values,Please insert correct values!!",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return HttpHelper.getHttpResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/songs/{id}")
    public ResponseEntity<?> updateSong(@RequestBody SongMappingHelper songMappingHelper, @PathVariable Long id){

        try {
            Song song1 = songRepository.getOne(id);
            song1.setArtist(new Artist(songMappingHelper.get_artistId(), "", 0, ""));
            song1.setProvider(new Provider(songMappingHelper.get_providerId(), ""));
            song1.setSongName(songMappingHelper.get_songName());
            song1.setGenre(songMappingHelper.get_genre());
            song1.setPublishingDate(songMappingHelper.get_publishingDate());
            songRepository.save(song1);
            return HttpHelper.getHttpResponseEntity(song1,HttpStatus.CREATED);
        }
        catch (Exception e){
            return HttpHelper.getHttpResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //                                    ******************* Deleting Song method *****************

    @DeleteMapping(value = "/songs/{id}")
    public void deleteSong(@PathVariable Long id){
        songRepository.deleteById(id);
    }

    /*@DeleteMapping(value = "/{id}/provider/{providerId}")
    public void deleteProvider(@PathVariable Long id,@PathVariable Long providerId){
        Song song=songRepository.getOne(id);
        song.getArtist().setArtistId(null);
        providerRepository.deleteById(providerId);

    }*/
}
