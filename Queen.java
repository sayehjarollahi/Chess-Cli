public class Queen extends Pieces {
    public Queen(String color, int x, int y) {
        super(color, x, y);
    }

    public boolean canMove(int x, int y, Board board) {
        if (board.isSpotFilledWithMe(x, y, this.color))
            return false;
        else if (this.x - x == 0 || this.y - y == 0)
            return moveLikeRook(x, y, board);
        else if (Math.abs(this.x - x) ==Math.abs( this.y - y))
            return moveLikeBishop(x, y, board);
        else
            return false;
    }

    private boolean moveLikeRook(int x, int y, Board board) {
        if (board.isSpotFilledWithMe(x, y, this.color))
            return false;
        if (this.x != x && this.y != y)
            return false;
        if (x == this.x) {
            for (int i = Math.min(y, this.y) + 1; i < Math.max(y, this.y); i++) {
                if (!board.isSpotEmpty(x, i))
                    return false;
            }
        } else if (y == this.y) {
            for (int i = Math.min(x, this.x) + 1; i < Math.max(x, this.x); i++) {
                if (!board.isSpotEmpty(i, y))
                    return false;
            }
        }
        return true;
    }

    private boolean moveLikeBishop(int x, int y, Board board) {
        int xCompare=-1;
        int yCompare=-1;
        if(Math.abs(this.x-x)!=Math.abs(this.y-y))
            return false;
        if(board.isSpotFilledWithMe(x,y,this.color))
            return false;
        if(this.x-x==0||this.y-y==0)
            return false;
        if(this.x<x)
            xCompare=1;
        if(this.y<y)
            yCompare=1;
        int i=this.x;
        int j=this.y;
        for(int k=1;k<Math.abs(this.x-x);k++){
            i+=xCompare;
            j+=yCompare;
            if (!board.isSpotEmpty(i, j))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Q"+this.color.charAt(0);
    }
}

