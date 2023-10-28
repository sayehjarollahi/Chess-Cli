public class Move {
    private Pieces movingPiece;
    private int firstX;
    private int firstY;
    private int finalX;
    private int finalY;
    private Pieces removedPiece;

    public Move(Pieces movingPiece, int x, int y,Board board) {
        this.movingPiece = movingPiece;
        this.finalX = x;
        this.finalY = y;
        this.firstX = movingPiece.getX();
        this.firstY = movingPiece.getY();
        move(board);
    }

    public Pieces getRemovedPiece() {
        return removedPiece;
    }

    public int getFirstX() {
        return firstX;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public int getFirstY() {
        return firstY;
    }

    public Pieces getMovingPiece() {
        return movingPiece;
    }

    private void move(Board board){
        if(board.isSpotFilledWithEnemy(finalX,finalY,movingPiece.getColor())){
            removedPiece=board.getPieceByLocation(finalX,finalY);
        }
        else{
            removedPiece=null;
        }
        board.setPieceInBoard(null,movingPiece.getX(),movingPiece.getY());
        board.setPieceInBoard(movingPiece,finalX,finalY);
    }
}
