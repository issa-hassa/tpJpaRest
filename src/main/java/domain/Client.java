package domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;

import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Entity
public class Client extends User {


    List<RDV> listRdv;

    public Client() {
    }

    public Client(String nom) {
       super(nom);
       this.listRdv = new ArrayList<>();
    }

    public Client(String nom, String prenom, String login, String motDePasse) {
        super(nom, prenom, login, motDePasse);
    }



    @OneToMany(mappedBy = "utilisateur")
    @XmlElement(name = "RDVS")
    @Autowired
    public List<RDV> getListRdvistRdv() {
        return Collections.unmodifiableList(this.listRdv);
    }




    public void setListRdvistRdv(List<RDV> listRdv) {
        this.listRdv = listRdv;
    }
}
