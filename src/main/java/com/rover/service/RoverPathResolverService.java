package com.rover.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.rover.enums.Movement;
import com.rover.exceptions.CollisionException;
import com.rover.models.Grid;
import com.rover.models.Placement;
import com.rover.models.Rover;

public class RoverPathResolverService {

  private HashMap<Long, Rover> roverMapping = new HashMap<>();
  private Grid grid;
  private Long hashMultiplier;
  private int roverNumber = 1;

  public RoverPathResolverService(Grid grid) {
    this.grid = grid;
    this.hashMultiplier = Long.valueOf(grid.getXMaxPosition()) * Long.valueOf(grid.getYMaxPosition());
  }

  public Long placeRover(Placement placement) {
    Long hash = calculateHash(placement);
    if (roverMapping.put(hash, new Rover(placement, grid, roverNumber++)) != null) {
      throw new CollisionException("Cannot place a rover where another rover is parked");
    }
    return hash;
  }

  public void resolveRoverPath(List<Movement> movements, Long hash) {
    Long currentHash = hash;
    for (Movement move : movements) {
      if (move.equals(Movement.M)) {
        currentHash = replaceRover(currentHash, move);
      } else {
        Rover rover = roverMapping.get(currentHash);
        rover.resolveMovement(move);
      }
    }
  }

  public List<String> getAllRovers() {
    List<Rover> roverList = getSortedRoverList();
    List<String> formattedList = new ArrayList<>();
    for (Rover rover : roverList) {
      formattedList.add(getRoverPlacementMessage(rover));
    }
    return formattedList;
  }

  private List<Rover> getSortedRoverList() {
    List<Rover> roverList = roverMapping.values().stream().collect(Collectors.toList());
    return roverList.stream().sorted(Comparator.comparingInt(Rover::getRoverNumber)).collect(Collectors.toList());
  }

  private String getRoverPlacementMessage(Rover rover) {
    return rover.getPlacement().getXPosition() + " " + rover.getPlacement().getYPosition() + " "
        + rover.getPlacement().getHeading().toString();
  }

  private Long calculateHash(Placement placement) {
    return hashMultiplier * Long.valueOf(placement.getXPosition()) + Long.valueOf(placement.getYPosition());
  }

  private Long replaceRover(Long hash, Movement move) {
    Long newHash = hash;
    Rover currentRover = roverMapping.get(hash);
    currentRover.resolveMovement(move);
    if (move.equals(Movement.M)) {
      newHash = this.placeRover(currentRover.getPlacement());
      roverMapping.put(newHash, currentRover);
      roverMapping.remove(hash);
    }
    return newHash;
  }
}
