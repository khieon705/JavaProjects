import javax.security.auth.callback.LanguageCallback;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoanService {
    private final int loanFee = 10;
    private final ArrayList<Loan> loanRecord;

    public LoanService() {
        loanRecord = new ArrayList<>();
    }

    public Loan getLoan(Patron patron, Book book) {
        for (Loan loan : loanRecord) {
            if (loan.getBook().equals(book) && loan.getPatron().equals(patron)) {
                return loan;
            }
        }

        return null;
    }

    public void addLoan(Loan loan) {
        loanRecord.add(loan);
    }

    public void loanRules() {

    }

    public void viewLoan(Loan loan) {
        System.out.println("=== Loan Record ===");
        System.out.println(loan);
        System.out.println(returnMessage(loan));
    }

    public void updateLoanStatus(LocalDate date, Loan loan) {
        loan.setReturnDate(date);
        loan.returned();
    }

    private String returnMessage(Loan loan) {
        StringBuilder sb = new StringBuilder();

        sb.append("Book returned! ");
        if (loan.isOverDue()) {
            sb.append("Late by ").append(loan.daysDifference()).append(" ").append(loan.daysDifference() == 1 ? "day." : "days. ");
        } else {
            sb.append("On time. ");
        }

        sb.append("Fee: $").append(loanFee(loan));

        return sb.toString();
    }

    public long loanFee(Loan loan) {
        if (loan.isOverDue()) {
            return loan.daysDifference() * loanFee;
        }

        return 0;
    }
}
