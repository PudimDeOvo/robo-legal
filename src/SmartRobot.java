import java.util.ArrayList;
import java.util.Random;

public class SmartRobot extends Robot {
    //boolean gaveUp;
    public SmartRobot(Grid grid, int x, int y, String color, boolean hasFood, boolean exploded){
        super(grid, x, y, color, false, false);
    }

    boolean failed = false;

    @Override
    public void move(int movement) throws InvalidMovementException{
        ArrayList<Integer> directions = new ArrayList<>(); 
        directions.add(1);
        directions.add(2);
        directions.add(3);
        directions.add(4);

        Random random = new Random();

        // simula uma randomização de movimento
        for (int i = 0; i < directions.size(); i++){
            int j = random.nextInt(directions.size());
            int temp = directions.get(i);
            directions.set(i, directions.get(j));
            directions.set(j, temp);
            // troca directions[i] e directions [j]
        }

        for (int dir : directions){
            try{
                super.move(dir);
                return; 
            } catch (InvalidMovementException e) {
                System.out.println("out of bound at (" + getX() + ", " + getY() + ").");
                throw new InvalidMovementException("is out of bounds!");
            }
        }
        // se todas as direções falharem
        throw new InvalidMovementException("No movement available. The robot is stuck!");
    }

    public boolean getFailed(){
        return failed;
    } 
}
