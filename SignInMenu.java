import java.util.ArrayList;
import java.util.TreeSet;

public class SignInMenu {
    private ArrayList<String> allCommands = new ArrayList<>();

    public SignInMenu() {
        this.allCommands.add("register [username] [password]");
        this.allCommands.add("login [username] [password]");
        this.allCommands.add("remove [username] [password]");
        this.allCommands.add("list_users");
        this.allCommands.add("help");
        this.allCommands.add("exit");
    }

    public void registerPlayer(String username, String password) {
        if (Player.checkExistenceOfPlayer(username)) {
            System.out.println("a user exists with this username");
        } else {
            new Player(username, password);
            System.out.println("register successful");
        }
    }

    public boolean loginPlayer(String username, String password) {
        if (!Player.checkExistenceOfPlayer(username)) {
            System.out.println("no user exists with this username");
            return false;
        } else if (!Player.checkPassword(username, password)) {
            System.out.println("incorrect password");
            return false;
        } else {
            System.out.println("login successful");
            return true;
        }
    }

    public void deletePlayer(String username, String password) {
        if (!Player.checkExistenceOfPlayer(username)) {
            System.out.println("no user exists with this username");
        } else if (!Player.checkPassword(username, password)) {
            System.out.println("incorrect password");
        } else {
            Player.getPlayerByUsername(username).deletePlayer();
            System.out.println("removed " + username + " successfully");
        }
    }

    public void listPlayers() {
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

}
