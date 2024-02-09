package Model;

public class Player {

    private String username;
    private String password;
    private String email;

    private Player() {
    }
    private static Player instance = new Player();

    public static Player getInstance() {
        return instance;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
