package com.rover.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MovementTest {

  @Test
  public void valueOfShouldReturnCorrectEnumWhenGivenValidString() {
    Movement movement1 = Movement.valueOf("M");
    Movement movement2 = Movement.valueOf("L");
    Movement movement3 = Movement.valueOf("R");

    Assertions.assertEquals(Movement.M, movement1);
    Assertions.assertEquals(Movement.L, movement2);
    Assertions.assertEquals(Movement.R, movement3);
  }

  @Test
  public void valueOfShouldReturnIllegalArgumentExceptionWhenGivenInvalidString() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Movement.valueOf("Z");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Movement.valueOf("");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Movement.valueOf(" ");
    });
  }

}
