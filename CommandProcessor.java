import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
    GameMenu gameMenu = new GameMenu();
    MainMenu mainMenu = new MainMenu();
    SignInMenu signInMenu = new SignInMenu();
    Scanner scanner = new Scanner(System.in);
    private int currentMenu = 0;

    public void run() {
        String input = new String();
        while (true) {
            input = scanner.nextLine();
            if (currentMenu == 0) {
                boolean endOfProgram = checkFirstMenuCommands(input);
                if (endOfProgram == true)
                    break;
            } else if (currentMenu == 1) {
                checkSecondMenuCommands(input);
            } else if (currentMenu == 2) {
                checkGameMenuCommands(input);
            }
        }
    }

    private boolean checkFirstMenuCommands(String input) {
        Matcher matcher;
        if (input.matches("register (\\S+) (\\S+)")) {
            if (input.matches("register (\\w+) (\\w+)")) {
                    matcher = getMatcher(input, "register (\\w+) (\\w+)");
                    signInMenu.registerPlayer(matcher.group(1), matcher.group(2));
            } else
                checkUsernamePasswordEfficiency(input);
        } else if (input.matches("login (\\S+) (\\S+)")) {
            if (input.matches("login (\\w+) (\\w+)")) {
                matcher = getMatcher(input, "login (\\w+) (\\w+)");
                if (signInMenu.loginPlayer(matcher.group(1), matcher.group(2))) {
                    currentMenu += 1;
                    mainMenu.setLoggedInPlayer(Player.getPlayerByUsername(matcher.group(1)));
                }
            } else
                checkUsernamePasswordEfficiency(input);
        } else if (input.matches("remove (\\S+) (\\S+)")) {
            if (input.matches("remove (\\w+) (\\w+)")) {
                matcher = getMatcher(input, "remove (\\w+) (\\w+)");
                signInMenu.deletePlayer(matcher.group(1), matcher.group(2));
            }else
                checkUsernamePasswordEfficiency(input);
        } else if (input.equals("list_users"))
            signInMenu.listPlayers();
        else if (input.equals("help"))
            signInMenu.help();
        else if (input.equals("exit")) {
            System.out.println("program ended");
            return true;
        } else
            System.out.println("invalid command");
        return false;
    }

    private void checkSecondMenuCommands(String input) {
        if (input.matches("new_game (\\S+) (-?\\d+)")) {
            String[] data = checkOtherUserEfficiency(input);
            if (data != null) {
                ChessGame newChessGame = mainMenu.newGame(data[0], Long.parseLong(data[1]));
                if (newChessGame != null) {
                    currentMenu += 1;
                    gameMenu.setChessGame(newChessGame);
                }
            }
        } else if (input.matches("scoreboard")) {
            mainMenu.showScoreBoard();
        } else if (input.matches("list_users")) {
            mainMenu.listUsers();
        } else if (input.matches("help")) {
            mainMenu.help();
        } else if (input.matches("logout")) {
            mainMenu.logout();
            currentMenu -= 1;
        } else
            System.out.println("invalid command");

    }

    private void checkGameMenuCommands(String input) {
        if (input.matches("select (-?0*\\d+),(-?0*\\d+)")) {
            if (!input.matches("select [1-8],[1-8]"))
                System.out.println("wrong coordination");
            else {
                Matcher matcher = getMatcher(input, "select ([1-8]),([1-8])");
                gameMenu.select(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(1)));
            }
        } else if (input.matches("deselect")) {
            gameMenu.deselect();
        } else if (input.matches("move (-?0*\\d+),(-?0*\\d+)")) {
            gameMenu.move(input);
        } else if (input.matches("next_turn")) {
            if (gameMenu.chessGame.changeTurn())
                currentMenu -= 1;
        } else if (input.matches("show_turn")) {
            gameMenu.showTurn();
        } else if (input.matches("undo")) {
            gameMenu.chessGame.undo();
        } else if (input.matches("undo_number")) {
            System.out.println("you have " + gameMenu.chessGame.undoShow() + " undo moves");
        } else if (input.matches("show_moves")) {
            gameMenu.chessGame.showMovesEach();
        } else if (input.matches("show_moves -all")) {
            gameMenu.chessGame.showMoves();
        } else if (input.matches("show_killed")) {
            gameMenu.chessGame.showKilledEach();
        } else if (input.matches("show_killed -all")) {
            gameMenu.chessGame.showKilled();
        } else if (input.matches("help")) {
            gameMenu.help();
        } else if (input.matches("forfeit")) {
            gameMenu.chessGame.forfeit();
            currentMenu -= 1;
        } else if (input.matches("show_board")) {
            gameMenu.showBoard();
        } else
            System.out.println("invalid command");
    }

    private void checkUsernamePasswordEfficiency(String input) {
        if (input.matches("(\\S*) (\\S+) (\\w+)"))
            System.out.println("username format is invalid");
        else if (input.matches("(\\S*) (\\w+) (\\S+)"))
            System.out.println("password format is invalid");
        else
            System.out.println("username format is invalid");
    }

    private String[] checkOtherUserEfficiency(String input) {
        if (input.matches("new_game (.+) (-?\\d+)")&&!input.matches("new_game (\\w+) (-?\\d+)")) {
            System.out.println("username format is invalid");
            return null;
        }
        else if (input.matches("new_game (\\w+) (-?\\d+)")) {
            Pattern pattern = Pattern.compile("new_game (\\w+) (-?\\d+)");
            Matcher matcher = pattern.matcher(input);
            matcher.find();
            String[] data = new String[2];
            data[0] = matcher.group(1);
            data[1] = matcher.group(2);
            if (Long.parseLong(data[1]) < 0) {
                System.out.println("number should be positive to have a limit or 0 for no limit");
                return null;
            }
            return data;
        }
        System.out.println("invalid command");
        return null;
    }

    private Matcher getMatcher(String input, String regex) {
        Matcher matcher = null;
        Pattern pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }


}
