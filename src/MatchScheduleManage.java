import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchScheduleManage implements IOFile<MatchSchedule> {
    private final Scanner scanner;
    FootBallClubManage footBallClubManage = new FootBallClubManage();
    private final ArrayList<MatchSchedule> matchSchedules;
    private final String PATH_FILE = "D:\\02_Study\\casestudy_md2\\src\\data\\matchSchedule.txt";

    public MatchScheduleManage() {
        scanner = new Scanner(System.in);
        matchSchedules = readBinary(PATH_FILE);
        checkDefaultIndex();
    }



    private void checkDefaultIndex() {
        if (matchSchedules.isEmpty()) {
            FootBallClub.INDEX = 0;
        } else {
            FootBallClub.INDEX = matchSchedules.get(matchSchedules.size() - 1).getId();
        }
    }

    @Override
    public void writeBinary(List<MatchSchedule> e, String path) {
        File file = new File(path);
        try (ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            objectOutputStream.writeObject(e);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public ArrayList<MatchSchedule> readBinary(String path) {
        File file = new File(path);
        ArrayList<MatchSchedule> matchSchedules1 = new ArrayList<>();
        try (ObjectInputStream objectInputStream
                = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            matchSchedules1 = (ArrayList<MatchSchedule>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println(ioException.getMessage());
        }
        return matchSchedules1;
    }

    public MatchSchedule createMatchSchedule() {
        System.out.println("Input match day: ");
        String date = scanner.nextLine();
        System.out.println("Input match time: ");
        String time = scanner.nextLine();
        System.out.println("Input the home team: ");
        FootBallClub homeTeam = footBallClubManage.getByName();
        System.out.println("Input the guest team: ");
        FootBallClub guestTeam = footBallClubManage.getByName();
        MatchSchedule matchSchedule = new MatchSchedule(date, time, homeTeam, guestTeam);
        matchSchedules.add(matchSchedule);
        writeBinary(matchSchedules, PATH_FILE);
        return matchSchedule;
    }

    public void displayMatchSchedule() {
        System.out.printf("%-10s%-17s%-15s%-15s%-15s%s",
                "STT", "MATCH DAY", "MATCH TIME", "HOME TEAM", "GUEST TEAM", "STADIUM\n");
        for (MatchSchedule matchSchedule : matchSchedules) {
            if (matchSchedule != null) {
                matchSchedule.display();
            } else {
                System.out.println("Not exist this object!");
            }
        }
    }
    public MatchSchedule getById() {
        displayMatchSchedule();
        System.out.println("Input id your find: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (MatchSchedule matchSchedule : matchSchedules) {
            if (matchSchedule.getId() == id) {
                return matchSchedule;
            }
        }
        return getById();
    }

    public MatchSchedule getById(int id) {
        for (MatchSchedule matchSchedule : matchSchedules) {
            if (matchSchedule.getId() == id) {
                return matchSchedule;
            }
        }
        return getById(id);
    }
}
