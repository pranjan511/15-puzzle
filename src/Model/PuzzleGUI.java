package Model;
//PuzzleGUI class creates a panel which 
//  contains two subpanels.
//  1. In the north is a subpanel for controls (just a button now).
//  2. In the center a graphics
//This needs a few improvements.  
//Both the PuzzleGUI and PuzzleModel define the number or rows and columns.
//How would you set both from one place? 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/////////////////////////////////////////////////// class SlidePuzzleGUI
//This class contains all the parts of the GUI interface
@SuppressWarnings("serial")
public class PuzzleGUI extends JPanel {
 //=============================================== instance variables
 private GraphicsPanel _puzzleGraphics;
 private PuzzleModel _puzzleModel = new PuzzleModel();
 //end instance variables

 //====================================================== constructor
 public PuzzleGUI() {
     //--- Create New Game button.  Add a listener to it.
     JButton newGameButton = new JButton("New Game");
     newGameButton.addActionListener(new NewGameAction());
     
   //--- Create Solve button.  Add a listener to it.
     JButton solveButton = new JButton("Solve");
     solveButton.addActionListener(new SolveAction());
     
     //--- Create control panel
     JPanel controlPanel = new JPanel();
     controlPanel.setLayout(new FlowLayout());
     controlPanel.add(newGameButton);
     
   //--- Create control panel
     JPanel controlPanel1 = new JPanel();
     controlPanel1.setLayout(new FlowLayout());
     controlPanel1.add(solveButton);
     
     //--- Create graphics panel
     _puzzleGraphics = new GraphicsPanel();
     
     //--- Set the layout and add the components
     this.setLayout(new BorderLayout());
     this.add(controlPanel, BorderLayout.NORTH);
     this.add(controlPanel1, BorderLayout.SOUTH);
     this.add(_puzzleGraphics, BorderLayout.CENTER);
 }//end constructor

 //////////////////////////////////////////////// class GraphicsPanel
 // This is defined inside the outer class so that
 // it can use the outer class instance variables.
 class GraphicsPanel extends JPanel implements MouseListener {
     private static final int ROWS = 4;
     private static final int COLS = 4;
     
     private static final int CELL_SIZE = 80; // Pixels
     private Font _biggerFont;
     
     //================================================== constructor
     public GraphicsPanel() {
         _biggerFont = new Font("SansSerif", Font.BOLD, CELL_SIZE/2);
         this.setPreferredSize(
                new Dimension(CELL_SIZE * COLS, CELL_SIZE*ROWS));
         this.setBackground(Color.black);
         this.addMouseListener(this);  // Listen own mouse events.
     }//end constructor
       
     //=======================================x method paintComponent
     public void paintComponent(Graphics g) {
         super.paintComponent(g);
         for (int r=0; r<ROWS; r++) {
             for (int c=0; c<COLS; c++) {
                 int x = c * CELL_SIZE;
                 int y = r * CELL_SIZE;
                 String text = _puzzleModel.getFace(r, c);
                 if (text != null) {
                     g.setColor(Color.gray);
                     g.fillRect(x+3, y+3, CELL_SIZE-4, CELL_SIZE-4);
                     g.setColor(Color.black);
                     g.setFont(_biggerFont);
                     g.drawString(text, x+10, y+(3*CELL_SIZE)/4);
                 }
             }
         }
     }//end paintComponent
     //======================================== listener mousePressed
     public void mousePressed(MouseEvent e) {
         //--- map x,y coordinates into a row and col.
         int col = e.getX()/CELL_SIZE;
         int row = e.getY()/CELL_SIZE;
         
         if (!_puzzleModel.moveTile(row, col)) {
             // moveTile moves tile if legal, else returns false.
             Toolkit.getDefaultToolkit().beep();
         }
         
         this.repaint();  // Show any updates to model.
     }//end mousePressed
    //========================================== ignore these events
     public void mouseClicked (MouseEvent e) {}
     public void mouseReleased(MouseEvent e) {}
     public void mouseEntered (MouseEvent e) {}
     public void mouseExited  (MouseEvent e) {}
 }//end class GraphicsPanel
 
 /**
  * Prabhat Ranjan
  * M.SC(CCN),Telecom SudParis
  * Date: 12/02/2016
  * */
 ////////////////////////////////////////// inner class NewGameAction
 public class NewGameAction implements ActionListener {
     public void actionPerformed(ActionEvent e) {
         _puzzleModel.reset();
         _puzzleGraphics.repaint();
     }
 }//end inner class NewGameAction
 
//////////////////////////////////////////inner class SolveAction
public class SolveAction implements ActionListener {
public void actionPerformed(ActionEvent e) {
float Result1=_puzzleModel.fitnessFunction1(_puzzleModel);
System.out.println(Result1);
float Result2=_puzzleModel.fitnessFunction2(_puzzleModel);
float Result3=_puzzleModel.fitnessFunction3(_puzzleModel);
if (Result1<=Result2 && Result1<=Result3){
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
			_puzzleModel.moveTile(i, j);
			if(_puzzleModel.moveTile(i, j)){
				System.out.println("["+(++i)+"]["+(++j)+"] to empty position");
			}
		}
	}
}
else if (Result2<= Result3 && Result2<=Result1 ){
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
			_puzzleModel.moveTile(i, j);
			if(_puzzleModel.moveTile(i, j)){
				System.out.println("["+(++i)+"]["+(++j)+"] to empty position");
			}
		}
	}
}
else{
	for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
			_puzzleModel.moveTile(i, j);
			if(_puzzleModel.moveTile(i, j)){
				System.out.println("["+(++i)+"]["+(++j)+"] to empty position");
			}
		}
	}
}	
}
}//end inner class SolveAction

}//end class PuzzleGUI