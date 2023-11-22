package domain;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement(name = "User")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public abstract class User {

    private Long id;
    private String nom;
    private String prenom;
    private String login;
    private String motDePasse;


    public User(String nom) {
        this.nom = nom;
    }

    public User(String nom, String prenom, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    @Id
    @GeneratedValue
    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    @XmlElement(name = "nom")
    public String getNom() {
        return nom;
    }

    @XmlElement(name = "prenom")
    public String getPrenom() {
        return prenom;
    }

    @XmlElement(name = "login")
    public String getLogin() {
        return login;
    }

    @XmlElement(name = "motDePasse")
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}