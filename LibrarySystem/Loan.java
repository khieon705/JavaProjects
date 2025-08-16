import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private final Patron patron;
    private final Book book;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private final LocalDate loanDate;
    private boolean returned;

    public Loan(Patron patron, Book book, LocalDate dueDate, LocalDate loanDate) {
        this.patron = patron;
        this.book = book;
        this.dueDate = dueDate;
        this.loanDate = loanDate;
        returned = false;
    }

    public Patron getPatron() {
        return patron;
    }

    public Book getBook() {
        return book;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void returned() {
        returned = true;
    }

    public ReturnResult isOverDue(LocalDate date) {
        return date.isAfter(dueDate) ? ReturnResult.SUCCESS_LATE : ReturnResult.SUCCESS_ON_TIME;
    }

    public long daysDifference() {
        return ChronoUnit.DAYS.between(dueDate, returnDate);
    }

    @Override
    public String toString() {
        return String.format("Loan(Patron=%s, %s, DueDate=%s, ReturnDate=%s, LoanDate=%s, Returned=%b)", patron.getName(), book, dueDate, returnDate, loanDate, returned);
    }
}
