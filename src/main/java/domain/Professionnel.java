package domain;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jdk.jshell.execution.Util;
import org.hibernate.boot.model.relational.Sequence;

import java.util.*;

@Entity
@XmlRootElement(name = "Professionnel")
public class Professionnel extends User {


    private String urlRDV;

    private List<RDV> listRDV;
    private Enum title;

    public static enum Title {
        RDV0,
        RDV1,
        RDV2
    }
    public Professionnel() {
        this.listRDV = new ArrayList<>();


    }

    public Professionnel(String nom) {
        super(nom);
        this.listRDV = new ArrayList<>();
    }

    public Professionnel(String nom, String prenom, String login, String motDePasse) {
        super(nom, prenom, login, motDePasse);
        this.listRDV = new ArrayList<>();

    }

    @OneToMany(mappedBy = "professionnel")
    @XmlElement(name = "RDVS")
    public List<RDV> getListRDV() {
        return Collections.unmodifiableList(this.listRDV);
    }

    public void setUrlRDV(String urlRDV) {
        this.urlRDV = urlRDV;
    }


    public void setListRDV(List<RDV> ListRDV) {
        this.listRDV = ListRDV;
    }

}
