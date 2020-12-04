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
    for (int lineNumber = 1; lineNumber < file.size(); lineNumber += 2) {
      Placement roverPos = FileUtilityService.resolveOddLine(file.get(lineNumber), lineNumber);
      List<Movement> roverMoves = FileUtilityService.resolveEvenLine(file.get(lineNumber + 1), lineNumber + 1);
      Long positionHash = roverService.placeRover(roverPos);
      roverService.resolveRoverPath(roverMoves, positionHash);
    }
    List<String> roverPositions = roverService.getAllRovers();
    for (String roverPosition : roverPositions) {
      System.out.println(roverPosition);
    }
  }
}
