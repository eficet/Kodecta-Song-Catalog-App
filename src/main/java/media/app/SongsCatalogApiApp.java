package media.app;

import media.app.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SongsCatalogApiApp {
    @Autowired
    ArtistRepository artistRepository;
    public static void main(String[] args){

        SpringApplication.run(SongsCatalogApiApp.class,args);
    }
}
