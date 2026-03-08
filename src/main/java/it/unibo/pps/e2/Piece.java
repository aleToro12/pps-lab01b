package it.unibo.pps.e2;

public abstract class Piece {
    private Pair<Integer, Integer> pos;

    public Piece(Pair<Integer, Integer> pos) {
        this.pos = pos;
    }

    public Pair<Integer, Integer> getPos() {
        return pos;
    }

    public void setPos(Pair<Integer, Integer> pos) {
        this.pos = pos;
    }

    public abstract boolean canMoveTo(int row, int col);
}