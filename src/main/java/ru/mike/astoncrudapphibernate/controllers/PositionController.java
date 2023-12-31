package ru.mike.astoncrudapphibernate.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mike.astoncrudapphibernate.entities.Position;
import ru.mike.astoncrudapphibernate.service.PositionServiceInterface;

import java.util.List;


@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionServiceInterface positionService;

    @Autowired
    public PositionController(PositionServiceInterface positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public ResponseEntity<String> addPosition(@RequestBody Position position) {
        try {
            positionService.addPosition(position);
            return ResponseEntity.status(HttpStatus.CREATED).body("Position added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding position.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePosition(@PathVariable long id, @RequestBody Position updatedPosition) {
        try {
            Position existingPosition = positionService.getPositionById(id);
            if (existingPosition == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Position not found.");
            }

            updatedPosition.setId(id);
            positionService.updatePosition(updatedPosition);
            return ResponseEntity.status(HttpStatus.OK).body("Position updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating position.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable long id) {
        try {
            Position existingPosition = positionService.getPositionById(id);
            if (existingPosition == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Position not found.");
            }

            positionService.deletePosition(id);
            return ResponseEntity.status(HttpStatus.OK).body("Position deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting position.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Position>> getAllPositions() {
        try {
            List<Position> positions = positionService.getAllPositions();
            return ResponseEntity.ok(positions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable long id) {
        try {
            Position position = positionService.getPositionById(id);
            if (position == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(position);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
