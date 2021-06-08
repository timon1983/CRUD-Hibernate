package repository.hibernate;

import model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.PostRepository;

import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    private SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();

    @Override
    public Post getById(Long id) {
        Transaction transaction;
        Post post;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            post = session.get(Post.class, id);
            transaction.commit();
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        Transaction transaction;
        List<Post> posts;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            posts = session.createQuery("FROM Post").list();
            transaction.commit();
        }
        return posts;
    }

    @Override
    public Post save(Post post) {
        Transaction transaction;
        Long postId;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            postId = (Long) session.save(post);
            post.setId(postId);
            transaction.commit();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        Transaction transaction;
        Post postUpdate;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            postUpdate = session.get(Post.class,post.getId());
            postUpdate.setContent(post.getContent());
            postUpdate.setCreated(post.getCreated());
            postUpdate.setUpdated(post.getUpdated());
            session.update(postUpdate);
            transaction.commit();
        }
        return postUpdate;
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction;
        Post postDelete;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            postDelete = session.get(Post.class, id);
            session.delete(postDelete);
            transaction.commit();
        }
    }
}
