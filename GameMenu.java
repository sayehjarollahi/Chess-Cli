import java.util.ArrayList;

public class GameMenu {
    private ArrayList<String> allCommands = new ArrayList<>();
    public ChessGame chessGame;

    public GameMenu() {
        this.allCommands.add("select [x],[y]");
        this.allCommands.add("deselect");
        this.allCommands.add("move [x],[y]");
        this.allCommands.add("next_turn");
        this.allCommands.add("show_turn");
        this.allCommands.add("undo");
        this.allCommands.add("undo_number");
        this.allCommands.add("show_moves [-all]");
        this.allCommands.add("show_killed [-all]");
        this.allCommands.add("show_board");
        this.allCommands.add("help");
        this.allCommands.add("forfeit");
    }

    public void setChessGame(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void select(int x, int y) {
        x -= 1;
        y -= 1;
        if (chessGame.getBoard().isSpotEmpty(x, y))
            System.out.println("no piece on this spot");
        else {
            if (chessGame.selectPiece(x, y))
                System.out.println("selected");
            else
                System.out.println("you can only select one of your pieces");
        }

    }

    public void deselect() {
        if (chessGame.deselectPiece())
            System.out.println("deselected");
        else
            System.out.println("no piece is selected");

    }

    public void move(String input) {
        if (chessGame.isTurnMoveDone())
            System.out.println("already moved");
        else if (!(input.matches("move [1-8],[1-8]")))
            System.out.println("wrong coordination");
        else if (!chessGame.isPieceSelected())
            System.out.println("do not have any selected piece");
        else {
            int x = Integer.parseInt(String.valueOf(input.charAt(7))) - 1;
            int y = Integer.parseInt(String.valueOf(input.charAt(5))) - 1;
            if (!chessGame.movePiece(x, y))
                System.out.println("cannot move to the spot");
        }

    }

    public void showTurn(){
        System.out.println("it is player "+chessGame.getTurnUsername()+" turn with color "+chessGame.getTurnColor());
    }

    public void showBoard() {
        System.out.println(chessGame.getBoard().toString());
    }

    public void help(){
        for (String command : allCommands) {
            System.out.println(command);
        }
    }

}








