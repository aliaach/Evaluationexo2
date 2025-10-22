package ma.projet.classes;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;



    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<tache> taches = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private employe chef;

    public projet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<tache> getTaches() {
        return taches;
    }

    public void setTaches(List<tache> taches) {
        this.taches = taches;
    }

    public employe getChef() {
        return chef;
    }

    public void setChef(employe chef) {
        this.chef = chef;
    }
}
