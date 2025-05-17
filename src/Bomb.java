public class Bomb extends Obstacle{
    public Bomb(int id, int x, int y){
        super(id, x, y);
    }

    @Override
    public void bump(Robot robot){
        try {
            System.out.println(robot.getColor() + "Robot bumped and exploded!");
            robot.setExploded(true);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean wasExploded(){
        return true;
    }
}
