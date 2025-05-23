public class Robot{
    private int x;
    private int y;
    private String color;
    private boolean hasFood;
    private boolean exploded;
    private static final int GRID_LIMIT = 3;


    public Robot(int x, int y, String color, boolean hasFood, boolean exploded){
        this.x = 0;
        this.y = 0;
        this.color = color;
        this.hasFood = false;
        this.exploded = false;

    }

    public int getX(){
        return x;
    }

    public void setX(int x) throws InvalidMovementException{
        if (x >= 0 && x <= GRID_LIMIT){
            this.x = x;
        } else {
            throw new InvalidMovementException("Coordinates must be between 0 and 4.");
        }
    }

    public int getY(){
        return y;
    }

    public void setY(int y) throws InvalidMovementException{
        if (y >= 0 && y <= GRID_LIMIT){
            this.y = y;
        } else {
            throw new InvalidMovementException("Coordinates must be between 0 and 4.");
        }
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public boolean getFood(){
        return hasFood;
    }

    public boolean getExploded(){
        return exploded;
    }

    public void getSensors(){
        System.out.println("exploded: " + getExploded() + ", food: " + getFood());
    }

    public boolean foundFood(Food food){
        if (this.x == food.getX() && this.y == food.getY()){
            this.hasFood = true;
            return true;
        }
        return false;
    }

    public boolean wasExploded(Bomb bomb) {
        if (this.x == bomb.getX() && this.y == bomb.getY()){
            this.exploded = true;
            return  true;
        }
        return false;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
    
    public void move(String movement) throws InvalidMovementException{
        switch(movement.toLowerCase()){
            case "up" -> {
                if(y+1 <= GRID_LIMIT){
                    y++;
                    System.out.println("new coordinates: (" + x + ", " + y + ").");
                } else {
                    System.out.println("out of bound at (" + x + ", " + y + ").");
                    throw new InvalidMovementException("Out of bounds!");
                }
            }

            case "down" -> {
                if (y-1 >= 0){
                    y--;
                    System.out.println("new coordinates: (" + x + ", " + y + ").");
                } else {
                    System.out.println("out of bound at (" + x + ", " + y + ").");
                    throw new InvalidMovementException("Out of bounds!");
                }
            }

            case "right" -> {
                if (x+1 <= GRID_LIMIT){
                    x++;
                    System.out.println("new coordinates: (" + x + ", " + y + ").");
                } else {
                    System.out.println("out of bound at (" + x + ", " + y + ").");
                    throw new InvalidMovementException("Out of bounds!");
                }
            }

            case "left" -> {
                if (x-1 >= 0){
                    x--;
                    System.out.println("new coordinates: (" + x + ", " + y + ").");
                } else {
                    System.out.println("out of bound at (" + x + ", " + y + ").");
                    throw new InvalidMovementException("Out of bounds!");
                }
            }
            default -> throw new InvalidMovementException("Invalid input.");
        }
    }

    public void move(int movement) throws InvalidMovementException{
        switch(movement){
            case 1 -> {
                if(y+1 <= GRID_LIMIT){
                    y++;
                    System.out.println("new coordinates: (" + x + ", " + y + ").");
                } else {
                    System.out.println("out of bound at (" + x + ", " + y + ").");
                    throw new InvalidMovementException("Out of bounds!");
                }
            }

            case 2 -> {
                if (y-1 >= 0){
                    y--;
                    System.out.println("new coordinates: (" + x + ", " + y + ").");
                } else {
                    System.out.println("out of bound at (" + x + ", " + y + ").");
                    throw new InvalidMovementException("coordinate Y is out of bounds!");
                }
            }

            case 3 -> {
                if (x+1 <= GRID_LIMIT){
                    x++;
                    System.out.println("new coordinates: (" + y + ", " + x + ").");
                } else {
                    System.out.println("out of bound at (" + x + ", " + y + ").");
                    throw new InvalidMovementException("coordinate X is out of bounds!");
                }
            }

            case 4 -> {
                if (x-1 >= 0){
                    x--;
                    System.out.println("new coordinates: (" + getX() + ", " + getY() + ").");
                } else {
                    System.out.println("out of bound at (" + x + ", " + y + ").");
                    throw new InvalidMovementException("coordinate X is out of bounds!");
                }
            }
            default -> throw new InvalidMovementException("Invalid input.");
        }
    }

}