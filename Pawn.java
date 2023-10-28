public class Pawn extends Pieces {
    public Pawn(String color, int x, int y) {
        super(color, x, y);
    }

    public boolean canMove(int x, int y, Board board) {
        int side=1;
        if(this.color.matches("black"))
            side=-1;
        int yCompare=Math.abs(this.y - y);
        int xCompare=Math.abs(this.x-x);
        if (y == this.y || xCompare > 1 || yCompare > 2)
            return false;
        //check if the piece is moving backward
        if ((side==1 && y < this.y) || (side==-1 && y > this.y))
            return false;
        if (xCompare == 1 && board.isSpotFilledWithEnemy(x, y, this.color) && yCompare == 1) {
            if (board.isSpotFilledWithEnemy(x, y, this.color))
                return true;
            return false;
        }
        if((side==1&&this.y==1)||(side==-1&&this.y==6)){
            if(xCompare==0&&yCompare==2 && board.isSpotEmpty(x,y)&&board.isSpotEmpty(x,this.y+side))
                return true;
            if(xCompare==0&&yCompare==1&&board.isSpotEmpty(x,y))
                return true;
        }
        if(xCompare==0&&yCompare==1&&board.isSpotEmpty(x,y))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "P"+this.color.charAt(0);
    }
}
