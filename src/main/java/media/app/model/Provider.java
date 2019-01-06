package media.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Provider {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long providerId;
    private String providerName;

    public Provider(){

    }

    public Provider(Long providerId,String providerName){
        this.providerId=providerId;
        this.providerName=providerName;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
