package com.rover.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeadingTest {

  @Test
  public void valueOfShouldReturnCorrectEnumWhenGivenValidString() {
    Heading heading1 = Heading.valueOf("N");
    Heading heading2 = Heading.valueOf("E");
    Heading heading3 = Heading.valueOf("S");
    Heading heading4 = Heading.valueOf("W");

    Assertions.assertEquals(Heading.N, heading1);
    Assertions.assertEquals(Heading.E, heading2);
    Assertions.assertEquals(Heading.S, heading3);
    Assertions.assertEquals(Heading.W, heading4);

  }

  @Test
  public void valueOfShouldReturnIllegalArgumentExceptionWhenGivenInvalidString() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Heading.valueOf("Z");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Heading.valueOf("");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Heading.valueOf(" ");
    });
  }

}
