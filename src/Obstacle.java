public abstract class Obstacle {
    protected int x;
    protected int y;
    protected int id;

    public Obstacle(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;

    }

    public int getID(){
        return id;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public abstract void bump(Robot robot);

    public abstract boolean wasExploded();
}
