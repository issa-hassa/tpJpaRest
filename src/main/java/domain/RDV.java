package domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@Entity
@XmlRootElement(name = "Client")
public class RDV {

    private Long id;
    private String nom;
    private Client client;
    private Professionnel professionnel;
    private Date date;



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
    @XmlElement(name = "Intitulé")
    public String getNom() {
        return nom;
    }

    public Date getDate() {
        return date;
    }

    @ManyToOne
    @XmlElement(name = "Client")
    public Client getClient() {
        return client;
    }
    @ManyToOne
    @XmlElement(name = "Professionnel")
    public Professionnel getProfessionnel() {
        return professionnel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setClient(Client client) {

        this.client = client;
        client.getListRdv().add(this);
    }

    public void setProfessionnel(Professionnel professionnel) {

        this.professionnel = professionnel;
        professionnel.getListRDV().add(this);
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
