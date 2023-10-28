public class Knight extends Pieces {
    public Knight(String color, int x, int y) {
        super(color, x, y);
    }

    public boolean canMove(int x, int y, Board board) {
        int xCompare = this.x - x;
        int yCompare = this.y - y;
        if(board.isSpotFilledWithMe(x,y,this.color))
            return false;
        if ((Math.abs(xCompare) == 2 && Math.abs(yCompare) == 1) || (Math.abs(yCompare) == 2 && Math.abs(xCompare) == 1)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "N"+this.color.charAt(0);
    }
}
