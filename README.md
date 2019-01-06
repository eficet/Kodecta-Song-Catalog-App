# Kodecta Song Catalog Application
I built this RestFul API using Java Spring Boot and JPA & Hibernate to build my Embadded SQL database.

## Instructions
1. Clone the repository from  ` https://github.com/eficet/Kodecta-Song-Catalog-App.git`.
2. To start the server and run the Application hit this command in the terminal after u enter the project folder ` java -jar target/Songs-Api-1.0-SNAPSHOT.jar `.
3. The database will be dropped and get created each time you run the server(the database has initial data so you can start testing).
4. To test the Application you can use Postman, In the documentaion you will see a detailed explanation of Routes and what they does.

## API Documentation

This App allows you to preform CRUD operations for Artists, Providers and most importantly for the Songs.

### Entities

Artist   - artistId, name, country, age.
Provider - providerId, providerName.
Song     - songId, songName, genre, createdAt, publishingDate.

### Routes

1. Route URL: ` http://localhost:8080/api/songs ` on this route you can preform GET and POST methods.

`  /** POST This function is Adding a new Song.
     * GET  This function returns list of  all songs.
     *
     * @return the list of all songs
     */ `

2. Route URL: ` http://localhost:8080/api/songs/{id}

