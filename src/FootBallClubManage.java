import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FootBallClubManage implements Manage<FootBallClub>, IOFile<FootBallClub> {

    private static Scanner scanner;
    private static ArrayList<FootBallClub> footBallClubs;
    private final String PATH_FILE = "D:\\02_Study\\casestudy_md2\\src\\data\\footballclub.txt";



    public FootBallClubManage(Scanner scanner) {
        this.scanner = scanner;
        footBallClubs = readBinary(PATH_FILE);
        checkDefaultIndex();
    }
    public FootBallClubManage() {
    }
    private void checkDefaultIndex() {
        if (footBallClubs.isEmpty()) {
            FootBallClub.INDEX = 0;
        } else {
            FootBallClub.INDEX = footBallClubs.get(footBallClubs.size() - 1).getId();
        }
    }

    @Override
    public void writeBinary(List<FootBallClub> e, String path) {
        File file = new File(path);
        try (ObjectOutputStream objectOutputStream
                     = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            objectOutputStream.writeObject(e);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public ArrayList<FootBallClub> readBinary(String path) {
        File file = new File(path);
        ArrayList<FootBallClub> footBallClubs1 = new ArrayList<>();
        try (ObjectInputStream objectInputStream
                     = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            footBallClubs1 = (ArrayList<FootBallClub>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        return footBallClubs1;
    }

    public Account getLoggingUser() {
        ArrayList<Account> loggingUser = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("D:\\02_Study\\casestudy_md2\\src\\data\\loggingUserName.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            loggingUser = (ArrayList<Account>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return loggingUser.get(0);
    }

    @Override
    public FootBallClub create() {
        boolean checkUserNameOfAccount = false;
        if (footBallClubs.size() == 0) {
            checkUserNameOfAccount = true;
        } else {
            for (FootBallClub footBallClub1 : footBallClubs) {
                if (!footBallClub1.getUsernameOfAccount().equals(getLoggingUser().getUserName())) {
                    checkUserNameOfAccount = true;
                    break;
                }
            }
        }
        if (checkUserNameOfAccount) {
            System.out.println("Input name football club: ");
            String name = scanner.nextLine();
            System.out.println("Input stadium football club: ");
            String stadium = scanner.nextLine();
            String userName = getLoggingUser().getUserName();
            FootBallClub footBallClub = new FootBallClub(name, stadium, userName);
            footBallClubs.add(footBallClub);
            writeBinary(footBallClubs, PATH_FILE);
            return footBallClub;
        } else {
            System.out.println("You already own a FootballClub!");
            return null;
        }
    }

    @Override
    public FootBallClub update() {
        FootBallClub footBallClub = getById();
        if (footBallClub != null) {
            System.out.println("Input new name football club: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                footBallClub.setName(name);
            }
            System.out.println("Input new stadium football club: ");
            String stadium = scanner.nextLine();
            if (!stadium.equals("")) {
                footBallClub.setStadium(stadium);
            }
        }
        writeBinary(footBallClubs, PATH_FILE);
        return footBallClub;
    }

    @Override
    public FootBallClub delete() {
        FootBallClub footBallClub = getById();
        if (footBallClub != null) {
            footBallClubs.remove(footBallClub);
        }
        writeBinary(footBallClubs, PATH_FILE);
        return footBallClub;
    }

    @Override
    public FootBallClub getById() {
        displayFootballClubByAccount();
        int id;
        do {
            try {
                System.out.println("Input id you want to find: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Have error, please try again!");
            }
        } while (true);

        for (FootBallClub footBallClub : footBallClubs) {
            if (footBallClub.getId() == id) {
                return footBallClub;
            }
        }
        return null;
    }

    public FootBallClub getByName() {
//        dissplayAll();
        System.out.println("Enter your team name: ");
        String searchName = scanner.nextLine();
        for (FootBallClub footBallClub : footBallClubs) {
            if (footBallClub.getName().equals(searchName)) {
                return footBallClub;
            }
        }
        return getByName();
    }

    public FootBallClub getById(int id) {
        for (FootBallClub footBallClub : footBallClubs) {
            if (footBallClub.getId() == id) {
                return footBallClub;
            }
        }
        return null;
    }

    @Override
    public void dissplayAll() {
        System.out.printf("%-10s%-10s%s", "ID", "NAME", "ADDRESS\n");
        for (FootBallClub footBallClub : footBallClubs) {
            footBallClub.display();
        }
    }

    public void displayOne(FootBallClub footBallClub) {
        if (footBallClub != null) {
            footBallClub.display();
        } else {
            System.out.println("Not exist this object!");
        }
    }


    public void displayFootballClubByAccount() {
        int id;
        try {
            System.out.println("Enter your account id ");
            id = Integer.parseInt(scanner.nextLine());
            System.out.printf("%-10s%-10s%s", "ID", "NAME", "ADDRESS\n");
            for (FootBallClub footBallClub : footBallClubs) {
                if (footBallClub.getId() == id) {
                    displayOne(footBallClub);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<FootBallClub> getFootBallClubs() {
        return footBallClubs;
    }
}
