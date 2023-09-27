package domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@XmlRootElement(name = "Client")
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



    @OneToMany(mappedBy = "client")
    @XmlElement(name = "RDVS")
    public List<RDV> getListRdv() {
        return Collections.unmodifiableList(this.listRdv);
    }




    public void setListRdv(List<RDV> listRdv) {
        this.listRdv = listRdv;
    }
}
