package com.rover.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.rover.enums.Heading;
import com.rover.enums.Movement;
import com.rover.exceptions.InvalidFileFormatException;
import com.rover.exceptions.InvalidFilePathException;
import com.rover.models.Grid;
import com.rover.models.Placement;

public final class FileUtilityService {

  private FileUtilityService() {
    throw new IllegalArgumentException("Utility class");
  }

  public static final List<String> readFileLines(String filePath) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      return br.lines().collect(Collectors.toList());
    } catch (FileNotFoundException e) {
      throw new InvalidFilePathException(e);
    }
  }

  public static final Grid getDimensions(String firstLine) {
    List<Integer> dimensions;
    try {
      dimensions = Arrays.stream(firstLine.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    } catch (NumberFormatException e) {
      throw new InvalidFileFormatException("Invalid first line, it contains non-numeric characters");
    }
    validateDimensions(dimensions);
    return new Grid(dimensions.get(0), dimensions.get(1));
  }

  public static final Placement resolveOddLine(String oddLine, int lineNumber) {
    List<String> oddLineElements = Arrays.stream(oddLine.split(" ")).collect(Collectors.toList());
    if (oddLineElements.size() == 3) {
      try {
        int xPosition = Integer.parseInt(oddLineElements.get(0));
        int yPosition = Integer.parseInt(oddLineElements.get(1));
        Heading heading = Heading.valueOf(oddLineElements.get(2));
        return new Placement(xPosition, yPosition, heading);
      } catch (NumberFormatException e) {
        throw new InvalidFileFormatException(
            "Invalid x or y position, those values need to be numeral in line:" + lineNumber);
      } catch (IllegalArgumentException e) {
        throw new InvalidFileFormatException(
            "Invalid heading value in line " + lineNumber + " allowable values are: N, S, E, W ");
      }
    }
    throw new InvalidFileFormatException(
        "Invalid line " + lineNumber + "it needs to have two coordinates and a heading");
  }

  public static final List<Movement> resolveEvenLine(String evenLine, int lineNumber) {
    char[] movements = evenLine.toCharArray();
    List<Movement> movementList = new ArrayList<>();
    for (char move : movements) {
      try {
        movementList.add(Movement.valueOf(Character.toString(move)));
      } catch (IllegalArgumentException e) {
        throw new InvalidFileFormatException(
            "Invalid movement value in line " + lineNumber + " allowable values are: L, R, M", e);
      }
    }
    return movementList;
  }

  private static final void validateDimensions(List<Integer> dimensions) {
    if (dimensions.size() != 2) {
      throw new InvalidFileFormatException("Invalid grid dimensions in first line");
    }
  }
}
