package com.rover.service;

import java.util.HashSet;
import java.util.Set;

import com.rover.enums.Heading;
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
      roverPathResolverService.placeRover(new Placement(5, 1, Heading.N));
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
}
