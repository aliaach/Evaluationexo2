package ma.projet.service;

import ma.projet.classes.projet;
import ma.projet.classes.tache;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProjetService implements IDao<projet> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(projet projet) {
        Session session = sessionFactory.getCurrentSession();
        session.save(projet);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(projet projet) {
        sessionFactory.getCurrentSession().delete(projet);
        return true;
    }

    @Override
    @Transactional
    public boolean update(projet projet) {
        sessionFactory.getCurrentSession().update(projet);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public projet findById(int id) {
        return sessionFactory.getCurrentSession().get(projet.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<projet> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from projet", projet.class)
                .list();
    }

    @Transactional(readOnly = true)
    public void afficherTachesPlanifieesParProjet(int projetId) {
        Session session = sessionFactory.getCurrentSession();
        List<tache> taches = session.createQuery(
                        "from tache t where t.projet.id = :id and t.dateFin is null",
                        tache.class
                )
                .setParameter("id", projetId)
                .list();

        if (!taches.isEmpty()) {
            projet projet = taches.get(0).getProjet();
            System.out.println("Projet : " + projet.getId() + "\tNom : " + projet.getNom() +
                    "\tDate début : " + (projet.getDateDebut() != null ? projet.getDateDebut() : "N/A"));
            System.out.println("Liste des tâches planifiées :");
            System.out.printf(
                    "%-5s %-25s %-12s %-12s%n",
                    "Num", "Nom", "Date Début", "Date Fin"
            );

            for (tache t : taches) {
                String dateDebut = t.getDateDebut() != null ? t.getDateDebut().toString() : "N/A";
                String dateFin = t.getDateFin() != null ? t.getDateFin().toString() : "N/A";
                System.out.printf(
                        "%-5s %-25s %-12s %-12s%n",
                        t.getId(), t.getNom(), dateDebut, dateFin
                );

            }
        } else {
            System.out.println("Aucune tâche planifiée pour ce projet.");
        }
    }

    @Transactional(readOnly = true)
    public void afficherTachesRealiseesParProjet(int projetId) {
        Session session = sessionFactory.getCurrentSession();
        List<tache> taches = session.createQuery(
                        "from tache t where t.projet.id = :id and t.dateFin is not null",
                        tache.class
                )
                .setParameter("id", projetId)
                .list();

        if (!taches.isEmpty()) {
            projet projet = taches.get(0).getProjet();
            System.out.println("Projet : " + projet.getId() + "\tNom : " + projet.getNom() +
                    "\tDate début : " + (projet.getDateDebut() != null ? projet.getDateDebut() : "N/A"));
            System.out.println("Liste des tâches réalisées :");


            System.out.printf(
                    "%-5s %-25s %-12s %-12s%n",
                    "Num", "Nom", "Date Début", "Date Fin"
            );

            for (tache t : taches) {
                String dateDebut = t.getDateDebut() != null ? t.getDateDebut().toString() : "N/A";
                String dateFin = t.getDateFin() != null ? t.getDateFin().toString() : "N/A";

                System.out.printf(
                        "%-5s %-25s %-12s %-12s%n",
                        t.getId(), t.getNom(), dateDebut, dateFin
                );
            }
        } else {
            System.out.println("Aucune tâche réalisée pour ce projet.");
        }
    }




}
