import org.omg.CORBA.WStringSeqHelper;

import java.util.ArrayList;
import java.util.TreeSet;

public class Player {
    private String userName;
    private String passWord;
    private String colorOfPieces;
    private int undo;
    private int score;
    private int wines;
    private int losses;
    private int draws;
    private static ArrayList<Player> allPlayers = new ArrayList<>();

    public Player(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.undo =2;
        allPlayers.add(this);
    }

    public void setUndoFirst(){
        this.undo=2;
    }

    public String getColorOfPieces() {
        return colorOfPieces;
    }

    public void setUndo() {
        this.undo -=1;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getWines() {
        return wines;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws += draws;
    }

    public int getUndo(){
        return undo;
    }

    public void setLosses(int losses) {
        this.losses += losses;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void setWines(int wines) {
        this.wines += wines;
    }

    public String getUserName() {
        return userName;
    }

    public void setColorOfPieces(String colorOfPieces) {
        this.colorOfPieces = colorOfPieces;
    }

    public static boolean checkExistenceOfPlayer(String username) {
        for (Player player : allPlayers) {
            if (player.userName.equals(username))
                return true;
        }
        return false;
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : allPlayers) {
            if (player.userName.equals(username))
                return player;
        }
        return null;
    }

    public static boolean checkPassword(String username, String password) {
        if (Player.getPlayerByUsername(username).passWord.equals(password))
            return true;
        return false;
    }

    public void deletePlayer() {
        for (Player eachPlayer : allPlayers) {
            if (eachPlayer.equals(this)){
                allPlayers.remove(eachPlayer);
                break;
            }
        }
    }

    public static TreeSet<String> listUsernames() {
        TreeSet<String> listOfUsers = new TreeSet<>();
        for (Player player : allPlayers) {
            listOfUsers.add(player.userName);
        }
        return listOfUsers;
    }

    public static ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    @Override
    public String toString() {
        return this.userName+" "+this.score+" "+this.wines+" "+this.draws+" "+this.losses;
    }
}
