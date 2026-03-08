package it.unibo.pps.e2;

public interface Logics{
    
    /**
     * attempt to move Knight on position row,col, if possible
     *
     * @param type
     * @param row
     * @param col
     * @return whether the pawn has been hit 
     */
    boolean hit(String type, int row, int col);
    
    /**
     * @param type
     * @param row
     * @param col
     * @return whether position row,col has the knight
     */
    boolean hasPiece(String type, int row, int col);
}
