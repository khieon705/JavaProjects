import java.time.LocalDate;
import java.util.ArrayList;

public class LoanService {
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

    public void updateLoanStatus(LocalDate date, Loan loan) {
        loan.setReturnDate(date);
        loan.returned();
    }

    public long loanFee(Loan loan) {
        if (loan.isOverDue(loan.getReturnDate())) {
            int loanFee = 10;
            return loan.daysDifference() * loanFee;
        }

        return 0;
    }

    public ArrayList<Loan> getLoanRecord() {
        return loanRecord;
    }
}
