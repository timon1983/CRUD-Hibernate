package repository.hibernate;

import model.Region;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.RegionRepository;

import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {

    @Override
    public Region getById(Long id) {
        Transaction transaction;
        Region region;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            region = session.get(Region.class, id);
            transaction.commit();
        }
        return region;
    }

    @Override
    public List<Region> getAll() {
        Transaction transaction;
        List<Region> regions;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            regions = session.createQuery("FROM Region").list();
            transaction.commit();
        }
        return regions;
    }

    @Override
    public Region save(Region region) {
        Transaction transaction;
        Long regionId;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            regionId = (Long)session.save(region);
            region.setId(regionId);
            transaction.commit();
        }
        return region;
    }

    @Override
    public Region update(Region region) {
        Transaction transaction;
        Region regionUpdate;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            regionUpdate = session.get(Region.class, region.getId());
            regionUpdate.setName(region.getName());
            session.update(regionUpdate);
            transaction.commit();
        }
        return region;
    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction;
        Region regionDelete;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            regionDelete = session.get(Region.class, id);
            session.delete(regionDelete);
            transaction.commit();
        }
    }
}
