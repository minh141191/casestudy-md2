import java.io.Serializable;

public class FootBallClub implements Serializable {
    private static final long serialVersionUID = 4805067676572501583L;
    public static int INDEX;
    private int id;
    private String name;
    private String stadium;
    private Tournament tournament;
    private String usernameOfAccount;

    public FootBallClub() {
    }

    public FootBallClub(String name, String stadium, String userName) {
        this.id = ++INDEX;
        this.name = name;
        this.stadium = stadium;
        this.usernameOfAccount = userName;
    }

    public FootBallClub(int id, String name, String stadium, Tournament tournament, String username) {
        this.id = id;
        this.name = name;
        this.stadium = stadium;
        this.tournament = tournament;
        this.usernameOfAccount = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getUsernameOfAccount() {
        return usernameOfAccount;
    }

    public void setUsernameOfAccount(String usernameOfAccount) {
        this.usernameOfAccount = usernameOfAccount;
    }

    @Override
    public String toString() {
        return name;
    }

    public void display() {
        System.out.printf("%-10s%-10s%s", this.id, this.name, this.stadium + "\n");
    }
}
