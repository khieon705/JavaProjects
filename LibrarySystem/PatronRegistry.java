import java.util.HashMap;
import java.util.List;

public class PatronRegistry {
    private final HashMap<String, Patron> patronList;

    public PatronRegistry() {
        patronList = new HashMap<>();
    }

    public void addPatron(Patron patron) {
        if (isDuplicate(patron)) {
            System.out.println("Name is already registered");
            return;
        }

        patronList.put(patron.getName(), patron);
    }

    private boolean isDuplicate(Patron patron) {
        return patronList.containsValue(patron);
    }

    public Patron getPatron(String name) {
        return patronList.get(name);
    }
}