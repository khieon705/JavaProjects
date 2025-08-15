import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    private final HashMap<Book, Integer> bookCollection;
    private final PatronRegistry patronRegistry;
    private final LoanService loanRecord;

    public Library() {
        bookCollection = new HashMap<>();
        patronRegistry = new PatronRegistry();
        loanRecord = new LoanService();
    }

    public void addBook(Book book, int numberOfCopies) {
        if (bookCollection.containsKey(book)) {
            bookCollection.replace(book, bookCollection.get(book) + numberOfCopies);
        } else {
            bookCollection.put(book, numberOfCopies);
        }
    }

    public void addPatron(Patron patron) {
        patronRegistry.addPatron(patron);
    }

    public void borrowBook(Book book, String name) {
        if (!bookCollection.containsKey(book)) {
            System.out.println("Book is not available in this library");
            return;
        }

        Patron patron = patronRegistry.getPatron(name);

        if (patron == null) {
            System.out.println("Patron is not registered. Please register first");
            return;
        }

        if (bookCollection.get(book) == 0) {
            System.out.println("Book is not available");
            return;
        }

        reduceCopy(book);
        loanRecord.addLoan(new Loan(patron, book, LocalDate.now().plusDays(7), LocalDate.now()));
    }

    public void returnBook(Book book, String name) {
        if (!bookCollection.containsKey(book)) {
            System.out.println("Book is not from this library");
            return;
        }

        Patron patron = patronRegistry.getPatron(name);
        Loan loan = loanRecord.getLoan(patron, book);
        loanRecord.updateLoanStatus(LocalDate.now(), loan);
        loanRecord.viewLoan(loan);
    }

    private void reduceCopy(Book book) {
        bookCollection.replace(book, bookCollection.get(book) - 1);
    }

    private void increaseCopy(Book book) {
        bookCollection.replace(book, bookCollection.get(book) + 1);
    }
}