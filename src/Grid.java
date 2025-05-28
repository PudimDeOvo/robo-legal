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
    public List<Obstacle> getObstacles(){
        return obstacles;
    }

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

    public String colorOfRobot(String text, String color){
        String colorCode = switch (color.toLowerCase()){
            case "red" -> "\u001B[31m";
            case "blue" -> "\u001B[34m";
            case "orange" -> "\u001B[33m"; // aparentemente não tem laranja :(
            default -> "\u001B[37m";
        };
        String resetCode = "\u001B[0m";
        return colorCode + text + resetCode;
    }

    public void printPosition(int robotX, int robotY, String robotColor) {
        for (int i = 3; i >= GRID_X; i--) { // COLUNA 
            for (int j = 0; j <= GRID_Y; j++) { // LINHA
                if (obstacles!= null && isRock(j, i) && j == robotX && i == robotY) {
                    System.out.print(" " + colorOfRobot("R", robotColor) + "@ ");
                } else if (j == robotX && i == robotY) {
                    System.out.print(" " + colorOfRobot("R", robotColor) + " ");
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
