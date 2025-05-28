import java.util.Random;

public class SmartRobot extends Robot {
    //boolean gaveUp;
    public SmartRobot(Grid grid, int x, int y, String color, boolean hasFood, boolean exploded){
        super(grid, x, y, color, false, false);
    }

    @Override
    public void move(int movement) throws InvalidMovementException{
        int turns = 0;
        int lastDir = 0;

        Random random = new Random();

        while (turns < 20){
            int oldX = getX();
            int oldY = getY();
            int randomMove = random.nextInt(1, 5);
            while (randomMove == lastDir){
                randomMove = random.nextInt(1, 5); // só pra evitar repetir a mesma direção
            }
            try{
                super.move(randomMove);
                return; // funciona
            } catch (InvalidMovementException e){
                turns++;
                lastDir = randomMove;
            }
            if (oldX == getX() && oldY == getY()){
                throw new InvalidMovementException("Out of bounds!");
            }
        }
        throw new InvalidMovementException("No movement available. The robot is stuck!");
    }
}
