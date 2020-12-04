package com.rover.models;

import java.util.Objects;

public final class Grid {

  private int xMaxPosition;
  private int yMaxPosition;

  public Grid() {
  }

  public Grid(int xMaxPosition, int yMaxPosition) {
    this.xMaxPosition = xMaxPosition;
    this.yMaxPosition = yMaxPosition;
  }

  public int getXMaxPosition() {
    return this.xMaxPosition;
  }

  public void setXMaxPosition(int xMaxPosition) {
    this.xMaxPosition = xMaxPosition;
  }

  public int getYMaxPosition() {
    return this.yMaxPosition;
  }

  public void setYMaxPosition(int yMaxPosition) {
    this.yMaxPosition = yMaxPosition;
  }

  public Grid xMaxPosition(int xMaxPosition) {
    this.xMaxPosition = xMaxPosition;
    return this;
  }

  public Grid yMaxPosition(int yMaxPosition) {
    this.yMaxPosition = yMaxPosition;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Grid)) {
      return false;
    }
    Grid grid = (Grid) o;
    return xMaxPosition == grid.xMaxPosition && yMaxPosition == grid.yMaxPosition;
  }

  @Override
  public int hashCode() {
    return Objects.hash(xMaxPosition, yMaxPosition);
  }

  @Override
  public String toString() {
    return "{" + " xMaxPosition='" + getXMaxPosition() + "'" + ", yMaxPosition='" + getYMaxPosition() + "'" + "}";
  }
}
