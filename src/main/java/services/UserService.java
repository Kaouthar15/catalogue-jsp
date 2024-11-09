package services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import models.User;
import utils.HibernateUtil;

public class UserService implements DAO<User> {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private SessionFactory sessionFactory = getSessionFactory();

    // Retrieve the SessionFactory
    protected SessionFactory getSessionFactory() {
        try {
            return HibernateUtil.getSessionFactory();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Could not create SessionFactory", ex);
            throw new IllegalStateException("Could not create SessionFactory");
        }
    }

    // Add a user
    @Override
    public void add(User user) {
    	System.out.println("add service");
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        logger.info("User saved successfully, User Details=" + user);
    }

    // Update a user
    @Override
    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        logger.info("User updated successfully, User Details=" + user);
    }

    // Remove a user
    @Override
    public void remove(Long userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.getReference(User.class, Long.valueOf(userId));
        if (user != null) {
            session.remove(user);
        }
        session.getTransaction().commit();
        logger.info("User deleted successfully, User Details=" + user);
    }

    // Get a user by ID
    @Override
    public User get(Long userId) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.getReference(User.class, Long.valueOf(userId));
        logger.info("User loaded successfully, User Details=" + user);
        return user;
    }

    // Get a user by ID with transaction (optional for transactional consistency)
    @Override
    public User getById(Long userId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.getReference(User.class, Long.valueOf(userId));
        session.getTransaction().commit();
        logger.info("User loaded successfully, User Details=" + user);
        return user;
    }

    // List all users
    @Override
    public List<User> list() {
        List<User> usersList = new ArrayList<>();
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            usersList = session.createQuery("select u from User u", User.class).list();
            session.getTransaction().commit();
            return usersList;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in list users", e);
            return usersList;
        }
    }

    // Select users by keyword (e.g., name or email)
    @Override
    public List<User> selectByKeyword(String str) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> usersList = session.createQuery("from User u WHERE u.name LIKE '%" + str + "%' OR u.email LIKE '%" + str + "%'", User.class).list();
        session.getTransaction().commit();
        logger.info("Users retrieved by keyword successfully, Users List=" + usersList);
        return usersList;
    }
}
