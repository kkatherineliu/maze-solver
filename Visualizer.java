// *** imports *** //
import javax.swing.*;
import java.awt.*;

/** Visualizer.java
  * Graphics showing the AI solve the maze live
  * @author Katherine Liu
  * @version 1.0 Nov 12, 2021
  */
public class Visualizer extends JFrame{
    /*
     * variables
     */
    public Maze maze;
    public MazePanel panel;
    public final int MAX_X = (int) getToolkit().getScreenSize().getWidth();
    public final int MAX_Y = (int) getToolkit().getScreenSize().getHeight();    
    public final int GRIDSIZE = MAX_Y/100;
    
    Visualizer (Maze maze) {
        this.maze = maze;
        this.panel = new MazePanel();
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(MAX_X/2, MAX_Y/2);
        this.setVisible(true);
    }
    
    /*
     * refreshes the screen to draw each step in the maze
     */
    private class MazePanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i<maze.getRows(); i++) {
                for (int j = 0; j<maze.getColumns(); j++) {
                    
                    // *** choosing grid colour based on maze layout *** //
                    if (maze.getMaze(i,j).equals(Constants.START)) {
                        g.setColor(Color.GREEN);
                    }
                    if (maze.getMaze(i,j).equals(Constants.END)) {
                        g.setColor(Color.RED);
                    }
                    if (maze.getMaze(i,j).equals(Constants.WALL)) {
                        g.setColor(Color.BLACK);
                    }
                    if (maze.getMaze(i,j).equals(Constants.SPACE)) {
                        g.setColor(Color.WHITE);
                    }
                    if (maze.getMaze(i,j).equals(Constants.VISIT)) {
                        g.setColor(Color.GRAY);
                    }
                    if (maze.getMaze(i,j).equals(Constants.DEAD_END)) {
                        g.setColor(Color.PINK);
                    }
                    
                    g.fillRect(GRIDSIZE*j,GRIDSIZE*i,GRIDSIZE,GRIDSIZE);
                }
            }
            this.repaint();
        }
    }
}