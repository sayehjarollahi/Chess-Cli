
import java.util.*;

public class MainMenu {
    private Player loggedInPlayer;
    private ArrayList<String> allCommands = new ArrayList<>();

    public MainMenu() {
        this.allCommands.add("new_game [username] [limit]");
        this.allCommands.add("scoreboard");
        this.allCommands.add("list_users");
        this.allCommands.add("help");
        this.allCommands.add("logout");
    }

    public void setLoggedInPlayer(Player loggedInPlayer) {
        this.loggedInPlayer = loggedInPlayer;
    }

    public ChessGame newGame(String userName, long limit) {
        if (userName.equals(loggedInPlayer.getUserName()))
            System.out.println("you must choose another player to start a game");
        else if (!Player.checkExistenceOfPlayer(userName))
            System.out.println("no user exists with this username");
        else {
            System.out.println("new game started successfully between " + loggedInPlayer.getUserName() + " and " + userName + " with limit " + limit);
            ChessGame chessGame=new ChessGame(loggedInPlayer, Player.getPlayerByUsername(userName), limit);
            return chessGame;
        }
        return null;
    }

    public void showScoreBoard() {
        ArrayList<Player> scoreboard = Player.getAllPlayers();
        Collections.sort(scoreboard,new SortPlayersList());
        for (Player player : scoreboard) {
            System.out.println(player.getUserName()+" "+player.getScore()+" "+player.getWines()+" "+player.getDraws()+" "+player.getLosses());
        }
    }

    public void listUsers() {
        TreeSet<String> players = Player.listUsernames();
        for (String player : players) {
            System.out.println(player);
        }
    }

    public void help() {
        for (String command : allCommands) {
            System.out.println(command);
        }
    }

    public void logout() {
        System.out.println("logout successful");
        this.loggedInPlayer=null;
    }

    static class SortPlayersList implements Comparator<Player>{
        @Override
        public int compare(Player player1, Player player2) {
            int scoreCompare=player1.getScore().compareTo(player2.getScore());
            int winCompare=player1.getWines().compareTo(player2.getWines());
            int drawCompare=player1.getDraws().compareTo(player2.getDraws());
            int lossCompare=player1.getLosses().compareTo(player2.getLosses());
            int userCompare=player1.getUserName().compareTo(player2.getUserName());
            if(scoreCompare==0){
                if(winCompare==0){
                    if(drawCompare==0){
                        if(lossCompare==0){
                            if(userCompare==0){
                                return scoreCompare;
                            }else{
                                return userCompare;
                            }
                        }else{
                            return lossCompare;
                        }
                    }else{
                        return -1*drawCompare;
                    }
                }else{
                    return -1*winCompare;
                }
            }else{
                return -1*scoreCompare;
            }


        }
    }


}


