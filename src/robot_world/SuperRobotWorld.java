
package robot_world;

import custom.collections.LinkedListStack;

import java.io.IOException;

public class SuperRobotWorld extends RobotWorld {

	// TODO: Add some fields to keep track of commands
    private class Command
    {
        public static final int MOVE = 0;
        public static final int ROTATECLOCKWISE = 1;
        public static final int ROTATECOUNTERCLOCKWISE = 2;
        public int type;
    }

    private LinkedListStack<Command> prevCommands;
    private LinkedListStack<Command> prevUndoCommands;
	
    public SuperRobotWorld(String mapFile) throws IOException {
        super(mapFile);
        prevCommands = new LinkedListStack<>();
        prevUndoCommands = new LinkedListStack<>();
    }

    @Override
    public void rotateClockwise() {
        Command cmd = new Command();
        cmd.type = Command.ROTATECLOCKWISE;
        prevCommands.push(cmd);
        super.rotateClockwise();
    }

    @Override
    public void rotateCounterClockwise() {
        Command cmd = new Command();
        cmd.type = Command.ROTATECOUNTERCLOCKWISE;
        prevCommands.push(cmd);
        super.rotateCounterClockwise();
    }

    @Override
    public void moveForward() {
        Command cmd = new Command();
        cmd.type = Command.MOVE;
        prevCommands.push(cmd);
        super.moveForward();
    }

    /**
     * Undo the last move or rotation command that put the robot
     * in its current state.  If no commands have been issued yet,
     * do nothing.
     */
    private void revertCommand(Command cmd)
    {
        switch (cmd.type)
        {
            case Command.ROTATECLOCKWISE:
                super.rotateCounterClockwise();
                break;
            case Command.ROTATECOUNTERCLOCKWISE:
                super.rotateClockwise();
                break;
            default:
                super.rotateClockwise();
                super.rotateClockwise();
                super.moveForward();
                super.rotateClockwise();
                super.rotateClockwise();
                break;
        }
    }

    public void undoCommand() {
        Command cmd = prevCommands.pop();
        prevUndoCommands.push(cmd);
        revertCommand(cmd);
    }
    
    /**
     * Undo the last n commands.  Do nothing if n is zero or negative.
     * 
     * @param n the number of commands to undo
     */
    public void undoCommands(int n) {
    	for (int i = 0; i < n; ++i)
        {
            undoCommand();
        }
    }
    
    /**
     * For previously undone commands, redo the last command that was
     * undone
     */
    public void redoUndoneCommand() {
        Command cmd = prevUndoCommands.pop();
        switch (cmd.type)
        {
            case Command.MOVE:
                super.moveForward();
                break;
            case Command.ROTATECLOCKWISE:
                super.rotateClockwise();
                break;
            default:
                super.rotateCounterClockwise();
                break;
        }
    }
    
    /**
     * Redo the last n undone commands.  Do nothing if n is zero or negative.
     * 
     * @param n the number of commands to redo
     */
    public void redoUndoneCommands(int n) {
    	for (int i = 0; i < n; ++i)
        {
            redoUndoneCommand();
        }
    }
}
