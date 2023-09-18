package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Entity
public class RdvIntitules {
    private Long id;
    private List<String> intitules;

    public RdvIntitules(List<String> intitules) {
        this.intitules = intitules;
    }

    public RdvIntitules() {
        this.intitules = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void addIntitule(String nom){
        if(nom.isBlank() ){
            Logger.getGlobal().info("l'intitulé est vide");
            return;
        }
        Objects.requireNonNull(nom);
        this.intitules.add(nom);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
