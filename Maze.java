// *** imports *** //
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import javax.swing.JOptionPane;
import java.util.Random;

/** Maze.java
  * Creates a 2D Array representation of a maze
  * Holds the start and end coordinates
  * @author Katherine Liu
  * @version 1.0 Nov 12, 2021
  */
public class Maze {
    /*
     * variables
     */
    private static String[][] maze;
    private static int rows, columns = 0;
    private static int[] start, end;
    
    Maze(String file) {
        loadMap(file);
        
        // generate start and end coordinates
        start = generateEnds(rows, columns);
        end = generateEnds(rows, columns);
        maze[start[Constants.ROW]][start[Constants.COL]] = Constants.START;
        maze[end[Constants.ROW]][end[Constants.COL]] = Constants.END;
    }
    
    /**
     * loadMap reads information from the .txt file to decide how large the rectangular map is
     * returns false if the wrong file was entered
     */
    public void loadMap(String file) {
        
        try {
            File map = new File (file + ".txt");
            Scanner readFile = new Scanner(map);
            String temp = "";
            
            // counting the number of rows and columns
            while (readFile.hasNext()) {
                temp = readFile.nextLine();
                rows++;
            }
            
            columns = temp.length();
            maze = new String[rows][columns];
            readFile.close();
            readFile = new Scanner(map);
            
            // filling the 2d array
            while (readFile.hasNext()) {
                for (int i = 0; i<rows; i++) {
                    maze[i] = readFile.nextLine().split("");
                }
            }
            readFile.close();
            
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "That map can not be found...\nMake sure to remove [.txt] from your input");
        }
    }
    
    /*
     * generateEnds randomly decides the beginning and end points
     */
    public static int[] generateEnds(int maxRow, int maxCol) {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(maxRow);
            col = rand.nextInt(maxCol);
        } while(!moveable(row, col));
        
        return new int[]{row, col};
    }
    
    /*
     * moveable checks if the next space is empty or the end
     */
    public static boolean moveable(int row, int col) {
        if ((maze[row][col].equals(Constants.SPACE)) || (maze[row][col].equals(Constants.END))) {
            return true;
        }
        return false;
    }
    
    /**
     * print displays the maze into the console
     */
    public void print() {
        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<columns; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
    
    // *** getters and setters *** //
    public String[][] getMaze() {
        return maze;
    }
    
    public String getMaze(int row, int col) {
        return maze[row][col];
    }
    
    public void setMaze(int row, int col, String item) {
        maze[row][col]= item;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    public int[] getStart() {
        return start;
    }
}

