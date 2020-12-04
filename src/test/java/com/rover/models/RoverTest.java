package com.rover.models;

import com.rover.enums.Heading;
import com.rover.enums.Movement;
import com.rover.exceptions.OutOfBoundsException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoverTest {

  @Test
  public void resolveMovementShouldReturnCorrectPositionGivenValidMovement() {
    Rover rover = new Rover(new Placement(0, 0, Heading.N), new Grid(5, 5), 1);

    rover.resolveMovement(Movement.M);

    Placement newPlacement = rover.getPlacement();

    Assertions.assertEquals(0, newPlacement.getXPosition());
    Assertions.assertEquals(1, newPlacement.getYPosition());
    Assertions.assertEquals(Heading.N, newPlacement.getHeading());
  }

  @Test
  public void resolveMovementShouldReturnOutOfBoundsExceptionGivenValidMovementOutsideGridLowerBound() {
    Rover rover = new Rover(new Placement(0, 0, Heading.W), new Grid(5, 5), 1);

    Assertions.assertThrows(OutOfBoundsException.class, () -> {
      rover.resolveMovement(Movement.M);
    });
  }

  @Test
  public void resolveMovementShouldReturnOutOfBoundsExceptionGivenValidMovementOutsideGridUpperBound() {
    Rover rover = new Rover(new Placement(5, 5, Heading.N), new Grid(5, 5), 1);

    Assertions.assertThrows(OutOfBoundsException.class, () -> {
      rover.resolveMovement(Movement.M);
    });
  }

  @Test
  public void resolveMovementShouldRotateRoverLeftCorrectlyGivenCorrectInputs() {
    Rover rover = new Rover(new Placement(0, 0, Heading.W), new Grid(5, 5), 1);

    rover.resolveMovement(Movement.L);
    Assertions.assertEquals(Heading.S, rover.getPlacement().getHeading());

    rover.resolveMovement(Movement.L);
    Assertions.assertEquals(Heading.E, rover.getPlacement().getHeading());

    rover.resolveMovement(Movement.L);
    Assertions.assertEquals(Heading.N, rover.getPlacement().getHeading());

    rover.resolveMovement(Movement.L);
    Assertions.assertEquals(Heading.W, rover.getPlacement().getHeading());
  }

  @Test
  public void resolveMovementShouldRotateRoverRightCorrectlyGivenCorrectInputs() {
    Rover rover = new Rover(new Placement(0, 0, Heading.W), new Grid(5, 5), 1);

    rover.resolveMovement(Movement.R);
    Assertions.assertEquals(Heading.N, rover.getPlacement().getHeading());

    rover.resolveMovement(Movement.R);
    Assertions.assertEquals(Heading.E, rover.getPlacement().getHeading());

    rover.resolveMovement(Movement.R);
    Assertions.assertEquals(Heading.S, rover.getPlacement().getHeading());

    rover.resolveMovement(Movement.R);
    Assertions.assertEquals(Heading.W, rover.getPlacement().getHeading());
  }

  @Test
  public void resolveMovementShouldMoveToCorrectPositionWhenGivenCorrectMovements() {
    Rover rover = new Rover(new Placement(2, 2, Heading.W), new Grid(5, 5), 1);

    rover.resolveMovement(Movement.R);
    rover.resolveMovement(Movement.M);
    Assertions.assertEquals(2, rover.getPlacement().getXPosition());
    Assertions.assertEquals(3, rover.getPlacement().getYPosition());

    rover.resolveMovement(Movement.R);
    rover.resolveMovement(Movement.M);

    Assertions.assertEquals(3, rover.getPlacement().getXPosition());
    Assertions.assertEquals(3, rover.getPlacement().getYPosition());

    rover.resolveMovement(Movement.R);
    rover.resolveMovement(Movement.M);

    Assertions.assertEquals(3, rover.getPlacement().getXPosition());
    Assertions.assertEquals(2, rover.getPlacement().getYPosition());

    rover.resolveMovement(Movement.R);
    rover.resolveMovement(Movement.M);

    Assertions.assertEquals(2, rover.getPlacement().getXPosition());
    Assertions.assertEquals(2, rover.getPlacement().getYPosition());
  }

}
