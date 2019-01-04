package media.app.controller;

import media.app.model.Provider;
import media.app.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class ProviderController {

    @Autowired
    ProviderRepository providerRepository;

    @GetMapping(value = "/provider")
    public List<Provider> getProviders(){
        return providerRepository.findAll();
    }

    @GetMapping(value = "/provider/{id}")
    public Optional<Provider> getProviderById(@PathVariable Long id){
        return providerRepository.findById(id);
    }

    @PostMapping(value = "/provider")
    public void addProvider(@RequestBody Provider provider){
        providerRepository.save(provider);
    }
    @PutMapping(value = "/provider/{id}")
    public void updateProvider(@RequestBody Provider provider,@PathVariable Long id){
        Provider _provider =providerRepository.getOne(id);
        _provider.setProviderName(provider.getProviderName());
        providerRepository.save(_provider);
    }

    @DeleteMapping(value = "/provider/{id}")
    public void deleteProdvider(@PathVariable Long id){
        Provider provider =providerRepository.getOne(id);
        providerRepository.delete(provider);
    }
}
