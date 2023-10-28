public class Rook extends Pieces {

    public Rook(String color, int x, int y) {
        super(color, x, y);
    }

    public boolean canMove(int x, int y, Board board) {
        if(board.isSpotFilledWithMe(x,y,this.color))
            return false;
        if (this.x != x && this.y != y)
            return false;
        if(x==this.x){
            for(int i = Math.min(y,this.y)+1;i<Math.max(y,this.y);i++){
                if(!board.isSpotEmpty(x,i))
                    return false;
            }
        } else if(y==this.y){
            for(int i = Math.min(x,this.x)+1;i<Math.max(x,this.x);i++){
                if(!board.isSpotEmpty(i,y))
                    return false;
            }
        }
      return true;
    }

    @Override
    public String toString() {
        return "R"+this.color.charAt(0);
    }
}
