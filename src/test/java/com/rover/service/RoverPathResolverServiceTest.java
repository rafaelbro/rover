package com.rover.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rover.enums.Heading;
import com.rover.enums.Movement;
import com.rover.exceptions.CollisionException;
import com.rover.exceptions.OutOfBoundsException;
import com.rover.models.Grid;
import com.rover.models.Placement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoverPathResolverServiceTest {

  private RoverPathResolverService roverPathResolverService;

  @BeforeEach
  public void setUpService() {
    roverPathResolverService = new RoverPathResolverService(new Grid(5, 5));
  }

  @Test
  public void placeRoverShoudPlaceRoverWhenGivenValidPlacement() {
    Long roverHash = roverPathResolverService.placeRover(new Placement(1, 1, Heading.N));

    Assertions.assertNotNull(roverHash);
  }

  @Test
  public void placeRoverShoudThrowOutOfBoundsExceptionWhenGivenPlacementOutsideGrid() {
    Assertions.assertThrows(OutOfBoundsException.class, () -> {
      roverPathResolverService.placeRover(new Placement(6, 1, Heading.N));
    });
    Assertions.assertThrows(OutOfBoundsException.class, () -> {
      roverPathResolverService.placeRover(new Placement(1, 6, Heading.N));
    });
    Assertions.assertThrows(OutOfBoundsException.class, () -> {
      roverPathResolverService.placeRover(new Placement(-1, 0, Heading.N));
    });
  }

  @Test
  public void placeRoverShouldCalculateUniqueHashForEachGridPositionWhenGivenValidGrid() {
    Set<Long> longSet = new HashSet<>();

    roverPathResolverService = new RoverPathResolverService(new Grid(100, 100));

    for (int gridXPlacement = 0; gridXPlacement < 100; gridXPlacement++) {
      for (int gridYPlacement = 0; gridYPlacement < 100; gridYPlacement++) {
        boolean notPresentInSet = longSet
            .add(roverPathResolverService.placeRover(new Placement(gridXPlacement, gridYPlacement, Heading.N)));
        Assertions.assertTrue(notPresentInSet);
      }
    }
    Assertions.assertEquals(100 * 100, longSet.size());
  }

  @Test
  public void placeRoverShouldThrowExceptionWhenPlacingInSamePosition() {
    Assertions.assertThrows(CollisionException.class, () -> {
      roverPathResolverService.placeRover(new Placement(4, 1, Heading.N));
      roverPathResolverService.placeRover(new Placement(4, 1, Heading.N));
    });
  }

  @Test
  public void resolveRoverPathShouldReturnValidHashesWhenGivenListOfMovements() {
    List<Movement> movements = FileUtilityService.resolveEvenLine("LRLRLRLRLMMMRMLMRMM", 1);

    Long roverKey = roverPathResolverService.placeRover(new Placement(0, 0, Heading.E));
    Long finalRoverKey = roverPathResolverService.resolveRoverPath(movements, roverKey);

    Long expectedKey = Long.valueOf(3 * 5 * 5 + 4); // expected final position (3,4) facing E

    Placement roverPlacement = roverPathResolverService.getSortedRoverList().get(0).getPlacement();

    Assertions.assertEquals(expectedKey, finalRoverKey);
    Assertions.assertEquals(Heading.E, roverPlacement.getHeading());
    Assertions.assertEquals(3, roverPlacement.getXPosition());
    Assertions.assertEquals(4, roverPlacement.getYPosition());
  }

  @Test
  public void resolveRoverPathShouldReturnOutOfBoundsExceptionWhenGivenListOfMovementsOutsideGrid() {
    List<Movement> movements = FileUtilityService.resolveEvenLine("MMMMMM", 1);

    Long roverKey = roverPathResolverService.placeRover(new Placement(0, 0, Heading.E));
    Assertions.assertThrows(OutOfBoundsException.class, () -> {
      roverPathResolverService.resolveRoverPath(movements, roverKey);
    });
  }

  @Test
  public void resolveRoverPathShouldReturnCollisionExceptionWhenRoversMoveToSameGridPosition() {
    roverPathResolverService.placeRover(new Placement(0, 0, Heading.E));
    Long roverKey2 = roverPathResolverService.placeRover(new Placement(1, 0, Heading.E));

    List<Movement> crashMovements = FileUtilityService.resolveEvenLine("LLM", 1);

    Assertions.assertThrows(CollisionException.class, () -> {
      roverPathResolverService.resolveRoverPath(crashMovements, roverKey2);
    });
  }

  @Test
  public void getAllRoversShouldReturnOrderedRoversWhenCalled() {
    roverPathResolverService.placeRover(new Placement(0, 0, Heading.N));
    roverPathResolverService.placeRover(new Placement(2, 2, Heading.E));
    roverPathResolverService.placeRover(new Placement(3, 3, Heading.S));
    roverPathResolverService.placeRover(new Placement(2, 1, Heading.E));
    roverPathResolverService.placeRover(new Placement(3, 4, Heading.W));

    List<String> roverPositionList = roverPathResolverService.getAllRovers();

    Assertions.assertEquals("0 0 N", roverPositionList.get(0));
    Assertions.assertEquals("2 2 E", roverPositionList.get(1));
    Assertions.assertEquals("3 3 S", roverPositionList.get(2));
    Assertions.assertEquals("2 1 E", roverPositionList.get(3));
    Assertions.assertEquals("3 4 W", roverPositionList.get(4));
  }

}
