package ru.mike.astoncrudapphibernate.service;

import ru.mike.astoncrudapphibernate.entities.Position;

import java.util.List;

public interface PositionServiceInterface {
    void addPosition(Position position);

    void updatePosition(Position updatedPosition);

    void deletePosition(long positionId);

    List<Position> getAllPositions();

    Position getPositionById(long positionId);
}
