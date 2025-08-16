import java.util.ArrayList;
import java.util.HashMap;

public class PatronRegistry {
    private final HashMap<String, Patron> patronList;

    public PatronRegistry() {
        patronList = new HashMap<>();
    }

    public void addPatron(Patron patron) {
        if (isDuplicate(patron.getName())) {
            System.out.println("Name is already registered");
            return;
        }

        patronList.put(patron.getName(), patron);
    }

    private boolean isDuplicate(String name) {
        return patronList.containsKey(name);
    }

    public Patron getPatron(String name) {
        return patronList.get(name);
    }

    public void viewPatronHistory(String name) {
        Patron patron = getPatron(name);

        if (patron == null) {
            System.out.println("Name entered is not registered");
            return;
        }

        ArrayList<Loan> record = patron.getRecord();

        if (record == null) {
            return;
        }

        System.out.println("=== Loan History ===");
        for (Loan loan : record) {
            System.out.println(loan);
            System.out.println();
        }
    }
}