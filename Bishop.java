public class Bishop extends Pieces {

    public Bishop(String color,int x,int y) {
        super(color,x,y);
    }
    public boolean canMove(int x,int y,Board board){
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
        return "B"+this.color.charAt(0);
    }
}
