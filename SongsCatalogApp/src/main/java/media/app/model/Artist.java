package media.app.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artist {

    //generates a unique id and increments it automaticaly
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "artistId")
    private Long artistId;
    private String name;
    private int age;
    private String country;

    public Artist(){

    }
    public Artist(Long artistId,String name,int age,String country){
        this.artistId=artistId;
        this.name=name;
        this.age=age;
        this.country=country;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
