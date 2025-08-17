import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

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

    public boolean addPatron(Patron patron) {
        return patronRegistry.addPatron(patron);
    }

    public BorrowResult borrowBook(Book book, String name) {
        if (!bookCollection.containsKey(book)) {
            System.out.println("Book is not available in this library");
            return BorrowResult.BOOK_NOT_FOUND;
        }

        Patron patron = patronRegistry.getPatron(name);

        if (patron == null) {
            System.out.println("Patron is not registered. Please register first");
            return BorrowResult.PATRON_NOT_REGISTERED;
        }

        if (bookCollection.get(book) == 0) {
            System.out.println("Book is not available");
            return BorrowResult.NO_COPIES_AVAILABLE;
        }

        reduceCopy(book);
        Loan loan = new Loan(patron, book, LocalDate.now().plusDays(7), LocalDate.now());
        loanRecord.addLoan(loan);
        patron.addLoan(loan);
        return BorrowResult.SUCCESS;
    }

    public ReturnResult returnBook(Book book, String name) {
        if (!bookCollection.containsKey(book)) {
            return ReturnResult.NOT_LIBRARY_BOOK;
        }

        Patron patron = patronRegistry.getPatron(name);

        if (patron == null) {
            return ReturnResult.PATRON_NOT_REGISTERED;
        }

        Loan loan = loanRecord.getLoan(patron, book);

        if (loan == null) {
            return ReturnResult.LOAN_NOT_FOUND;
        }

        loanRecord.updateLoanStatus(LocalDate.now(), loan);
        increaseCopy(book);
        return loan.isOverDue(LocalDate.now());
    }

    public List<Loan> getLoanHistoryForPatron(String name) {
        Patron patron = patronRegistry.getPatron(name);

        if (patron == null) {
            return null;
        }

        return patron.getRecord();
    }

    public List<Loan> getAllLoanHistory() {
        return loanRecord.getLoanRecord();
    }

    public long getLoanFee(Loan loan) {
        return loanRecord.loanFee(loan);
    }

    private void reduceCopy(Book book) {
        bookCollection.replace(book, bookCollection.get(book) - 1);
    }

    private void increaseCopy(Book book) {
        bookCollection.replace(book, bookCollection.get(book) + 1);
    }
}