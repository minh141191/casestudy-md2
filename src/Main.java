
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tournament tournament = new Tournament(1, "CodeGym_League");

        Scanner scanner = new Scanner(System.in);
        FootBallClubManage footBallClubManage = new FootBallClubManage(scanner);
        PlayerManage playerManage = new PlayerManage(footBallClubManage, scanner);
        AccountManage accountManage = new AccountManage();
        MatchScheduleManage matchScheduleManage = new MatchScheduleManage();
        MatchResultManage matchResultManage = new MatchResultManage(scanner);
        RankingsManage rankingsManage = new RankingsManage();

        int choice = -1;
        do {
            System.out.println("-----MENU------");
            System.out.println("1. Register: ");
            System.out.println("2. Login: ");
            System.out.println("3. Forget password: ");
            System.out.println("0. Exit! ");
            System.out.println("Input your choice! ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            switch (choice) {
                case 1:
                    accountManage.createAccount();
                    break;
                case 2:
                    accountManage.login();
                    userMenu(matchScheduleManage, playerManage, scanner,
                            matchResultManage, rankingsManage, footBallClubManage);
                    break;
                case 3:
                    accountManage.findPassWord();
                    break;
                case 0:
                    System.exit(0);
                    break;
               }

        } while (true);

    }

    private static void userMenu(MatchScheduleManage matchScheduleManage, PlayerManage playerManage,
                                 Scanner scanner, MatchResultManage matchResultManage, RankingsManage rankingsManage,
                                 FootBallClubManage footBallClubManage) {
        int choice = -1;
        do {
            System.out.println("-----MENU-----");
            System.out.println("1. Create FootballClub: ");
            System.out.println("2. Update FootballClub: ");
            System.out.println("3. DisplayAll FootballClub: ");
            System.out.println("4. Create Player: ");
            System.out.println("5. Display Player By FootballClub: ");
            System.out.println("6. Create Match Schedule: ");
            System.out.println("7. Display Match Schedule: ");
            System.out.println("8. Update Match result: ");
            System.out.println("9. Display Match result: ");
            System.out.println("10. Rankings: ");
            System.out.println("Input your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            switch (choice) {
                case 1:
                    FootBallClub footBallClubCreate = playerManage.getFootBallClubManage().create();
                    System.out.println("FootBallClub created is: ");
                    playerManage.getFootBallClubManage().displayOne(footBallClubCreate);
                    break;
                case 2:
                    FootBallClub footBallClubUpdate = playerManage.getFootBallClubManage().update();
                    System.out.println("FootballClub updated is: ");
                    playerManage.getFootBallClubManage().displayOne(footBallClubUpdate);
                    break;
                case 3:
                    playerManage.getFootBallClubManage().dissplayAll();
                    break;
                case 4:
                    Player playerCreate = playerManage.create();
                    System.out.println("Player created is: ");
                    playerManage.displayOne(playerCreate);
                    break;
                case 5:
                    playerManage.displayPlayerByFootballClub();
                    break;
                case 6:
                    matchScheduleManage.createMatchSchedule();
                case 7:
                    matchScheduleManage.displayMatchSchedule();
                    break;
                case 8:
                    matchResultManage.updataMatchResult();
                    break;
                case 9:
                    matchResultManage.displayMatchResult();
                    break;
                case 10:
                    rankingsManage.getRankings(footBallClubManage.getFootBallClubs(), matchResultManage.getMatchResults());
                    break;
             }

        } while (choice != 0);
    }
}