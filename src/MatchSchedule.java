import java.io.Serializable;

public class MatchSchedule implements Serializable {
    private static final long serialVersionUID = -1446755785117417541L;
    public static int INDEX;
    private int id;
    private String date;
    private String time;
    private FootBallClub homeTeam;
    private FootBallClub guest;

    public MatchSchedule() {
    }

    public MatchSchedule(String date, String time, FootBallClub homeTeam, FootBallClub guest) {
        this.id = ++INDEX;
        this.date = date;
        this.time = time;
        this.homeTeam = homeTeam;
        this.guest = guest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public FootBallClub getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(FootBallClub homeTeam) {
        this.homeTeam = homeTeam;
    }

    public FootBallClub getGuest() {
        return guest;
    }

    public void setGuest(FootBallClub guest) {
        this.guest = guest;
    }


    @Override
    public String toString() {
        return homeTeam + " vs " + guest;

    }

    public void display() {
        System.out.printf("%-10s%-17s%-15s%-15s%-15s%s",
               this.id, this.date, this.time, this.homeTeam, this.guest, homeTeam.getStadium() + "\n" );
    }
}
