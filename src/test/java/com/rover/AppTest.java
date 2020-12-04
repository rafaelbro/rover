package com.rover;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import com.rover.exceptions.CollisionException;
import com.rover.exceptions.InvalidFileFormatException;
import com.rover.exceptions.InvalidFilePathException;
import com.rover.exceptions.OutOfBoundsException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

  private static final String FILE_SEPARATOR = File.separator;
  private static final String PROJECT_RESOURCES = FILE_SEPARATOR + "src" + FILE_SEPARATOR + "test" + FILE_SEPARATOR
      + "java" + FILE_SEPARATOR + "com" + FILE_SEPARATOR + "rover" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR;
  private static final String FILE_PATH_SAMPLE = "movements.txt";
  private static final String FILE_PATH_OUTBOUND = "outOfBoundMovements.txt";
  private static final String FILE_PATH_INEXISTENT = "noFile.txt";
  private static final String FILE_PATH_WRONG_FORMAT = "invalidFileFormat.txt";
  private static final String FILE_PATH_COLLISION = "collisionFile.txt";

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void runningAppTestShouldReturnExpectedOutputWhenGivenInput1() throws IOException {
    System.setProperty("pathToFile", getResourcePath(FILE_PATH_SAMPLE));
    App.main(null);
    List<String> outputs = outContent.toString().lines().collect(Collectors.toList());

    Assertions.assertEquals("1 3 N", outputs.get(0));
    Assertions.assertEquals("5 1 E", outputs.get(1));
  }

  @Test
  public void runningAppTestShouldThrowOutOfBoundsExceptionWhenGivenPathOutOfBounds() throws IOException {
    System.setProperty("pathToFile", getResourcePath(FILE_PATH_OUTBOUND));
    Assertions.assertThrows(OutOfBoundsException.class, () -> {
      App.main(null);
    });
  }

  @Test
  public void runningAppTestShouldThrowFileNotFoundExceptionWhenGivenWrongFilePath() throws IOException {
    System.setProperty("pathToFile", getResourcePath(FILE_PATH_INEXISTENT));
    Assertions.assertThrows(InvalidFilePathException.class, () -> {
      App.main(null);
    });
  }

  @Test
  public void runningAppTestShouldThrowInvalidFileFormatExceptionWhenGivenWrongFileFormat() throws IOException {
    System.setProperty("pathToFile", getResourcePath(FILE_PATH_WRONG_FORMAT));
    Assertions.assertThrows(InvalidFileFormatException.class, () -> {
      App.main(null);
    });
  }

  @Test
  public void runningAppTestShouldThrowCollisionExceptionWhenGivenWrongFileFormat() {
    System.setProperty("pathToFile", getResourcePath(FILE_PATH_COLLISION));
    Assertions.assertThrows(CollisionException.class, () -> {
      App.main(null);
    });
  }

  private String getResourcePath(String resourceName) {
    return System.getProperty("user.dir") + PROJECT_RESOURCES + resourceName;
  }
}
