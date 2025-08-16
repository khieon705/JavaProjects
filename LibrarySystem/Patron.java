import java.util.ArrayList;
import java.util.Objects;

public class Patron {
    private final String name;
    private final ArrayList<Loan> record;

    public Patron(String name) {
        this.name = name;
        record = new ArrayList<>();
    }

    public void addLoan(Loan loan) {
        record.add(loan);
    }

    public ArrayList<Loan> getRecord() {
        return record;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Patron patron)) return false;
        return Objects.equals(getName(), patron.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
