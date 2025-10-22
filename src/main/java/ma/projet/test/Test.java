package ma.projet.test;

import ma.projet.service.EmployeService;
import ma.projet.service.EmployeTacheService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;
import ma.projet.classes.employe;
import ma.projet.classes.projet;
import ma.projet.classes.tache;
import ma.projet.classes.Employetache;
import ma.projet.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx =
                     new AnnotationConfigApplicationContext(HibernateUtil.class)) {

            EmployeService employeService = ctx.getBean(EmployeService.class);
            EmployeTacheService employeTacheService = ctx.getBean(EmployeTacheService.class);
            ProjetService projetService = ctx.getBean(ProjetService.class);
            TacheService tacheService = ctx.getBean(TacheService.class);

            employe e1 = new employe();
            e1.setNom("Yassine");
            e1.setPrenom("Amrani");
            employeService.create(e1);

            employe e2 = new employe();
            e2.setNom("Noura");
            e2.setPrenom("Belkadi");
            employeService.create(e2);

            projet p1 = new projet();
            p1.setNom("Aquila");
            p1.setChef(e1);
            p1.setDateDebut(LocalDate.of(2025, 9, 1));
            p1.setDateFin(LocalDate.of(2025, 12, 31));
            projetService.create(p1);

            projet p2 = new projet();
            p2.setNom("Lyra");
            p2.setChef(e2);
            p2.setDateDebut(LocalDate.of(2025, 8, 15));
            p2.setDateFin(LocalDate.of(2025, 11, 30));
            projetService.create(p2);

            tache t1 = new tache();
            t1.setNom("Plan Sprint 1");
            t1.setProjet(p1);
            t1.setPrix(500f);
            t1.setDateDebut(LocalDate.of(2025, 9, 5));
            t1.setDateFin(null);
            tacheService.create(t1);

            tache t2 = new tache();
            t2.setNom("Setup CI/CD");
            t2.setProjet(p2);
            t2.setPrix(800f);
            t2.setDateDebut(LocalDate.of(2025, 8, 20));
            t2.setDateFin(null);
            tacheService.create(t2);

            tache t3 = new tache();
            t3.setNom("Module Auth");
            t3.setProjet(p1);
            t3.setPrix(1500f);
            t3.setDateDebut(LocalDate.of(2025, 9, 10));
            t3.setDateFin(LocalDate.of(2025, 10, 1));
            tacheService.create(t3);

            tache t4 = new tache();
            t4.setNom("Reporting v1");
            t4.setProjet(p1);
            t4.setPrix(2000f);
            t4.setDateDebut(LocalDate.of(2025, 10, 2));
            t4.setDateFin(LocalDate.of(2025, 10, 5));
            tacheService.create(t4);

            Employetache et1 = new Employetache(); et1.setEmploye(e1); et1.setTache(t1); employeTacheService.create(et1);
            Employetache et2 = new Employetache(); et2.setEmploye(e1); et2.setTache(t3); employeTacheService.create(et2);
            Employetache et3 = new Employetache(); et3.setEmploye(e2); et3.setTache(t2); employeTacheService.create(et3);
            Employetache et4 = new Employetache(); et4.setEmploye(e1); et4.setTache(t4); employeTacheService.create(et4);

            System.out.println("\nTâches d'un employé:");
            employeService.afficherTachesParEmploye(e1.getId());

            System.out.println("\nProjets gérés par l'employé:");
            employeService.afficherProjetsGeresParEmploye(e1.getId());

            System.out.println("\nTâches planifiées pour le projet:");
            projetService.afficherTachesPlanifieesParProjet(p1.getId());

            System.out.println("\nTâches réalisées pour le projet:");
            projetService.afficherTachesRealiseesParProjet(p1.getId());

            System.out.println("\nTâches prix > 1000:");
            tacheService.afficherTachesPrixSuperieurA1000();

            System.out.println("\nTâches réalisées entre deux dates:");
            tacheService.afficherTachesEntreDates(
                    LocalDate.of(2025, 9, 1),
                    LocalDate.of(2025, 10, 10)
            );
        }
    }
}
