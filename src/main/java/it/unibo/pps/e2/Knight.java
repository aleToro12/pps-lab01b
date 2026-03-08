package it.unibo.pps.e2;

public class Knight extends Piece {
    public Knight(Pair<Integer, Integer> pos) {
        super(pos);
    }

    @Override
    public boolean canMoveTo(int row, int col) {
        int dx = Math.abs(this.getPos().getX() - row);
        int dy = Math.abs(this.getPos().getY() - col);
        return (dx != 0 && dy != 0 && dx + dy == 3);
    }
}