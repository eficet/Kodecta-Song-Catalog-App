# Kodecta Song Catalog Application
I built this RestFul API using Java Spring Boot and JPA & Hibernate to build my Embadded SQL database.

## Instructions
1. Clone the repository from  ` https://github.com/eficet/Kodecta-Song-Catalog-App.git`.
2. To start the server and run the Application hit this command in the terminal after u enter the project folder ` java -jar target/Songs-Api-1.0-SNAPSHOT.jar `.
3. The database will be dropped and get created each time you run the server(the database has initial data so you can start testing).
4. To test the Application you can use Postman, In the documentaion you will see a detailed explanation of the routes and what they do.

## API Documentation

This App allows you to preform CRUD operations for Artists, Providers and most importantly for the Songs and search for songs by different criterias.

### Entities

- Artist   - artistId, name, country, age.
- Provider - providerId, providerName.
- Song     - songId, songName, genre, createdAt, publishingDate, artistId(foriegn key), providerId(foriegn key).

### Routes
#### Song Routes:
1. Route URL: ` http://localhost:8080/api/songs ` on this route you can preform GET and POST request, so you can GET all songs in the database and add new (POST) song. 
2. Route URL: ` http://localhost:8080/api/songs/{id}`
on this route you preform GET,DELETE and PUT request, so you can delete, update and get the songs bi its id.
3. Route URL: `http://localhost:8080/api/songs/artist/{name}` on this route you search for the songs by Artist name.
4. Route URL: `http://localhost:8080/api/songs/genre/{genre}` on this route you search for the songs by genre.
5. Route URL: `http://localhost:8080/api/songs/provider/{provider}` on this route you search for the songs by Provider name.
6. Route URL: `http://localhost:8080/api/songs/name/{name}` on this route you search for the songs by Song's name.
7. Route URL: `http://localhost:8080/api/songs/country/{country}` on this route you search for the songs by Origin country.

#### Artist Routes:
1. Route URL: ` http://localhost:8080/api/artist ` on this route you can preform GET and POST requests , so you can GET all artists in the database and add new (POST) artist. 
2. Route URL: ` http://localhost:8080/api/songs/{id}`
on this route you preform GET,DELETE and PUT requests, so you can delete, update and get the artist bi its id.

#### Provider Routes:

1. Route URL: ` http://localhost:8080/api/provider ` on this route you can preform GET and POST requests , so you can GET all providers in the database and add new (POST) provider. 
2. Route URL: ` http://localhost:8080/api/provider/{id}`
on this route you preform PUT, so you can update the provider.

NOTE:
- Example of the put request of the song 
``` {
    	"songName": "Dino",
        "genre": "rock",
        "artistId":5,
        "providerId":2,
        "createdAt":"2011-02-21T22:12:00",
        "publishingDate":"2010-04-20"
} ```
- startedAt is automatically created according to the current time so dont bother puting it in post body.
- However startedAt can be updated by you but it should be in the correct format ` yyyy-MM-ddThh:mm:ss.SSS `.



