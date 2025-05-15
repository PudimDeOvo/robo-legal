public class Bomb extends Obstacle{
    public Bomb(int id, int x, int y){
        super(id, x, y);
    }

    @Override
    public void bump(Robot robot){
        System.out.println("Robot" + robot.getColor() + "bumped!");
        robot.setExploded(true);
    }

    @Override
    public boolean wasExploded(){
        return true;
    }
}
