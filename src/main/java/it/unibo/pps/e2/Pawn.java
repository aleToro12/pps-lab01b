package it.unibo.pps.e2;

public class Pawn extends Piece {
    public Pawn(Pair<Integer, Integer> pos) {
        super(pos);
    }

    @Override
    public boolean canMoveTo(int row, int col) {
        return false;
    }
}