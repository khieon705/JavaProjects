import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final Library library;

    public UserInterface(Scanner scanner, Library library) {
        this.scanner = scanner;
        this.library = library;
    }

    public void start() {
        while (true) {
            System.out.println("=== Library System ===");
            System.out.println("[1] Add Book");
            System.out.println("[2] Add Patron");
            System.out.println("[3] Borrow Book");
            System.out.println("[4] Return Book");
            System.out.println("[5] View Patron Information");
            System.out.println("[6] Exit Program");
            System.out.print("Enter Command: ");
            int command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1 -> addBook();
                case 2 -> addPatron();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 6 -> {
                    System.out.println("Exiting Program...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Input. Try again");
            }

        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter number of copies: ");
        int numOfCopies = Integer.parseInt(scanner.nextLine());

        library.addBook(new Book(title, author), numOfCopies);
    }

    private void addPatron() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        library.addPatron(new Patron(name));
    }

    private void borrowBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();

        library.borrowBook(new Book(title, author), name);
    }

    private void returnBook() {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        library.returnBook(new Book(title, author), name);
    }

    private void viewPatronInformation() {

    }
}
