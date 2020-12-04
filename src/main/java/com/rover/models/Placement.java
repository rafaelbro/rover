package com.rover.models;

import java.util.Objects;

import com.rover.enums.Heading;

public final class Placement {

  private int xPosition;
  private int yPosition;
  private Heading heading;

  public Placement() {
  }

  public Placement(int xPosition, int yPosition, Heading heading) {
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.heading = heading;
  }

  public final int getXPosition() {
    return this.xPosition;
  }

  public final void setXPosition(int xPosition) {
    this.xPosition = xPosition;
  }

  public int getYPosition() {
    return this.yPosition;
  }

  public void setYPosition(int yPosition) {
    this.yPosition = yPosition;
  }

  public Heading getHeading() {
    return this.heading;
  }

  public void setHeading(Heading heading) {
    this.heading = heading;
  }

  public Placement xPosition(int xPosition) {
    this.xPosition = xPosition;
    return this;
  }

  public Placement yPosition(int yPosition) {
    this.yPosition = yPosition;
    return this;
  }

  public Placement heading(Heading heading) {
    this.heading = heading;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Placement)) {
      return false;
    }
    Placement placement = (Placement) o;
    return xPosition == placement.xPosition && yPosition == placement.yPosition
        && Objects.equals(heading, placement.heading);
  }

  @Override
  public int hashCode() {
    return Objects.hash(xPosition, yPosition, heading);
  }

  @Override
  public String toString() {
    return "{" + " xPosition='" + getXPosition() + "'" + ", yPosition='" + getYPosition() + "'" + ", heading='"
        + getHeading() + "'" + "}";
  }

}
