package ma.projet.service;

import ma.projet.classes.employe;
import ma.projet.classes.projet;
import ma.projet.classes.tache;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//no copy
@Repository
public class EmployeService implements IDao<employe> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(employe employe) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employe);
        return true;
    }
    @Override
    @Transactional
    public boolean delete(employe employe) {
        sessionFactory.getCurrentSession().delete(employe);
        return true;
    }
    @Override
    @Transactional
    public boolean update(employe employe) {
        sessionFactory.getCurrentSession().update(employe);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public employe findById(int id) {
        return sessionFactory.getCurrentSession().get(employe.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<employe> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from employe", employe.class)
                .list();
    }


    @Transactional(readOnly = true)
    public void afficherTachesParEmploye(int employeId) {
        Session session = sessionFactory.getCurrentSession();
        List<tache> taches = session.createQuery(
                        "select e.tache from Employetache e where e.employe.id = :employeId",
                        tache.class
                )
                .setParameter("employeId", employeId)
                .list();

        if (!taches.isEmpty()) {
            employe employe = session.get(ma.projet.classes.employe.class, employeId);
            System.out.println("Employé : " + employe.getNom() + " " + employe.getPrenom());
            System.out.println("Liste des tâches :");

            System.out.printf(
                    "%-5s %-25s %-25s %-12s %-12s%n",
                    "Num", "Nom", "Projet", "Date Début", "Date Fin"
            );

            for (tache t : taches) {
                String dateDebut = t.getDateDebut() != null ? t.getDateDebut().toString() : "N/A";
                String dateFin = t.getDateFin() != null ? t.getDateFin().toString() : "N/A";
                String nomProjet = t.getProjet() != null ? t.getProjet().getNom() : "N/A";
                System.out.printf(
                        "%-5d %-25s %-25s %-12s %-12s%n",
                        t.getId(), t.getNom(), nomProjet, dateDebut, dateFin
                );
            }
        } else {
            System.out.println("Aucune tâche assignée à cet employé.");
        }
    }

    @Transactional(readOnly = true)
    public void afficherProjetsGeresParEmploye(int employeId) {
        Session session = sessionFactory.getCurrentSession();
        List<projet> projets = session.createQuery(
                        "from projet p where p.chef.id = :id",
                        projet.class
                )
                .setParameter("id", employeId)
                .list();

        if (!projets.isEmpty()) {
            employe employe = session.get(ma.projet.classes.employe.class, employeId);
            System.out.println("Employé : " + employe.getNom() + " " + employe.getPrenom());
            System.out.println("Liste des projets gérés :");

            System.out.printf(
                    "%-5s %-25s %-12s %-12s%n",
                    "ID", "Nom", "Date Début", "Date Fin"
            );


            for (projet p : projets) {
                String dateDebut = p.getDateDebut() != null ? p.getDateDebut().toString() : "N/A";
                String dateFin = p.getDateFin() != null ? p.getDateFin().toString() : "N/A";

                System.out.printf(
                        "%-5d %-25s %-12s %-12s%n",
                        p.getId(), p.getNom(), dateDebut, dateFin
                );
            }
        } else {
            System.out.println("Cet employé ne gère aucun projet.");
        }
    }



}
