import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = -5843138276569669084L;
    public static int INDEX;
    private int id;
    private String name;
    private int age;
    private String gender;
    private String position;
    private String address;
    private FootBallClub footBallClub;

    public Player() {
    }

    public Player(String name, int age, String gender, String position, String address, FootBallClub footBallClub) {
        this.id = ++INDEX;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.position = position;
        this.address = address;
        this.footBallClub = footBallClub;
    }

    public Player(int id, String name, int age, String gender, String position, String address, FootBallClub footBallClub) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.position = position;
        this.address = address;
        this.footBallClub = footBallClub;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public FootBallClub getFootBallClub() {
        return footBallClub;
    }

    public void setFootBallClub(FootBallClub footBallClub) {
        this.footBallClub = footBallClub;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", position='" + position + '\'' +
                ", address='" + address + '\'' +
                ", footBallClub=" + footBallClub +
                '}';
    }

    public void display() {
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%s",
                this.id, this.name, this.age, this.gender,
                this.position, this.address, this.footBallClub.getName() + "\n");
    }
}
