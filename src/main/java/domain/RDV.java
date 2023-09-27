package domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Component
public class RDV {

    private Long id;
    private String nom;
    private Client client;
    private Professionnel professionnel;

    public RDV() {
    }

    public RDV(String nom) {
        this.nom = nom;
    }

    @Id
    @GeneratedValue
    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }
    @XmlElement(name = "Intitilé")
    public String getNom() {
        return nom;
    }
    @ManyToOne
    @XmlElement(name = "Client")
    @Autowired

    public Client getUtilisateur() {
        return client;
    }
    @ManyToOne
    @XmlElement(name = "Professionnel")
    @Autowired
    public Professionnel getProfessionnel() {
        return professionnel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUtilisateur(Client client) {
        this.client = client;
    }

    public void setProfessionnel(Professionnel professionnel) {
        this.professionnel = professionnel;
    }
}
