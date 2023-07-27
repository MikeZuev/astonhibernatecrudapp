package ru.mike.astoncrudapphibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mike.astoncrudapphibernate.dao.PositionDaoInterface;
import ru.mike.astoncrudapphibernate.entities.Position;
import ru.mike.astoncrudapphibernate.service.PositionServiceInterface;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionServiceInterface {

    private final PositionDaoInterface positionDao;

    @Autowired
    public PositionServiceImpl(PositionDaoInterface positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public void addPosition(Position position) {
        positionDao.addPosition(position);

    }

    @Override
    public void updatePosition(Position updatedPosition) {
        positionDao.updatePosition(updatedPosition);

    }

    @Override
    public void deletePosition(long positionId) {
        positionDao.deletePosition(positionId);

    }

    @Override
    public List<Position> getAllPositions() {
        return positionDao.getAllPositions();
    }

    @Override
    public Position getPositionById(long positionId) {
        return positionDao.getPositionById(positionId);
    }
}
