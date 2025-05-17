import java.util.List;

public class Grid {
    protected int x;
    protected int y;
    //private static final int GRID_LIMIT = 3;
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
}
