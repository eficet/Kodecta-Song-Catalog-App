package media.app.controller;

import media.app.helpers.HttpHelper;
import media.app.model.Provider;
import media.app.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class ProviderController {

    @Autowired
    ProviderRepository providerRepository;

    /**
     * This Function Returns all Providers
     *
     * @return List of Providers
     */
    @GetMapping(value = "/provider")
    public List<Provider> getProviders() {
        return providerRepository.findAll();
    }

    /**
     * This function returns Provider by his ID
     *
     * @param id
     * @return Provider object
     */
    @GetMapping(value = "/provider/{id}")
    public ResponseEntity<?> getProviderById(@PathVariable Long id) {
        try {
            Optional<Provider> provider = providerRepository.findById(id);
            return HttpHelper.getHttpResponseEntity(provider, HttpStatus.OK);
        } catch (Exception e) {

            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function Adds new Artist
     *
     * @param provider the provider object from request body
     * @return the added artist
     */
    @PostMapping(value = "/provider")
    public ResponseEntity<?> addProvider(@RequestBody Provider provider) {
        try {
            providerRepository.save(provider);
            return HttpHelper.getHttpResponseEntity(provider, HttpStatus.CREATED);
        } catch (Exception e) {

            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This Function updates the Provider in the database
     *
     * @param id
     * @param provider
     */
    @PutMapping(value = "/provider/{id}")
    public ResponseEntity<?> updateProvider(@RequestBody Provider provider, @PathVariable Long id) {
        try {
            Provider _provider = providerRepository.getOne(id);
            _provider.setProviderName(provider.getProviderName());
            providerRepository.save(_provider);
            return HttpHelper.getHttpResponseEntity(provider, HttpStatus.CREATED);
        } catch (Exception e) {
            return HttpHelper.getHttpResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
