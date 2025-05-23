import java.util.List;

public class Grid {
    protected int x;
    protected int y;
    private static final int GRID_X = 0;
    private static final int GRID_Y = 3;
    private List<Obstacle> obstacles;
    private Food food;

    public Grid(List<Obstacle> obstacles, Food food){
        this.food = food;
        this.obstacles = obstacles;
    }

    // função para conferir se a posição está ocupada por outra coisa (comida, pedra...)
    public boolean isOccupied(int x, int y){
        for (Obstacle obs : obstacles){
            if (x == obs.getX() && y == obs.getY()){
                return true;
            }
        }
        if (x == food.getX() && y == food.getY()){
            return true;
        }
        return false;
    }

    // funções auxiliares 
    public boolean isRock(int x, int y){
        for (Obstacle obs : obstacles){
            if (x == obs.getX() && y == obs.getY() && obs instanceof Rock){
                return true;
            }
        }
        return false;
    }

    public boolean isBomb(int x, int y){
        for (Obstacle obs : obstacles){
            if (x == obs.getX() && y == obs.getY() && obs instanceof Bomb){
                return true;
            }
        }
        return false;
    }

    public void printPosition(int robotX, int robotY) {
        for (int i = 3; i >= GRID_X; i--) { // COLUNA 
            for (int j = 0; j <= GRID_Y; j++) { // LINHAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                if (j == robotX && i == robotY) {
                    System.out.print(" R ");
                } else if (obstacles!= null && isRock(j, i)) {
                    System.out.print(" @ ");
                } else if (obstacles!= null && isBomb(j, i)) {
                    System.out.print(" O ");
                } else if (j == food.getX() && i == food.getY()) {
                    System.out.print(" F ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println(); 
        }
        System.out.println(); 
    }
}
