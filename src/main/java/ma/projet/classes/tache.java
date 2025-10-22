package ma.projet.classes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@NamedQuery(
        name = "Tache.findByPrixSuperieur",
        query = "from tache t where t.prix > 1000"
)
public class tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private ma.projet.classes.projet projet;

    @OneToMany(mappedBy = "tache")
    private List<Employetache> employeTache = new ArrayList<>();


    public tache() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public ma.projet.classes.projet getProjet() {
        return projet;
    }

    public void setProjet(ma.projet.classes.projet projet) {
        this.projet = projet;
    }

    public List<Employetache> getEmployeTache() {
        return employeTache;
    }

    public void setEmployeTache(List<Employetache> employeTache) {
        this.employeTache = employeTache;
    }
}
