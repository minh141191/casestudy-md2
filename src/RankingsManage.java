import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RankingsManage {
    public void getRankings(ArrayList<FootBallClub> footBallClubs, ArrayList<MatchResult> matchResults) {
        List<Rankings> rankingList = new ArrayList<>();
        // 1. For danh sach doi bong
        for (FootBallClub footBallClub : footBallClubs) {
            // 2. Thong ke tong so tran dau, so diem, tong so ban thang, tong so ban thua tu danh sach ket qua tran dau
            List<MatchResult> resultHomeList = matchResults.stream()
                    .filter(result -> result.getMatchSchedule().getHomeTeam().getId() == footBallClub.getId())
                    .collect(Collectors.toList());
            List<MatchResult> resultGuestList = matchResults.stream()
                    .filter(result -> result.getMatchSchedule().getGuest().getId() == footBallClub.getId())
                    .collect(Collectors.toList());
            int totalPoint = 0;
            int totalGoals = 0;
            int totalGoalsConceded = 0;
            for (MatchResult result : resultHomeList) {
                totalGoals += result.getHomeTeamGoals();
                totalGoalsConceded += result.getGuestGoals();
                if (result.getHomeTeamGoals() > result.getGuestGoals()) {
                    totalPoint += 3;
                } else if (result.getHomeTeamGoals() == result.getGuestGoals()) {
                    totalPoint += 1;
                }
            }

            for (MatchResult result : resultGuestList) {
                totalGoals += result.getGuestGoals();
                totalGoalsConceded += result.getHomeTeamGoals();
                if (result.getGuestGoals() > result.getHomeTeamGoals()) {
                    totalPoint += 3;
                } else if (result.getGuestGoals() == result.getHomeTeamGoals()) {
                    totalPoint += 1;
                }
            }

            Rankings ranking = new Rankings();
            ranking.setFootBallClub(footBallClub);
            ranking.setTotalMatch(resultHomeList.size() + resultGuestList.size());
            ranking.setPoint(totalPoint);
            ranking.setTotalGoals(totalGoals);
            ranking.setTotalGoalsConceded(totalGoalsConceded);
            ranking.setDifferenceGoals(totalGoals - totalGoalsConceded);
            rankingList.add(ranking);
        }

        // 3. Sort danh sach ranking (sort theo point, differenceGoals)
        PointComparator pointComparator = new PointComparator();
        rankingList.sort(pointComparator);
        // 4. Display
        System.out.printf("%-10s%-15s%-15s%-15s%-20s%-25s%s",
                "STT", "FOOTBALL-CLUB", "TOTAL-MATCH", "TOTAL-POINT", "TOTAL-GOALS", "TOTAL-GOALS-CONCEDED", "DIFFERENCE-GOALS\n");
        int stt = 1;
        for (Rankings ranking : rankingList) {
            ranking.setStt(stt++);
            ranking.display();
        }
    }
}
