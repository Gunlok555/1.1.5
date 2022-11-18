package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sf = Util.getConnection();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS testKata" +
                    " (id mediumint not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана!");
        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("DROP TABLE IF EXISTS testKata").executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена!");
        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
            //System.out.println("User с именем – " + name + " добавлен в базу данных!");
        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User под id – " + id + " удален из базы данных!");

        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sf.openSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createQuery(criteriaQuery).getResultList();
        try {
            transaction.commit();
            return userList;
        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("TRUNCATE TABLE testKata;").executeUpdate();
            transaction.commit();

        } catch (HibernateException e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
    }
}