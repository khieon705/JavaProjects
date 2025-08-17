import java.util.List;
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
            System.out.println();

            switch (command) {
                case 1 -> addBook();
                case 2 -> addPatron();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> viewLoanInformation();
                case 6 -> {
                    System.out.println("Exiting Program...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Input. Try again");
            }

            System.out.println();
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = InputSanitizer.formatStoredString(scanner.nextLine());
        System.out.print("Enter book author: ");
        String author = InputSanitizer.formatStoredString(scanner.nextLine());
        System.out.print("Enter number of copies: ");
        int numOfCopies = Integer.parseInt(scanner.nextLine());

        library.addBook(new Book(title, author), numOfCopies);
    }

    private void addPatron() {
        System.out.print("Enter name: ");
        String name = InputSanitizer.formatStoredString(scanner.nextLine());

        if (library.addPatron(new Patron(name))) {
            System.out.println("Patron successfully registered");
        } else {
            System.out.println("Patron is already registered");
        }
    }

    private void borrowBook() {
        System.out.print("Enter book title: ");
        String title = InputSanitizer.formatStoredString(scanner.nextLine());
        System.out.print("Enter book author: ");
        String author = InputSanitizer.formatStoredString(scanner.nextLine());
        System.out.print("Enter patron name: ");
        String name = InputSanitizer.formatStoredString(scanner.nextLine());
        System.out.println();

        BorrowResult result = library.borrowBook(new Book(title, author), name);
        switch (result) {
            case SUCCESS -> System.out.println("Book borrowed successfully");
            case BOOK_NOT_FOUND ->  System.out.println("Book is not available in this library");
            case PATRON_NOT_REGISTERED -> System.out.println("Patron is not registered. Please register first");
            case NO_COPIES_AVAILABLE -> System.out.println("No copy available");
        }
    }

    private void returnBook() {
        System.out.print("Enter patron name: ");
        String name = InputSanitizer.formatStoredString(scanner.nextLine());
        System.out.print("Enter book title: ");
        String title = InputSanitizer.formatStoredString(scanner.nextLine());
        System.out.print("Enter book author: ");
        String author = InputSanitizer.formatStoredString(scanner.nextLine());
        System.out.println();

        Book book = new Book(title, author);
        ReturnResult result = library.returnBook(book, name);
        switch (result) {
            case SUCCESS_ON_TIME -> System.out.println("Book returned! On Time. Fee: $0");
            case SUCCESS_LATE -> {
                Loan loan = library.getLoanHistoryForPatron(name).stream()
                        .filter(l -> l.getBook().equals(book))
                        .findFirst()
                        .orElse(null);

                if (loan == null) {
                    System.out.println("Loan record was not found");
                    break;
                }

                System.out.println("Book returned! Late by " + loan.daysDifference());
                if (loan.daysDifference() > 1) {
                    System.out.println(" days. Fee: $" + library.getLoanFee(loan));
                } else {
                    System.out.println(" day. Fee: $" + library.getLoanFee(loan));
                }
            }
        }
    }

    private void viewLoanInformation() {
        System.out.print("View all? (Y/n) ");
        String input = InputSanitizer.formatStoredString(scanner.nextLine());

        if (input.equals("y")) {
            viewAllHistory(library.getAllLoanHistory());
        } else if (input.equals("n")) {
            System.out.print("Enter patron name: ");
            String name = InputSanitizer.formatStoredString(scanner.nextLine());
            viewLoanHistoryForPatron(name, library.getLoanHistoryForPatron(name));
        }
    }

    private void viewAllHistory(List<Loan> record) {
        System.out.println("=== All Loan Records ===");
        record.forEach(System.out::println);
    }

    private void viewLoanHistoryForPatron(String name, List<Loan> record) {
        if (record == null) {
            System.out.println("Patron is not registered and has no record");
            return;
        }

        System.out.println(InputSanitizer.formatOutputString(name) + " Record:");
        record.forEach(System.out::println);
    }
}