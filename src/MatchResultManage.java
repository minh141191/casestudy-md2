import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchResultManage implements IOFile<MatchResult> {
    private static Scanner scanner;
    private final ArrayList<MatchResult> matchResults;
    MatchScheduleManage matchScheduleManage = new MatchScheduleManage();
    private final String PATH_FILE = "D:\\02_Study\\casestudy_md2\\src\\data\\matchResult.txt";

    public MatchResultManage(Scanner scanner) {
        this.scanner = scanner;
        matchResults = readBinary(PATH_FILE);
    }

    @Override
    public void writeBinary(List<MatchResult> e, String path) {
        File file = new File(path);
        try (ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            objectOutputStream.writeObject(e);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public ArrayList<MatchResult> readBinary(String path) {
        File file = new File(path);
        ArrayList<MatchResult> matchResults1 = new ArrayList<>();
        try (ObjectInputStream objectInputStream
                = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            matchResults1 = (ArrayList<MatchResult>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println(ioException.getMessage());
        }
        return matchResults1;
    }

    public ArrayList<MatchResult> getMatchResults() {
        return matchResults;
    }

    public static int inputNum() {
        int x;
        do {
            try {
                x = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error : " + e.getMessage());
            }
        } while (true);
        return x;
    }

    public MatchResult updataMatchResult() {
        System.out.println("Input id: ");
        int id = inputNum();
        MatchSchedule matchSchedule = matchScheduleManage.getById();
        System.out.println("Input homeTeamGoals of home team: ");
        int homeTeamGoals = inputNum();
        System.out.println("Input guestGoals of guest team: ");
        int guestGoals = inputNum();
        MatchResult matchResult = new MatchResult(id, matchSchedule, homeTeamGoals, guestGoals);
        matchResults.add(matchResult);
        writeBinary(matchResults, PATH_FILE);
        return matchResult;
    }

    public void displayMatchResult() {
        System.out.printf("%-10s%-25s%-25s%s", "STT", "MATCH SCHEDULE",
                "HOME TEAM GOALS", "GUEST GOALS\n");
        for (MatchResult matchResult : matchResults) {
            matchResult.display();
        }
    }
}
