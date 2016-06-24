package Model;
/**
 * Prabhat Ranjan
 * M.SC(CCN),Telecom SudParis
 * Date: 12/02/2016
 * */
////////////////////////////////////////////class Puzzle
import javax.swing.JFrame;

public class Puzzle {

//show the next step
//============================================= method main
public static void main(String[] args) {
JFrame window = new JFrame("Puzzle");
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
window.setContentPane(new PuzzleGUI());//Create new puzzle set
window.pack();// finalize layout
window.setVisible(true); // make window visible
window.setResizable(true);
}//end main
}//end class Puzzle