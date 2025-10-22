package ma.projet.classes;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employetache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

    private LocalDate dateDebutReelle;
    private LocalDate dateFinReelle;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private ma.projet.classes.employe employe;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    private ma.projet.classes.tache tache;

    public Employetache() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(LocalDate dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public LocalDate getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(LocalDate dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public ma.projet.classes.employe getEmploye() {
        return employe;
    }

    public void setEmploye(ma.projet.classes.employe employe) {
        this.employe = employe;
    }

    public ma.projet.classes.tache getTache() {
        return tache;
    }

    public void setTache(ma.projet.classes.tache tache) {
        this.tache = tache;
    }
}
