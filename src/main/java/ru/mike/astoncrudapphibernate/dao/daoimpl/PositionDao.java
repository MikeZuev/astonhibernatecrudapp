package ru.mike.astoncrudapphibernate.dao.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mike.astoncrudapphibernate.HibernateConfig;
import ru.mike.astoncrudapphibernate.entities.Position;
import ru.mike.astoncrudapphibernate.dao.PositionDaoInterface;

import java.util.ArrayList;
import java.util.List;

@Component
public class PositionDao implements PositionDaoInterface {

    private final HibernateConfig hibernateConfig;

    @Autowired
    public PositionDao(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Override
    public void addPosition(Position position) {
        try(Session session = hibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(position);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updatePosition(Position updatedPosition) {
        try(Session session = hibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(updatedPosition);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletePosition(long positionId) {
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Position position = session.get(Position.class, positionId);
            if(position != null) {
                session.remove(position);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            positions =  session.createQuery("FROM Position", Position.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }

    @Override
    public Position getPositionById(long positionId) {
        Transaction transaction = null;
        Position position = null;
        try (Session session = hibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            position = session.get(Position.class, positionId);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return position;

    }
}
