import java.time.LocalDate;
import java.util.List;

public class Library {
    private final BookCollection bookCollection;
    private final PatronRegistry patronRegistry;
    private final LoanService loanRecord;

    public Library() {
        bookCollection = new BookCollection();
        patronRegistry = new PatronRegistry();
        loanRecord = new LoanService();
    }

    public void addBook(Book book, int numberOfCopies) {
        bookCollection.addBook(book, numberOfCopies);
    }

    public boolean addPatron(Patron patron) {
        return patronRegistry.addPatron(patron);
    }

    public BorrowResult borrowBook(Book book, String name) {
        if (bookCollection.hasBook(book)) {
            Patron patron = patronRegistry.getPatron(name);

            if (patron == null) {
                return BorrowResult.PATRON_NOT_REGISTERED;
            }

            if (bookCollection.isAvailable(book)) {
                bookCollection.reduceCopy(book);
                Loan loan = new Loan(patron, book, LocalDate.now().plusDays(7), LocalDate.now());
                loanRecord.addLoan(loan);
                patron.addLoan(loan);
                return BorrowResult.SUCCESS;
            }

            return BorrowResult.NO_COPIES_AVAILABLE;
        }

        return BorrowResult.BOOK_NOT_FOUND;
    }

    public ReturnResult returnBook(Book book, String name) {
        if (bookCollection.hasBook(book)) {
            Patron patron = patronRegistry.getPatron(name);

            if (patron == null) {
                return ReturnResult.PATRON_NOT_REGISTERED;
            }

            Loan loan = loanRecord.getLoan(patron, book);

            if (loan == null) {
                return ReturnResult.LOAN_NOT_FOUND;
            }

            loanRecord.updateLoanStatus(LocalDate.now(), loan);
            bookCollection.increaseCopy(book);
            return loan.isOverDue(LocalDate.now());
        }

        return ReturnResult.NOT_LIBRARY_BOOK;
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
}