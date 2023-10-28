import java.sql.SQLOutput;
import java.util.ArrayList;

public class ChessGame {
    private Board board;
    private Player player1;
    private Player player2;
    private Player turn;
    private Move thisTurnMove;
    private Pieces selectedPiece;
    private long movementLimit;
    private long remainingMove;
    private boolean undoUsed;
    private ArrayList<Move> allMoves = new ArrayList<>();
    private ArrayList<Pieces> killedPieces = new ArrayList<>();


    public ChessGame(Player player1, Player player2, long movementLimit) {
        player1.setColorOfPieces("white");
        player2.setColorOfPieces("black");
        player1.setUndoFirst();
        player2.setUndoFirst();
        this.player1 = player1;
        this.player2 = player2;
        this.movementLimit = movementLimit;
        this.remainingMove = movementLimit;
        this.turn = player1;
        this.board = new Board();
        this.undoUsed = false;
    }

    public Board getBoard() {
        return board;
    }

    public boolean selectPiece(int x, int y) {
        if (turn.getColorOfPieces().equals(board.getPieceByLocation(x, y).getColor())) {
            selectedPiece = board.getPieceByLocation(x, y);
            return true;
        }
        return false;
    }

    public boolean deselectPiece() {
        if (selectedPiece != null) {
            selectedPiece = null;
            return true;
        }
        return false;
    }

    public boolean isTurnMoveDone() {
        if (thisTurnMove == null)
            return false;
        return true;
    }

    public boolean isPieceSelected() {
        if (selectedPiece == null)
            return false;
        return true;
    }

    public boolean movePiece(int x, int y) {
        if (!selectedPiece.canMove(x, y, board))
            return false;
        else {
            Move move = new Move(selectedPiece, x, y, board);
            if (move.getRemovedPiece() != null) {
                killedPieces.add(move.getRemovedPiece());
                move.getRemovedPiece().setKilled(true);
                System.out.println("rival piece destroyed");
            } else {
                System.out.println("moved");
            }
            allMoves.add(move);
            thisTurnMove = move;
            remainingMove -= 1;
            return true;
        }

    }

    public void undo() {
        if (turn.getUndo() == 0)
            System.out.println("you cannot undo anymore");
        else if (thisTurnMove == null)
            System.out.println("you must move before undo");
        else if (undoUsed)
            System.out.println("you have used your undo for this turn");
        else {
            undoUsed = true;
            new Move(thisTurnMove.getMovingPiece(), thisTurnMove.getFirstX(), thisTurnMove.getFirstY(), board);
            if (thisTurnMove.getRemovedPiece() != null) {
                board.setPieceInBoard(thisTurnMove.getRemovedPiece(), thisTurnMove.getRemovedPiece().getX(), thisTurnMove.getRemovedPiece().getY());
                killedPieces.remove(thisTurnMove.getRemovedPiece());
                thisTurnMove.getRemovedPiece().setKilled(false);
            }
            remainingMove += 1;
            allMoves.remove(thisTurnMove);
            thisTurnMove = null;
            this.turn.setUndo();
            System.out.println("undo completed");
        }
    }

    public int undoShow() {
        return turn.getUndo();
    }

    public void showMovesEach() {
        int type = 1;
        if (turn.getColorOfPieces().equals("white"))
            type = 0;
        for (int i = type; i < allMoves.size(); i += 2) {
            Move move = allMoves.get(i);
            if (move.getRemovedPiece() == null)
                System.out.println(move.getMovingPiece().toString() + " " + (move.getFirstY() + 1) + "," + (move.getFirstX() + 1) + " to " + (move.getFinalY() + 1) + "," + (move.getFinalX() + 1));
            else
                System.out.println(move.getMovingPiece().toString() + " " + (move.getFirstY() + 1) + "," + (move.getFirstX() + 1) + " to " + (move.getRemovedPiece().getY() + 1) + "," + (move.getRemovedPiece().getX() + 1) + " destroyed " + move.getRemovedPiece().toString());
        }
    }

    public void showMoves() {
        for (Move move : allMoves) {
            if (move.getRemovedPiece() == null)
                System.out.println(move.getMovingPiece().toString() + " " + (move.getFirstY() + 1) + "," + (move.getFirstX() + 1) + " to " + (move.getFinalY() + 1) + "," + (move.getFinalX() + 1));
            else
                System.out.println(move.getMovingPiece().toString() + " " + (move.getFirstY() + 1) + "," + (move.getFirstX() + 1) + " to " + (move.getRemovedPiece().getY() + 1) + "," + (move.getRemovedPiece().getX() + 1) + " destroyed " + move.getRemovedPiece().toString());
        }
    }

    public void showKilledEach() {
        String color = "black";
        if (turn.equals(player1))
            color = "white";
        for (Pieces killedPiece : killedPieces) {
            if (killedPiece.getColor().equals(color))
                System.out.println(killedPiece.toString() + " killed in spot " + (killedPiece.getY() + 1) + "," + (killedPiece.getX() + 1));
        }
    }

    public void showKilled() {
        for (Pieces killedPiece : killedPieces) {
            System.out.println(killedPiece.toString() + " killed in spot " + (killedPiece.getY() + 1) + "," + (killedPiece.getX() + 1));
        }
    }

    public void forfeit(){
        System.out.println("you have forfeited");
        if(turn.equals(player1)){
            player1.setScore(-1);
            player1.setLosses(1);
            player2.setScore(2);
            player2.setWines(1);
            System.out.println("player "+player2.getUserName()+" with color "+player2.getColorOfPieces()+" won");
        }
        else{
            player2.setScore(-1);
            player2.setLosses(1);
            player1.setScore(2);
            player1.setWines(1);
            System.out.println("player "+player1.getUserName()+" with color "+player1.getColorOfPieces()+" won");
        }
    }

    public boolean changeTurn() {
        if (thisTurnMove == null) {
            System.out.println("you must move then proceed to next turn");
            return false;
        }
        System.out.println("turn completed");
        if (isKingDestroyed()) {
            setWinner();
            return true;
        }
        if (isGameFinished()) {
            setDraw();
            return true;
        } else {
            undoUsed = false;
            thisTurnMove = null;
            selectedPiece = null;
            if (turn.equals(player1))
                turn = player2;
            else
                turn = player1;
            return false;
        }
    }

    public String getTurnUsername() {
        return turn.getUserName();
    }

    public String getTurnColor() {
        return turn.getColorOfPieces();
    }

    private boolean isGameFinished() {
        if (remainingMove == 0 && movementLimit != 0)
            return true;
        return false;
    }

    public boolean isKingDestroyed() {
        for (Pieces killedPiece : killedPieces) {
            if (killedPiece.toString().matches("Kw|Kb"))
                return true;
        }
        return false;
    }

    private void setWinner() {
        turn.setWines(1);
        turn.setScore(3);
        if (turn.equals(player1)) {
            player2.setLosses(1);
        } else
            player1.setLosses(1);
        System.out.println("player " + turn.getUserName() + " with color " + turn.getColorOfPieces() + " won");
    }

    private void setDraw() {
        player1.setDraws(1);
        player2.setDraws(1);
        player1.setScore(1);
        player2.setScore(1);
        System.out.println("draw");
    }


}
