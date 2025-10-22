package ma.projet.service;

import ma.projet.classes.tache;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TacheService implements IDao<tache> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(tache tache) {
        Session session = sessionFactory.getCurrentSession();
        session.save(tache);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(tache tache) {
        sessionFactory.getCurrentSession().delete(tache);
        return true;
    }

    @Override
    @Transactional
    public boolean update(tache tache) {
        sessionFactory.getCurrentSession().update(tache);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public tache findById(int id) {
        return sessionFactory.getCurrentSession().get(tache.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<tache> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from tache", tache.class)
                .list();
    }



    @Transactional(readOnly = true)
    public void afficherTachesPrixSuperieurA1000() {
        Session session = sessionFactory.getCurrentSession();
        List<tache> taches = session.createNamedQuery("Tache.findByPrixSuperieur", tache.class)
                .list();

        if (!taches.isEmpty()) {
            System.out.println("Liste des tâches avec prix > 1000 DH :");

            System.out.printf(
                    "%-5s %-25s %-25s %-10s %-12s %-12s%n",
                    "Num", "Nom", "Projet", "Prix", "Date Début", "Date Fin"
            );

            for (tache t : taches) {
                String dateDebut = t.getDateDebut() != null ? t.getDateDebut().toString() : "N/A";
                String dateFin = t.getDateFin() != null ? t.getDateFin().toString() : "N/A";
                String nomProjet = t.getProjet() != null ? t.getProjet().getNom() : "N/A";
                System.out.printf(
                        "%-5d %-25s %-25s %-10.0f %-12s %-12s%n",
                        t.getId(), t.getNom(), nomProjet, t.getPrix(), dateDebut, dateFin
                );
            }
        } else {
            System.out.println("Aucune tâche avec prix > 1000 DH.");
        }
    }

    @Transactional(readOnly = true)
    public void afficherTachesEntreDates(LocalDate dateDebut, LocalDate dateFin) {
        Session session = sessionFactory.getCurrentSession();
        List<tache> taches = session.createQuery(
                        "from tache t where t.dateFin between :debut and :fin",
                        tache.class
                )
                .setParameter("debut", dateDebut)
                .setParameter("fin", dateFin)
                .list();

        if (!taches.isEmpty()) {
            System.out.println("Liste des tâches réalisées entre " + dateDebut + " et " + dateFin + " :");

            System.out.printf(
                    "%-5s %-25s %-25s %-10s %-12s %-12s%n",
                    "Num", "Nom", "Projet", "Prix", "Date Début", "Date Fin"
            );

            for (tache t : taches) {
                String dateDeb = t.getDateDebut() != null ? t.getDateDebut().toString() : "N/A";
                String dateFinT = t.getDateFin() != null ? t.getDateFin().toString() : "N/A";
                String nomProjet = t.getProjet() != null ? t.getProjet().getNom() : "N/A";
                System.out.printf(
                        "%-5d %-25s %-25s %-10.0f %-12s %-12s%n",
                        t.getId(), t.getNom(), nomProjet, t.getPrix(), dateDeb, dateFinT
                );
            }
        } else {
            System.out.println("Aucune tâche réalisée dans cette période.");
        }
    }




}
