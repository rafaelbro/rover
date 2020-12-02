## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `lib`: the folder to maintain dependencies
- `src` : the folder to maintain sources
  - `enums` : this folder contains all the enums used in the project
  - `exceptions` : this folder contains custom exceptions
  - `models` : this folder contains objects and structures of this project
  - `service` : this folder contains classes that manipulate and facilitate the handling of data in the project
- `test` : this folder contains Unit tests for each class and a integration test of the app.

## Dependencies
-JUnit 5 for automated testing


## Assumption list
Considering the 2d grid, a rover cannot occupy or pass through the same space as another rover is parked on. That will throw an exception.
Going off grid is not a valid state and will throw and exception.
Line 0 need to have two integers separated by a space, not having that will throw a file format exception.
Line 1 and all subsequent odd lines will be 3 elements separated by a space, two integers and a character of allowable value = {W,E,N,S} respectively, anything differing from that pattern will throw an file out of expected format exception.
Line 2 and all subsequent even lines will be a list of movement comands with allowableValues = {L,R,M} any character diverging from that will throw an exception.
File path needs to be correctly provided, the program will throw an exception in case it does no find the file in the specified path.