package com.rover;

import java.io.IOException;
import java.util.List;

import com.rover.enums.Movement;
import com.rover.models.Grid;
import com.rover.models.Placement;
import com.rover.service.FileUtilityService;
import com.rover.service.RoverPathResolverService;

public class App {
  public static void main(String[] args) throws IOException {
    String filePath = System.getProperty("pathToFile");

    List<String> file = FileUtilityService.readFileLines(filePath);
    Grid grid = FileUtilityService.getDimensions(file.get(0));
    RoverPathResolverService roverService = new RoverPathResolverService(grid);
    loopRovers(roverService, file);
    printRovers(roverService);
  }

  private static void printRovers(RoverPathResolverService roverService) {
    List<String> roverPositions = roverService.getAllRovers();
    for (String roverPosition : roverPositions) {
      System.out.println(roverPosition);
    }
  }

  private static void loopRovers(RoverPathResolverService roverService, List<String> file) {
    Placement roverPos;
    Long positionHash = Long.valueOf(0);
    List<Movement> roverMoves;
    for (int lineNumber = 1; lineNumber < file.size(); lineNumber++) {
      if ((lineNumber % 2) == 1) {
        roverPos = FileUtilityService.resolveOddLine(file.get(lineNumber), lineNumber);
        positionHash = roverService.placeRover(roverPos);
      } else {
        roverMoves = FileUtilityService.resolveEvenLine(file.get(lineNumber), lineNumber);
        roverService.resolveRoverPath(roverMoves, positionHash);
      }
    }
  }
}
