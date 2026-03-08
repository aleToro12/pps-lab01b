# Exercise 2 Description
Consider the above [program](#program-description),
 which is already working ([`Main`](Main.java) should launch the described program)
 and is implemented with a GUI class
 and interface/implementation for the logic.
 The purpose of the exercise is to refactor only the **logic** part of the project
 (not the GUI, do not focus on it!) to improve its quality and achieve good testing of its various parts.

We propose the following TDD approach (but you can adapt it):

## Step 1: Seal ```LogicsImpl``` logic
1. Write some tests that pass on `Logics`/`LogicsImpl` and that capture the core of the logic's functionality
2. Implement any small additions to `Logics`/`LogicsImpl` needed to do so (e.g., additional constructors?)
3. Do not improve `Logics`/`LogicsImpl` at this point; we will do so later
4. Verify the system's functionality again

## Step 2: Refactor to improve design
1. Consider an aspect of `LogicsImpl`'s implementation that can be externalized from `LogicsImpl` (SRP), i.e., inserted into a new class which `LogicsImpl` will be composed of
2. Build the interface and its implementation of this new class with TDD, i.e.,
   with various red-green-refactor steps on the new class
3. Have `LogicsImpl` use this new class
4. Re-run the tests for `LogicsImpl` and also perform manual testing on the GUI

Repeat these steps until `LogicsImpl` has been significantly reduced.

## Step 3: Final refinements
At this point:
1. Use TDD to improve what remains of `LogicsImpl`
2. Create a UML diagram of the resulting system

# Program Description
The goal of this exercise is to create
a GUI with the appearance shown in the provided image:

![](https://user-images.githubusercontent.com/23448811/222983821-6b32db03-87fc-4bb6-9760-2e67c2f3f588.png)

it contains a chess-like square grid of buttons (5x5),
in which a pawn indicated with "*" and a knight indicated with "K"
are both randomly positioned at the start.

Pressing a button will move the knight to that position,
and this will work according to the rules of chess,
where knights only move with a unique movement of 2 squares
in one direction and 1 in the perpendicular direction.

Considering the figure,
where the knight is in 0-based position (4,3), i.e.,
the fourth column and the third row, for example,
it could only move to 3 squares,
i.e., (3,1) by capturing the pawn, (2,2) and (2,4).
Clicking on an invalid position should not do anything.
When the knight lands on the pawn, the application should be closed.

UML della logica implementata:

```mermaid
classDiagram
    class Logics {
        <<interface>>
        +hit(String type, int row, int col) boolean
        +hasPiece(String type, int row, int col) boolean
    }

    class LogicsImpl {
        -Map~String,Piece~ pieces
        -int size
        -Random random
        +LogicsImpl(int size)
        +LogicsImpl(int size, Piece knight, Piece pawn)
        -randomEmptyPosition() Pair
        -isPositionOccupied(Pair pos) boolean
    }

    class Piece {
        <<abstract>>
        -Pair~Integer,Integer~ pos
        +getPos() Pair
        +setPos(Pair pos)
        +canMoveTo(int row, int col)* boolean
    }

    class Knight {
        +canMoveTo(int row, int col) boolean
    }

    class Pawn {
        +canMoveTo(int row, int col) boolean
    }

    class Pair~X,Y~ {
-X x
-Y y
+getX() X
+getY() Y
+equals(Object o) boolean
}

Logics <|.. LogicsImpl : implements
LogicsImpl "1" *-- "many" Piece : manages
Piece <|-- Knight : extends
Piece <|-- Pawn : extends
Piece --> Pair : uses
LogicsImpl --> Pair : uses
```