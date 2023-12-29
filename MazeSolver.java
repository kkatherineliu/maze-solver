// *** imports ***//

import javax.swing.JOptionPane;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

/** MazeSolver.java
  * Takes in user input to choose a maze to solve
  * Calls the visualizer to see the AI solve the maze live
  * @author Katherine Liu
  * @version 1.0 Nov 12, 2021
  */
public class MazeSolver {
    /*
     * variables
     */
    public static Maze maze;
    public static Visualizer visualizer;
    public static final int DELAY = 50;
    
    public static void main(String[]args) {
        // setting up the maze to solve
        String file = JOptionPane.showInputDialog("Which city do you want to check? (exclude file extension): ");
        maze = new Maze(file);
        visualizer = new Visualizer(maze);
        
        // solving the maze
        findExit(maze, maze.getStart());
    }
    
    /*
     * findExit finds a path from the start to the end of the maze
     */
    public static boolean findExit(Maze maze, int[] location) {
        int row = location[Constants.ROW];
        int col = location[Constants.COL];
        
        // time delay for visualizer
        try {
            TimeUnit.MILLISECONDS.sleep(DELAY);
        } catch (InterruptedException e) {
        }
        
        // *** base case: reached the end *** //
        if (maze.getMaze(row,col).equals(Constants.END)) {
            return true;
        } else {
            if (location != maze.getStart()) {
                maze.setMaze(row,col,Constants.VISIT);
            }
            for (int i = -1; i<2; i+=2) {
                // *** moving left and right *** //
                if (maze.moveable(row, col+i) && findExit(maze, new int[]{row, col+i})) {
                    return true;
                }
                // *** moving up and down ***//
                if (maze.moveable(row+i, col) && findExit(maze, new int[]{row+i, col})) {
                    return true;
                }
            }
        }
        
        // time delay for visualizer
        try {
            TimeUnit.MILLISECONDS.sleep(DELAY/2);
        } catch (InterruptedException e) {
        }
        
        // *** base case: hitting a dead end *** //
        maze.setMaze(row,col, Constants.DEAD_END);
        return false;
    }
}