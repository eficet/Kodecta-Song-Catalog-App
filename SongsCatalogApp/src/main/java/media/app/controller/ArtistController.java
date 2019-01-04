package media.app.controller;

import media.app.model.Artist;
import media.app.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping(value = "/artist")
    public List<Artist> getArtists(){
        List<Artist> data = artistRepository.findAll();
        return data;
    }

    @PostMapping(value = "/artist")
    public void postArtist(@RequestBody Artist artist){
        artistRepository.save(artist);
    }

    @GetMapping(value = "/artist/{id}")
    public Optional<Artist> getArtistById(@PathVariable Long id){
        Optional<Artist> artist= artistRepository.findById(id);
        return artist;

       // return artist;
    }

    @PutMapping(value = "/artist/{id}")
    public void updateArtist(@PathVariable Long id,@RequestBody Artist artist ){
        Artist myArtist = artistRepository.getOne(id);
        myArtist.setName(artist.getName());
        myArtist.setCountry(artist.getCountry());
        myArtist.setAge(artist.getAge());
        artistRepository.save(myArtist);
    }

    @DeleteMapping(value="artist/{id}")
    public void deleteArtist(@PathVariable Long id){
        artistRepository.deleteById(id);
    }

}
