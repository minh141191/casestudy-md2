public class Rankings {
    private int stt;
    private FootBallClub footBallClub;
    private int totalMatch;
    private int point;
    private int totalGoals;
    private int totalGoalsConceded;
    private int differenceGoals;

    public Rankings() {
    }

    public Rankings(FootBallClub footBallClub, int totalMatch, int point, int totalGoals, int totalGoalsConceded, int differenceGoals) {
        this.footBallClub = footBallClub;
        this.totalMatch = totalMatch;
        this.point = point;
        this.totalGoals = totalGoals;
        this.totalGoalsConceded = totalGoalsConceded;
        this.differenceGoals = differenceGoals;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public FootBallClub getFootBallClub() {
        return footBallClub;
    }

    public void setFootBallClub(FootBallClub footBallClub) {
        this.footBallClub = footBallClub;
    }

    public int getTotalMatch() {
        return totalMatch;
    }

    public void setTotalMatch(int totalMatch) {
        this.totalMatch = totalMatch;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }

    public int getTotalGoalsConceded() {
        return totalGoalsConceded;
    }

    public void setTotalGoalsConceded(int totalGoalsConceded) {
        this.totalGoalsConceded = totalGoalsConceded;
    }

    public int getDifferenceGoals() {
        return differenceGoals;
    }

    public void setDifferenceGoals(int differenceGoals) {
        this.differenceGoals = differenceGoals;
    }

    @Override
    public String toString() {
        return "Rankings{" +
                "footBallClub=" + footBallClub.getName() +
                ", totalMatch=" + totalMatch +
                ", point=" + point +
                ", totalGoals=" + totalGoals +
                ", totalGoalsConceded=" + totalGoalsConceded +
                ", differenceGoals=" + differenceGoals +
                '}';
    }

    public void display() {
        System.out.printf("%-10s%-15s%-15s%-15s%-20s%-25s%s", this.stt, this.footBallClub,
                this.totalMatch, this.point, this.totalGoals, this.totalGoalsConceded, this.differenceGoals + "\n");
    }
}
