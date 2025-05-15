public class Rock extends Obstacle{
    public Rock(int id, int x, int y){
        super(id, x, y);
    }

    @Override
    public void bump(Robot robot){
        System.out.println("Robot" + robot.getColor() + "bumped into a rock!");
        robot.setExploded(false);
    }

    @Override
    public boolean wasExploded(){
        return false;
    }
}
