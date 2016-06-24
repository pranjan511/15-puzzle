package Model;
/**
 * Prabhat Ranjan
 * M.SC(CCN),Telecom SudParis
 * Date: 12/02/2016
 * */
////////////////////////////////////////////////////////// class Tile
// Represents the individual "tiles" that slide in puzzle.
public class Tile {
    //============================================ instance variables
    private int _row;     // row of final position
    private int _col;     // col of final position
    private String _face;  // string to display 
    //end instance variables
    
    //==================================================== constructor
    public Tile(int row, int col, String face) {
        _row = row;
        _col = col;
        _face = face;
    }//end constructor
    
    //======================================================== setFace
    public void setFace(String newFace) {
        _face = newFace;
    }//end getFace
    
    //======================================================== getFace
    public String getFace() {
        return _face;
    }//end getFace
   
    //=============================================== isInFinalPosition
    public boolean isInFinalPosition(int r, int c) {
        return r==_row && c==_col;
    }//end isInFinalPosition
}//end class Tile