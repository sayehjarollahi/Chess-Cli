public abstract class Pieces {
    protected String color;
    protected boolean killed;
    protected int x;
    protected int y;

    public Pieces(String color,int x,int y) {
        this.color = color;
        this.killed = false ;
        this.x=x;
        this.y=y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean isKilled() {
        return killed;
    }

    public abstract boolean canMove(int x,int y,Board board);





}

