import java.io.Serializable;

public class MatchResult implements Serializable {
    private static final long serialVersionUID = -804317887237200690L;
    private int id;
    private MatchSchedule matchSchedule;
    private int homeTeamGoals;
    private int guestGoals;

    public MatchResult() {
    }

    public MatchResult(int id, MatchSchedule matchSchedule, int homeTeamGoals, int guestGoals) {
        this.id = id;
        this.matchSchedule = matchSchedule;
        this.homeTeamGoals = homeTeamGoals;
        this.guestGoals = guestGoals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MatchSchedule getMatchSchedule() {
        return matchSchedule;
    }

    public void setMatchSchedule(MatchSchedule matchSchedule) {
        this.matchSchedule = matchSchedule;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getGuestGoals() {
        return guestGoals;
    }

    public void setGuestGoals(int guestGoals) {
        this.guestGoals = guestGoals;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "id=" + id +
                ", matchSchedule=" + matchSchedule +
                ", homeTeamGoals=" + homeTeamGoals +
                ", guestGoals=" + guestGoals +
                '}';
    }

    public void display() {
        System.out.printf("%-10s%-25s%-25s%s",
                this.id, this.matchSchedule, this.homeTeamGoals, this.guestGoals + "\n" );
    }
}
