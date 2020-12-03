package com.rover.service;

import java.io.IOException;
import java.util.List;

import com.rover.enums.Heading;
import com.rover.enums.Movement;
import com.rover.exceptions.InvalidFileFormatException;
import com.rover.exceptions.InvalidFilePathException;
import com.rover.models.Grid;
import com.rover.models.Placement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileUtilityServiceTest {

  private static final String PROJECT_RESOURCES = "\\src\\test\\java\\com\\rover\\resources\\";
  private static final String EXAMPLE_RESOURCE = "movements.txt";
  private static final String EXAMPLE_INVALID_RESOURCE = "invalid_resource.txt";

  @Test
  public void readFileLinesShouldReadLinesCorrectlyWhenGivenCorrectPath() throws IOException {
    List<String> fileLines = FileUtilityService.readFileLines(getResourcePath(EXAMPLE_RESOURCE));
    Assertions.assertEquals(5, fileLines.size());
    Assertions.assertEquals("5 5", fileLines.get(0));
    Assertions.assertEquals("MMRMMRMRRM", fileLines.get(fileLines.size() - 1));
  }

  @Test
  public void readFileLinesShouldThrowInvalidFilePathExceptionWhenGivenIncorrectPath() throws IOException {
    Assertions.assertThrows(InvalidFilePathException.class, () -> {
      FileUtilityService.readFileLines(getResourcePath(EXAMPLE_INVALID_RESOURCE));
    });
  }

  @Test
  public void getDimensionsShouldReturnCorrectDimensionsWhenGivenCorrectInput() {
    Grid grid = FileUtilityService.getDimensions("5 7");
    Assertions.assertEquals(5, grid.getXMaxPosition());
    Assertions.assertEquals(7, grid.getYMaxPosition());
  }

  @Test
  public void getDimensionsShouldThrowInvalidFileFormatExceptionWhenGivenMoreThan2Dimensions() {
    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.getDimensions("5 7 8");
    });
  }

  @Test
  public void getDimensionsShouldThrowInvalidFileFormatExceptionWhenGivenLessThan2Dimensions() {
    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.getDimensions("5");
    });
  }

  @Test
  public void getDimensionsShouldThrowInvalidFileFormatExceptionWhenGivenNonNumericDimensions() {
    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.getDimensions("a b");
    });
  }

  @Test
  public void getDimensionsShouldThrowInvalidFileFormatExceptionWhenGivenNonNumericDimensionsRegardlessOfAmount() {
    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.getDimensions("a");
    });
    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.getDimensions("a b c d");
    });
  }

  @Test
  public void resolveOddLineShouldReturnCorrectPlacementWhenGivenCorrectInput() {
    Placement placement = FileUtilityService.resolveOddLine("1 2 E", 1);

    Assertions.assertEquals(1, placement.getXPosition());
    Assertions.assertEquals(2, placement.getYPosition());
    Assertions.assertEquals(Heading.E, placement.getHeading());
  }

  @Test
  public void resolveOddLineShouldReturnCorrectPlacementWhenGivenCorrectUntrimmedInput() {
    Placement placement = FileUtilityService.resolveOddLine("     1 2 E       ", 1);

    Assertions.assertEquals(1, placement.getXPosition());
    Assertions.assertEquals(2, placement.getYPosition());
    Assertions.assertEquals(Heading.E, placement.getHeading());
  }

  @Test
  public void resolveOddLineShouldReturnInvalidFileFormatExceptionWhenGivenIncorrectInput() {

    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveOddLine("E 1 2", 1);
    });

    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveOddLine("1 2 Z", 1);
    });

    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveOddLine("2 E", 1);
    });

    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveOddLine("1 2 3 E", 1);
    });

    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveOddLine("", 1);
    });

    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveOddLine("12 E", 1);
    });
  }

  @Test
  public void resolveEvenLineShouldReturnListOfMovementsWhenGivenCorrectInput() {
    String movementString = "LRRMLM";
    List<Movement> movements = FileUtilityService.resolveEvenLine(movementString, 2);

    Assertions.assertEquals(movementString.length(), movements.size());
    Assertions.assertEquals(Movement.L, movements.get(0));
    Assertions.assertEquals(Movement.R, movements.get(1));
    Assertions.assertEquals(Movement.R, movements.get(2));
    Assertions.assertEquals(Movement.M, movements.get(3));
    Assertions.assertEquals(Movement.L, movements.get(4));
    Assertions.assertEquals(Movement.M, movements.get(5));
  }

  @Test
  public void resolveEvenLineShouldReturnNoMovementsWhenGivenEmptyLineAsInput() {
    String movementString = "";
    List<Movement> movements = FileUtilityService.resolveEvenLine(movementString, 2);

    Assertions.assertEquals(0, movements.size());
  }

  @Test
  public void resolveEvenLineShouldReturnNoMovementsWhenGivenBlankLineAsInput() {
    String movementString = "     ";
    List<Movement> movements = FileUtilityService.resolveEvenLine(movementString, 2);

    Assertions.assertEquals(0, movements.size());
  }

  @Test
  public void resolveEvenLineShouldThrowInvalidFileFormatExceptionWhenGivenIncorrectInput() {
    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveEvenLine("LRRMLMLMZ", 2);
    });

    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveEvenLine("LRRM LMLM", 2);
    });

    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      FileUtilityService.resolveEvenLine("ADSDA", 2);
    });
  }

  private String getResourcePath(String resourceName) {
    return System.getProperty("user.dir") + PROJECT_RESOURCES + resourceName;
  }

}