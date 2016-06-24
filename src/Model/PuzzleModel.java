package Model;
/**
 * Prabhat Ranjan
 * M.SC(CCN),Telecom SudParis
 * Date: 12/02/2016
 * */


import java.lang.Math;
/////////////////////////////////////////// class PuzzleModel
public class PuzzleModel {
    private static final int ROWS = 4;
    private static final int COLS = 4;
    public float fitness_score=0.0f;
    public Tile[][] _contents;  // All tiles.
    public Tile _emptyTile; // The empty space.
    
    //================================================= constructor
    public PuzzleModel() {
        _contents = new Tile[ROWS][COLS];
        reset();             // Initialize and shuffle tiles.
    }//end constructor
    
  //===================================================Parameterized constructor
    public PuzzleModel(int ROWS, int COLS){
    	_contents = new Tile[ROWS][COLS];
    	for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
                _contents[r][c] = new Tile(r, c, "" + (r*COLS+c+1));
            }
        }
        //--- Set last tile face to null to mark empty space
        _emptyTile = _contents[ROWS-1][COLS-1];
        _emptyTile.setFace(null);
    }
    
    //===================================================== getFace
    // Return the string to display at given row, col.
    public String getFace(int row, int col) {
        return _contents[row][col].getFace();
    }//end getFace
    
    //======================================================= reset
    // Initialize and shuffle the tiles.
    public void reset() {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
                _contents[r][c] = new Tile(r, c, "" + (r*COLS+c+1));
            }
        }
        //--- Set last tile face to null to mark empty space
        _emptyTile = _contents[ROWS-1][COLS-1];
        _emptyTile.setFace(null);
        
        //-- Shuffle - Exchange each tile with random tile.
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
                exchangeTiles(r, c, (int)(Math.random()*ROWS)
                                  , (int)(Math.random()*COLS));
            }
        }
    }//end reset
    
    //===========================================get_position
    // To get the position of a given number in array
    // Return integer array to index
    public int[] get_position(int num){
    	int i,j = 0,score = 0;
    	int []array = new int[2];
    	for(i=0;i<ROWS;i++){
    		for(j=0;j<COLS;j++)
    		{
    			if (Integer.parseInt(_contents[i][j].getFace()) == num ){
    				score++;
    				fitness_score = (float) (score/16.0);
    				array[0]=i;
    				array[1]=j;
    			}
    		}
    	}
    	
    	return array;
    }//end of get_position
    
  //===================================================================fitnessFunction1
    //To find the fitness score based on each tile and summing up at the end 
    //where 0 is fully solved puzzle with all tiles positioned correctly and 1 with all tiles positioned wrongly.
    
    public float fitnessFunction1(PuzzleModel puzzleModel){
    	int i=0, score=0;
    	PuzzleModel solved = new PuzzleModel(4,4);
    	int [] solved_index;
    	int [] index;
    	for (i=0;i<16;i++){
    		solved_index = solved.get_position(i);
    		index= puzzleModel.get_position(i);
    		if(solved_index!=index){
    			score++;
    		}
    		fitness_score = (float) ((float)score/16.0);
    	}
    	return fitness_score;
    }//end of fitnessFunction1
        
    //===================================================================fitnessFunction2
    // find the fitness score based on distance from positions; Every tile can be positioned in 4 ways
    // Return float value of fitness_score
    public float fitnessFunction2(PuzzleModel puzzleModel){
    	//fixed position for empty tile
    	int []position1 = new int[2];
    	int []position2 = new int[2];
    	float diff = 0;
    	float fitness_score = 0;
    	PuzzleModel solved = new PuzzleModel(4,4);
    	for (int i=0;i<16;){
    		position1=puzzleModel.get_position(i);
    		position2=solved.get_position(i);
    		if(solved.get_position(i)!=puzzleModel.get_position(i))
    		{
    			if(position1[0]==position2[0]){
    			diff=Math.abs(position1[1]-position2[1]);
    			}
    			else if (position1[1]==position2[1]){
    			diff=Math.abs(position1[0]-position2[0]);
    			}
    			else{
    				diff=Math.abs(position1[0]-position2[0])+Math.abs(position1[1]-position2[1]);
    			}
    		}
    		fitness_score=(float) (diff/64.0);
    		return fitness_score;
    	}
		return fitness_score;
    }//end of fitnessFunction2
    
    
   //==================================================== Fitness function3
   //To find the average of fitness score obtained by fitness function 1 and fitness function 2
   // Return average fitness_score
   public float fitnessFunction3(PuzzleModel puzzleModel){
	return (float)(puzzleModel.fitnessFunction1(puzzleModel)+puzzleModel.fitnessFunction2(puzzleModel)/2.0);   
   }//end of fitnessFunction3
   
    //==================================================== moveTile
    // Move a tile to empty position beside it, if possible.
    // Return true if it was moved, false if not legal.
    public boolean moveTile(int r, int c) {
        //--- It's a legal move if the empty cell is next to it.
        return checkEmpty(r, c, -1, 0) || checkEmpty(r, c, 1, 0)
            || checkEmpty(r, c, 0, -1) || checkEmpty(r, c, 0, 1);
    }//end moveTile
    
    //================================================== checkEmpty
    // Check to see if there is an empty position beside tile.
    // Return true and exchange if possible, else return false.
    private boolean checkEmpty(int r, int c, int rdelta, int cdelta) {
        int rNeighbor = r + rdelta;
        int cNeighbor = c + cdelta;
        //--- Check to see if this neighbor is on board and is empty.
        if (isLegalRowCol(rNeighbor, cNeighbor) 
                  && _contents[rNeighbor][cNeighbor] == _emptyTile) {
            exchangeTiles(r, c, rNeighbor, cNeighbor);
            return true;
        }
        return false;
    }//end checkEmpty
    
    
    //=============================================== isLegalRowCol
    // Check for legal row, col
    public boolean isLegalRowCol(int r, int c) {
        return r>=0 && r<ROWS && c>=0 && c<COLS;
    }//end isLegalRowCol
    
    //=============================================== exchangeTiles
    // Exchange two tiles.
    public void exchangeTiles(int r1, int c1, int r2, int c2) {
        Tile temp = _contents[r1][c1];
        _contents[r1][c1] = _contents[r2][c2];
        _contents[r2][c2] = temp;
    }//end exchangeTiles
        
    //=================================================== isGameOver
    @SuppressWarnings("unused")
	public boolean isGameOver() {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<ROWS; c++) {
                Tile trc = _contents[r][c];
                return trc.isInFinalPosition(r, c);
            }
        }
        
        //--- Falling through loop means nothing out of place.
        return true;
    }//end isGameOver
}//end class PuzzleModel