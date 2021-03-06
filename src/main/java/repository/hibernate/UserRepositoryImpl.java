package repository.hibernate;

import model.Post;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getById(Long id) {
        Transaction transaction;
        User user;
        List<Post> posts;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = (Query) session.createQuery("FROM User U JOIN FETCH U.region WHERE U.id = " + id);
            posts = session.createQuery("FROM Post").list();
            user = (User)query.uniqueResult();
            user.setPosts(posts);
            transaction.commit();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        Transaction transaction;
        List<User> users;
        List<Post> posts;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User U JOIN FETCH U.region").list();
            posts = session.createQuery("FROM Post").list();
            for(User user: users){
                user.setPosts(posts);
            }
            transaction.commit();
        }
        return users;
    }

    @Override
    public User save(User user) {
        Transaction transaction;
        Long userId;
        Long regionId;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            regionId = (Long) session.save(user.getRegion());
            userId = (Long) session.save(user);
            user.getRegion().setId(regionId);
            user.setId(userId);
            transaction.commit();
        }
        return user;
    }

    @Override
    public User update(User user) {
        Transaction transaction;
        User userUpdate;
        List<Post> posts;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = (Query) session.createQuery("FROM User U JOIN FETCH U.region WHERE U.id = " + user.getId());
            posts = session.createQuery("FROM Post").list();
            userUpdate = (User)query.uniqueResult();
            userUpdate.setPosts(posts);
            userUpdate.setFirstName(user.getFirstName());
            userUpdate.setLastName(user.getLastName());
            userUpdate.setRegion(user.getRegion());
            session.update(userUpdate);
            transaction.commit();
        }
        return userUpdate;
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction;
        User userDelete;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userDelete = session.get(User.class, id);
            session.delete(userDelete);
            transaction.commit();
        }
    }
}
