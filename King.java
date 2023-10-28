public class King extends Pieces {
    public King(String color,int x,int y) {
        super(color,x,y);
    }
    public boolean canMove(int x,int y,Board board){
        if(Math.abs(x-this.x)>1||Math.abs(y-this.y)>1)
            return false;
        if(board.isSpotFilledWithMe(x,y,this.color))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "K"+this.color.charAt(0);
    }
}
