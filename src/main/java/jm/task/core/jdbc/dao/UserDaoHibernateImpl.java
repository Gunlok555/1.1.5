package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sf = Util.getConnection();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            try {
                session.createNativeQuery("CREATE TABLE IF NOT EXISTS testKata" +
                        " (id mediumint not null auto_increment, name VARCHAR(50), " +
                        "lastname VARCHAR(50), " +
                        "age tinyint, " +
                        "PRIMARY KEY (id))").executeUpdate();
                session.getTransaction().commit();
                System.out.println("Таблица создана!");
            } catch (HibernateException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            try {
                session.createNativeQuery("DROP TABLE IF EXISTS testKata").executeUpdate();
                session.getTransaction().commit();
                System.out.println("Таблица удалена!");
            } catch (HibernateException e) {
                e.printStackTrace();


            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            try {
                session.save(new User(name, lastName, age));
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            try {
                session.delete(session.get(User.class, id));
                session.getTransaction().commit();
                System.out.println("User под id – " + id + " удален из базы данных!");

            } catch (HibernateException e) {
                session.getTransaction().rollback();

            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sf.openSession()) {

            try {
                session.beginTransaction();
                List<User> userList = session.createQuery("from User").getResultList();
                session.getTransaction().commit();
                return userList;
            } catch (HibernateException e) {
                session.getTransaction().rollback();

            }
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            try {
                session.createNativeQuery("TRUNCATE TABLE testKata;").executeUpdate();
                session.getTransaction().commit();

            } catch (HibernateException e) {
                e.printStackTrace();


            }
        }
    }
}