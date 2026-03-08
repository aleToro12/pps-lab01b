package it.unibo.pps.e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LogicsTest {
    private static final int SIZE = 5;
    private Logics logics;
    private final Pair<Integer, Integer> knightPos = new Pair<>(2, 2);
    private final Pair<Integer, Integer> pawnPos = new Pair<>(4, 3);

    @BeforeEach
    void setUp() {
        logics = new LogicsImpl(SIZE, new Knight(knightPos), new Pawn(pawnPos));
    }

    private void assertPosition(String type, int row, int col) {
        assertTrue(logics.hasPiece(type, row, col), type + " dovrebbe essere in " + row + "," + col);
    }

    @Test
    void testSuccessfulCapture() {
        assertTrue(logics.hit("knight", 4, 3));
        assertPosition("knight", 4, 3);
    }

    @Test
    void testMoveWithoutCapture() {
        assertFalse(logics.hit("knight", 0, 1));
        assertPosition("knight", 0, 1);
    }

    @Test
    void testInvalidMoveStaysPut() {
        assertFalse(logics.hit("knight", 0, 0));
        assertPosition("knight", 2, 2);
    }

    @Test
    void testBoundaries() {
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit("knight", -1, SIZE));
        assertThrows(IndexOutOfBoundsException.class, () -> logics.hit("knight", SIZE, 0));
    }

    @Test
    void testRandomPlacementIsConsistent() {
        Logics l = new LogicsImpl(SIZE);

        Pair<Integer, Integer> p1 = findPiece(l, "knight");
        Pair<Integer, Integer> p2 = findPiece(l, "pawn");

        assertNotNull(p1);
        assertNotNull(p2);
        assertNotEquals(p1, p2, "I pezzi non devono sovrapporsi");
    }

    private Pair<Integer, Integer> findPiece(Logics l, String type) {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (l.hasPiece(type, r, c)) return new Pair<>(r, c);
        return null;
    }
}