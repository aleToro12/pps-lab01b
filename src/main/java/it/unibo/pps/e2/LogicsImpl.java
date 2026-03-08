package it.unibo.pps.e2;

import java.util.*;

public class LogicsImpl implements Logics {

    private final Map<String, Piece> pieces = new HashMap<>();
	private final Random random = new Random();
	private final int size;
	 
    public LogicsImpl(int size){
    	this.size = size;
        this.pieces.put("pawn", new Pawn(randomEmptyPosition()));
        this.pieces.put("knight", new Knight(randomEmptyPosition()));
    }

    //Ho introdotto questo costruttore per semplicità nei test
    public LogicsImpl(int size, Piece knight, Piece pawn) {
        this.size = size;
        this.pieces.put("knight", knight);
        this.pieces.put("pawn", pawn);
    }

    private Pair<Integer, Integer> randomEmptyPosition() {
        Pair<Integer, Integer> pos;
        do {
            pos = new Pair<>(random.nextInt(size), random.nextInt(size));
        } while (isPositionOccupied(pos));
        return pos;
    }

    private boolean isPositionOccupied(Pair<Integer, Integer> pos) {
        return pieces.values().stream().anyMatch(p -> p.getPos().equals(pos));
    }
    
	@Override
	public boolean hit(String type, int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}

        Piece piece = pieces.get(type);

		if (piece.canMoveTo(row, col)) {
			piece.setPos(new Pair<>(row,col));
			return pieces.values().stream()
                    .filter(entry -> !entry.equals(piece))
                    .anyMatch(p -> p.getPos().equals(piece.getPos()));
		}
		return false;
	}

    @Override
    public boolean hasPiece(String type, int row, int col) {
        Piece p = pieces.get(type);
        return p != null && p.getPos().equals(new Pair<>(row, col));
    }
}
