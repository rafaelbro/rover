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
 - Considering the 2d grid, a rover cannot occupy or pass through the same space as another rover is parked on. That will throw an exception.
 - Going off grid is not a valid state and will throw and exception.
 - Line 0 need to have two integers separated by a space, not having that will throw a file format exception.
 - Line 1 and all subsequent odd lines will be 3 elements separated by a space, two integers and a character of allowable value = {W,E,N,S}respectively, anything differing from that pattern will throw an file out of expected format exception.
 - Line 2 and all subsequent even lines will be a list of movement comands with allowableValues = {L,R,M} any character diverging from that will throw an exception. It is a valid assumption that even lines may come empty, meaning that the rover wont move.
 - File path needs to be correctly provided, the program will throw an exception in case it does no find the file in the specified path.
 - A 5x6 grid is able to place in x from 0 to 4 and in y from 0 to 5
 - Each rover occupies a unique position in a hash map, their key is a long calculated using the formula: xposition*((Grid X limit)*(Grid Y limit)) + yposition. 
    - Java specifies a long as 8 bytes, taking off all the negative numbers the domain would be from 0 to 9,223,372,036,854,775,807.
    - This gives us an inequation that needs to be respected: (xGridSize^2 * yGridSize) + yGridSize < 9,223,372,036,854,775,807.
  

