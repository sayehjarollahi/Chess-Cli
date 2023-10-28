import java.util.Arrays;

public class Board {
    private Pieces[][] board;


    public Board() {
        board = new Pieces[8][8];
        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn("white", i, 1);
            board[i][6] = new Pawn("black", i, 6);
        }
        board[0][0] = new Rook("white", 0, 0);
        board[0][7] = new Rook("black", 0, 7);
        board[7][0] = new Rook("white", 7, 0);
        board[7][7] = new Rook("black", 7, 7);
        board[1][0] = new Knight("white", 1, 0);
        board[6][0] = new Knight("white", 6, 0);
        board[1][7] = new Knight("black", 1, 7);
        board[6][7] = new Knight("black", 6, 7);
        board[2][0] = new Bishop("white", 2, 0);
        board[5][0] = new Bishop("white", 5, 0);
        board[2][7] = new Bishop("black", 2, 7);
        board[5][7] = new Bishop("black", 5, 7);
        board[3][0] = new Queen("white", 3, 0);
        board[3][7] = new Queen("black", 3, 7);
        board[4][0] = new King("white", 4, 0);
        board[4][7] = new King("black", 4, 7);
    }

    public boolean isSpotEmpty(int x, int y) {
        if (board[x][y] == null)
            return true;
        else
            return false;
    }

    public boolean isSpotFilledWithEnemy(int x, int y, String color) {
        if (isSpotEmpty(x, y))
            return false;
        else if (getPieceByLocation(x, y).getColor().matches(color))
            return false;
        return true;
    }

    public boolean isSpotFilledWithMe(int x, int y, String color) {
        if (isSpotEmpty(x, y))
            return false;
        else if (getPieceByLocation(x, y).getColor().matches(color))
            return true;
        return false;
    }

    public Pieces getPieceByLocation(int x, int y) {
        return board[x][y];
    }

    public void setPieceInBoard(Pieces piece,int x,int y){
        this.board[x][y]=piece;
        if(piece!=null){
            piece.setX(x);
            piece.setY(y);
        }
    }

    @Override
    public String toString() {
        String result = new String();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] == null)
                    result = result.concat("  |");
                else {
                    result = result.concat(board[j][i].toString() + "|");
                }
            }
            if(i!=0) {
                result = result + "\n";
            }
        }
        return result;
    }
}
