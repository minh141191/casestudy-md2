import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerManage implements Manage<Player>, IOFile<Player> {
    private static Scanner scanner;
    private final ArrayList<Player> players;
    private final FootBallClubManage footBallClubManage;
    private final String PATH_FILE = "D:\\02_Study\\casestudy_md2\\src\\data\\player.txt";

    public PlayerManage(FootBallClubManage footBallClubManage, Scanner scanner) {
        this.scanner = scanner;
        this.footBallClubManage = footBallClubManage;
        players = readBinary(PATH_FILE);
        checkDefaultIndex();
    }

    private void checkDefaultIndex() {
        if (players.isEmpty()) {
            Player.INDEX = 0;
        } else {
            Player.INDEX = players.get(players.size() - 1).getId();
        }
    }

    public FootBallClubManage getFootBallClubManage() {
        return footBallClubManage;
    }

    @Override
    public void writeBinary(List<Player> e, String path) {
        File file = new File(path);
        try (ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(e);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public ArrayList<Player> readBinary(String path) {
        File file = new File(path);
        ArrayList<Player> playerArrayList = new ArrayList<>();
        try (ObjectInputStream objectInputStream
                = new ObjectInputStream(new FileInputStream(file))) {
            playerArrayList = (ArrayList<Player>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println(ioException.getMessage());
        }
        return playerArrayList;
    }

    private String getGender() {
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Other");
        System.out.println("Input your choice: ");
        int choice = inputNum();
        switch (choice) {
            case 1:
                return "Male";
            case 2:
                return "Female";
            default:
                return "Other";
        }
    }

    private String getGender(int count) {
        System.out.println("Input gender player: ");
        String gender;
        do {
            gender = getGender();
            count++;
            if (count == 4) {
                return null;
            }
        } while (gender.equals(""));
        return gender;
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

    private Integer getAge(int count) {
        System.out.println("Input age player: ");
        int age;
        do {
            age = inputNum();
            count++;
            if (count == 4) {
                return null;
            } else if (age < 16 || age > 60) {
                System.out.println("Please re-input: ");
            }
        } while ((age < 16 || age > 60));
        return age;
    }

    private FootBallClub getFootBallClub() {
        System.out.println("Input football club of player: ");
        FootBallClub footBallClub;
        do {
            footBallClub = footBallClubManage.getByName();
        } while (footBallClub == null);
            return footBallClub;
    }

    @Override
    public Player create() {
        int count = 0;
        System.out.println("Input name player: ");
        String name = scanner.nextLine();
        Integer age = getAge(count);
        if (age == null) return  null;
        String gender = getGender(count);
        if (gender == null) return null;
        System.out.println("Input position player: ");
        String position = scanner.nextLine();
        System.out.println("Input address player: ");
        String address = scanner.nextLine();
        FootBallClub footBallClub = getFootBallClub();
        Player player = new Player(name, age, gender, position, address, footBallClub);
        players.add(player);
        writeBinary(players, PATH_FILE);
        return player;
    }

    @Override
    public Player update() {
        Player player = getById();
        if (player != null) {
            System.out.println("Input new name player: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                player.setName(name);
            }
            System.out.println("Input new age player: ");
            String age = scanner.nextLine();
            if (!age.equals("")) {
                player.setAge(Integer.parseInt(age));
            }
            System.out.println("Input new gender player: ");
            String gender;
            do {
                gender = getGender();
                if (!gender.equals("") && !gender.equals("Not change")) {
                    player.setGender(gender);
                }
            } while (gender.equals(""));
            System.out.println("Input new position player: ");
            String position = scanner.nextLine();
            if (!position.equals("")) {
                player.setPosition(position);
            }
            System.out.println("Input new address player: ");
            String address = scanner.nextLine();
            if (!address.equals("")) {
                player.setAddress(address);
            }
            System.out.println("Input new football club of player: ");
            FootBallClub footBallClub = getFootBallClub();
            if (footBallClub != null) {
                player.setFootBallClub(footBallClub);
            }
        }
        writeBinary(players, PATH_FILE);
        return player;
    }

    @Override
    public Player delete() {
        Player player = getById();
        if (player != null) {
            players.remove(player);
        }
        writeBinary(players, PATH_FILE);
        return player;
    }

    @Override
    public Player getById() {
        dissplayAll();
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

        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void dissplayAll() {
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%s",
                "ID", "NAME", "AGE", "GENDER", "POSITION", "ADDRESS", "FOOTBALL_CLUB\n");
        for (Player player : players) {
            player.display();
        }
    }

    public void displayOne(Player player) {
        if (player != null) {
            player.display();
        } else {
            System.out.println("Not exist this object!");
        }
    }

    public void displayPlayerByFootballClub() {
        footBallClubManage.dissplayAll();
        int id;
        try {
            System.out.println("Input id you want to choice: ");
            id = Integer.parseInt(scanner.nextLine());
            System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%s",
                    "ID", "NAME", "AGE", "GENDER", "POSITION", "ADDRESS", "FOOTBALL_CLUB\n");
            for (Player player :players) {
                if (player.getFootBallClub().getId() == id) {
                    displayOne(player);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
