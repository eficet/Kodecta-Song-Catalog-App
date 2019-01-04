package media.app.repository;

import media.app.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Long> {

     List<Song> findByArtistNameIgnoreCase(String name);

    List<Song> findByGenreIgnoreCase(String genre);

    List<Song> findByProviderProviderNameIgnoreCase(String publisher);

    List<Song> findBySongNameIgnoreCase(String name);

    List<Song> findByArtistCountry(String country);
}
