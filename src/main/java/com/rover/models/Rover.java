package com.rover.models;

import java.util.Objects;

import com.rover.enums.Heading;
import com.rover.enums.Movement;
import com.rover.exceptions.OutOfBoundsException;

public class Rover {

  private Placement placement;
  private Grid grid;
  private int roverNumber;

  public Rover() {
  }

  public Rover(Placement placement, Grid grid, int roverNumber) {
    this.placement = placement;
    this.grid = grid;
    this.roverNumber = roverNumber;
  }

  public Placement getPlacement() {
    return this.placement;
  }

  public void setPlacement(Placement placement) {
    this.placement = placement;
  }

  public Grid getGrid() {
    return this.grid;
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  public int getRoverNumber() {
    return this.roverNumber;
  }

  public void setRoverNumber(int roverNumber) {
    this.roverNumber = roverNumber;
  }

  public Rover placement(Placement placement) {
    this.placement = placement;
    return this;
  }

  public Rover grid(Grid grid) {
    this.grid = grid;
    return this;
  }

  public Rover roverNumber(int roverNumber) {
    this.roverNumber = roverNumber;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Rover)) {
      return false;
    }
    Rover rover = (Rover) o;
    return Objects.equals(placement, rover.placement) && Objects.equals(grid, rover.grid)
        && roverNumber == rover.roverNumber;
  }

  @Override
  public int hashCode() {
    return Objects.hash(placement, grid, roverNumber);
  }

  @Override
  public String toString() {
    return "{" + " placement='" + getPlacement() + "'" + ", grid='" + getGrid() + "'" + ", roverNumber='"
        + getRoverNumber() + "'" + "}";
  }

  public void resolveMovement(Movement movement) {
    if (movement.equals(Movement.M)) {
      resolveMove();
    } else {
      resolveRotation(movement);
    }
  }

  private void resolveRotation(Movement rotation) {
    switch (placement.getHeading()) {
      case N:
        if (rotation.equals(Movement.L))
          placement.setHeading(Heading.W);
        else
          placement.setHeading(Heading.E);
        return;
      case W:
        if (rotation.equals(Movement.L))
          placement.setHeading(Heading.S);
        else
          placement.setHeading(Heading.N);
        return;
      case S:
        if (rotation.equals(Movement.L))
          placement.setHeading(Heading.E);
        else
          placement.setHeading(Heading.W);
        return;
      case E:
        if (rotation.equals(Movement.L))
          placement.setHeading(Heading.N);
        else
          placement.setHeading(Heading.S);
        return;
    }
  }

  private void resolveMove() {
    int xPosition = placement.getXPosition();
    int yPosition = placement.getYPosition();
    switch (placement.getHeading()) {
      case N:
        yPosition += 1;
        validateYCurrentPosition(yPosition);
        placement.setYPosition(yPosition);
        break;
      case W:
        xPosition -= 1;
        validateXCurrentPosition(xPosition);
        placement.setXPosition(xPosition);
        break;
      case S:
        yPosition -= 1;
        validateYCurrentPosition(yPosition);
        placement.setYPosition(yPosition);
        break;
      case E:
        xPosition += 1;
        validateXCurrentPosition(xPosition);
        placement.setXPosition(xPosition);
        break;
    }
  }

  private void validateXCurrentPosition(int xPositionValue) {
    if (xPositionValue < 0 && xPositionValue > grid.getXMaxPosition()) {
      throw new OutOfBoundsException();
    }
  }

  private void validateYCurrentPosition(int yPositionValue) {
    if (yPositionValue < 0 && yPositionValue > grid.getYMaxPosition()) {
      throw new OutOfBoundsException();
    }
  }
}
