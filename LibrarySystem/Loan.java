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

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void returned() {
        returned = true;
    }

    public boolean isOverDue() {
        return returnDate.isBefore(dueDate);
    }

    public long daysDifference() {
        return ChronoUnit.DAYS.between(dueDate, returnDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Patron: ").append(patron.getName()).append("\n");
        sb.append(book).append("\n");
        sb.append("Loan Date: ").append(loanDate).append("\n");
        sb.append("Due Date: ").append(dueDate).append("\n");
        sb.append("Return Date: ").append(returnDate == null ? "N/A" : returnDate).append("\n");
        sb.append("Status: ").append(!returned ? "Not yet returned" : "Returned").append("\n");

        return sb.toString();
    }
}
