package ma.projet.service;

import ma.projet.classes.Employetache;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeTacheService implements IDao<Employetache> {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    @Transactional
    public boolean create(Employetache employeTache) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employeTache);
        return true;
    }
    @Override
    @Transactional
    public boolean delete(Employetache employeTache) {
        sessionFactory.getCurrentSession().delete(employeTache);
        return true;
    }
    @Override
    @Transactional
    public boolean update(Employetache employeTache) {
        sessionFactory.getCurrentSession().update(employeTache);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Employetache findById(int id) {
        return sessionFactory.getCurrentSession().get(Employetache.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employetache> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employetache", Employetache.class)
                .list();
    }



}

