
package robot_world;

import java.io.IOException;

public class RobotWorld {
       
	// These are constants representing possible
	// chars in the world map

	public static final char SPACE = ' ';
	public static final char WALL = '#';
	public static final char GOAL = 'G';

	public static final char NORTH = '^';
	public static final char SOUTH = 'V';
	public static final char EAST = '>';
	public static final char WEST = '<';

	char directions[] = new char[]{NORTH, EAST, SOUTH, WEST};
	int currentDirection;
    
    // The map of the world
	private char[][] worldMap;
    
	// The dimensions of the world map
	private int maxRows, maxColumns;
	
	// The location of the robot
	private int robotRow, robotColumn;
    
	
    public RobotWorld(String mapFileName) throws IOException {
        
    	// This loads the file data into the world map
    	
        WorldMapReader wmr = new WorldMapReader();
        worldMap = wmr.readWorldMap(mapFileName);
        
        // This saves the dimensions of the world map
        maxRows = wmr.getMaxRows();
        maxColumns = wmr.getMaxColumns();
        
        // This sets the location of the robot to the initial position
        // shown in the file
        robotRow = wmr.getInitRobotRow();
        robotColumn = wmr.getInitRobotColumn();

        for (int i = 0; i < 4; ++i)
        {
            if (directions[i] == worldMap[robotRow][robotColumn])
            {
                currentDirection = i;
                break;
            }
        }
    }
    
 
    /**
     * Changes the direction of the robot by rotating it clockwise
     * 90 degrees
     */
    public void rotateClockwise() {
        currentDirection = (currentDirection + 1) % 4;
        worldMap[robotRow][robotColumn] = directions[currentDirection];
    }
    
    
    /**
     * Changes the direction of the robot by rotating it counter-
     * clockwise 90 degrees
     */
    public void rotateCounterClockwise() {
        
        // TODO: Fix me!!!  this now only works if the robot was facing south :)
        if (currentDirection == 0)
        {
            currentDirection = 3;
        }
        else
        {
            --currentDirection;
        }
        worldMap[robotRow][robotColumn] = directions[currentDirection];
    }
    
    
    /**
     * Moves the robot forward one place, as long as it is not 
     * directly in front of a wall (if it is, throw an exception).
     * Also outputs a message if the robot finds the goal
     */
    public void moveForward() {
        int moveX;
        int moveY;

        switch (directions[currentDirection])
        {
            case NORTH:
                moveY = -1;
                moveX = 0;
                break;
            case EAST:
                moveY = 0;
                moveX = 1;
                break;
            case SOUTH:
                moveY = 1;
                moveX = 0;
                break;
            default:
                moveY = 0;
                moveX = -1;
        }

        int nextX = robotColumn + moveX;
        int nextY = robotRow + moveY;

        if (worldMap[nextY][nextX] == WALL)
        {
            System.out.println("Trying to move into the wall");
            return;
        }

        if (worldMap[nextY][nextX] == GOAL)
        {
            System.out.println("Success");
        }

        worldMap[robotRow][robotColumn] = SPACE;
        worldMap[nextY][nextX] = directions[currentDirection];
        robotRow = nextY;
        robotColumn = nextX;
    }
    
    public void printCurrentWorldMap() {
    	
    	for (int i = 0; i < maxRows; i++) {
    		for (int j = 0; j < maxColumns; j++) {
    			System.out.print(worldMap[i][j]);
    		}
    		System.out.println();
    	}
    	System.out.println();
    }

    void moveBackwards()
    {

    }
}
