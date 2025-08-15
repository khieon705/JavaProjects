import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        UserInterface UI = new UserInterface(scanner, library);

        UI.start();
    }
}
