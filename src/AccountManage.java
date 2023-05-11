import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountManage implements IOFile<Account> {
    private ArrayList<Account> accounts;
    private Scanner scanner;
    private String accountPath = "D:\\02_Study\\casestudy_md2\\src\\data\\account.txt";
    private String loggingUserPath = "D:\\02_Study\\casestudy_md2\\src\\data\\loggingUserName.txt";

    public AccountManage() {
        accounts = readBinary(accountPath);
        scanner = new Scanner(System.in);
    }

    @Override
    public void writeBinary(List<Account> e, String path) {
        File file = new File(path);
        try (ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(e);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public ArrayList<Account> readBinary(String path) {
        File file = new File(path);
        ArrayList<Account> newAccounts = new ArrayList<>();
        try (ObjectInputStream objectInputStream
                = new ObjectInputStream(new FileInputStream(file))) {
            newAccounts = (ArrayList<Account>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println(ioException.getMessage());
        }
        return newAccounts;
    }

    public Account createAccount() {
        int id = getLastAccountId() + 1;
        String userName = getValidUserName();
        String passWord = getValidPassWord();
        String phoneNumber = getValidPhoneNumber();
        String email = getValidEmail();
        Account account = new Account(id, userName, passWord, phoneNumber, email);
        accounts.add(account);
        writeBinary(accounts, accountPath);
        System.out.println("You have successfully created an account");
        return account;
    }

    private String getValidPhoneNumber() {
        boolean check;
        String phoneNumber;
        do {
            System.out.println("Enter phone number (10 numbers, start with 0)");
            phoneNumber = scanner.nextLine();
            check = ValidateData.validatePhone(phoneNumber);
            if (!check) System.out.println("Invalid !! Pls re-input");
        } while (!check);
        return phoneNumber;
    }

    private String getValidPassWord() {
        boolean check;
        String passWord;
        do {
            System.out.println("Enter password (at least 6 char, start with capital letter)");
            passWord = scanner.nextLine();
            check = ValidateData.validatePassWord(passWord);
            if (!check) System.out.println("Invalid !! Pls re-input");
        } while (!check);
        return passWord;
    }

    private String getValidUserName() {
        boolean checkRegex;
        boolean checkUserNameExisted;
        String userName;
        do {
            System.out.println("Enter username (at least 6 char _ is allowed)");
            userName = scanner.nextLine();
            checkRegex = ValidateData.validateUserName(userName);
            checkUserNameExisted = checkUserNameExisted(userName);
            if (!checkRegex || checkUserNameExisted)
                System.out.println("Pls re-input because username is invalid or existed !!");
        } while (!checkRegex || checkUserNameExisted);
        return userName;
    }

    private String getValidEmail() {
        boolean check;
        String email;
        do {
            System.out.println("Enter email (example: example@gmail.com)");
            email = scanner.nextLine();
            check = ValidateData.validateEmail(email);
            if (!check) System.out.println("Invalid !! Pls re-input");
        } while (!check);
        return email;
    }

    public int getLastAccountId() {
        if (accounts.size() == 0) return 0;
        else return accounts.get(accounts.size() - 1).getId();
    }

    public void displayAllAccount() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public boolean checkUserNameExisted(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equalsIgnoreCase(userName)) return true;
        }
        return false;
    }

    public boolean checkLogin(String userName, String passWord) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName) && account.getPassWord().equals(passWord)) {
                return true;
            }
        }
        return false;
    }

    public String login() {
        boolean checkLogin;
        String userName;
        String passWord;
        int count = 0;
        do {
            count++;
            System.out.println("Enter your username");
            userName = scanner.nextLine();
            System.out.println("Enter your password");
            passWord = scanner.nextLine();
            checkLogin = checkLogin(userName, passWord);
            if (count == 3) {
                System.out.println("\033[31mYou have 3 times wrong input !! " +
                        "If you dont have account pls register !! If you forgot password press 3 !!\033[0m");
                return null;
            }
            if (checkLogin) {
                System.out.println("Login success !! Welcome " + userName);
            } else {
                System.out.println("Pls re-input because username or password is wrong!!");
            }
        } while (!checkLogin);
        ArrayList<Account> loggingUserList = new ArrayList<>();
        loggingUserList.add(getAccountByUserName(userName));
        writeBinary(loggingUserList, loggingUserPath);
        return userName;
    }

    public Account getAccountById() {
        displayAllAccount();
        int id = -1;
        try {
            System.out.println("Input id of account you want");
            id = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Account account : accounts) {
            if (account.getId() == id) return account;
        }
        return null;
    }

    public void deleteAccount() {
        Account deleteAccount = getAccountById();
        if (deleteAccount == null) System.out.println("Not have that id");
        else {
            accounts.remove(deleteAccount);
            System.out.println("Delete success!!");
        }
        writeBinary(accounts, accountPath);
    }

    public Account getAccountByUserName(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName)) return account;
        }
        return null;
    }

    public void updateAccount() {
        ArrayList<Account> loggingUserList = readBinary(loggingUserPath);
        for (Account account : accounts) {
            if (account.getUserName().equals(loggingUserList.get(0).getUserName())) {
                String passWord = getValidPassWord();
                String phoneNumber = getValidPhoneNumber();
                String email = getValidEmail();
                account.setPassWord(passWord);
                account.setPhoneNumber(phoneNumber);
                account.setEmail(email);
                System.out.println("Update success!!");
                writeBinary(accounts, accountPath);
                break;
            }
        }
    }

    public void findPassWord() {
        System.out.println("Input your username");
        String userName = scanner.nextLine();
        Account account = getAccountByUserName(userName);
        if (account != null) {
            System.out.println("Input your phone number");
            String phoneNumber = scanner.nextLine();
            if (account.getPhoneNumber().equals(phoneNumber)) {
                System.out.println("Your password is : " + account.getPassWord());
            } else {
                System.out.println("Your phone number is wrong !!");
            }
        } else {
            System.out.println("Your username is wrong !!");
        }
    }
    public void deleteAllAccount(){
        accounts.removeIf(account -> !account.getUserName().equals("minh123"));
        writeBinary(accounts,accountPath);
        System.out.println("Delete success !!");
    }
}
