import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmartRobot extends Robot {
    public SmartRobot(int x, int y, String color, boolean hasFood, boolean exploded){
        super(x, y, color, false, false);
    }

    @Override
    public void move(int movement) throws InvalidMovementException{
        List<Integer> directions = new ArrayList<>(Arrays.asList(1, 2, 3, 4)); 
        Collections.shuffle(directions); // tenta direções de modo aleatório

        for (int dir : directions){
            try{
                super.move(dir);
                return; 
            } catch (InvalidMovementException e) {
                System.out.println("Direction " + dir + " failed."); // debug
            }
        }
        // se todas as direções falharem
        throw new InvalidMovementException("No movement available. The robot is stuck!");
    }
}
