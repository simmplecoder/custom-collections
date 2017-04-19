
package robot_world;

public class Tester {
    
    public static void main(String[] args) throws Exception {
        
        SuperRobotWorld rw = new SuperRobotWorld("src/robot_world/map1.txt");
        rw.printCurrentWorldMap();

        System.out.println("Moving forward");
        rw.moveForward();
        rw.printCurrentWorldMap();

        System.out.println("Rotation clockwise");
        rw.rotateClockwise();
        rw.printCurrentWorldMap();
        
        for (int i = 0; i < 5; i++) {
            System.out.println("Moving forward");
        	rw.moveForward();
        	rw.printCurrentWorldMap();
        }

        for (int i = 0; i < 7; ++i)
        {
            System.out.println("Undoing commands");
            rw.undoCommand();
            rw.printCurrentWorldMap();
        }

        for (int i = 0; i < 7; ++i)
        {
            System.out.println("Redoing commands");
            rw.redoUndoneCommand();
            rw.printCurrentWorldMap();
        }
    }
}
