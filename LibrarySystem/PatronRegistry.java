import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class PatronRegistry {
    private final Map<String, Patron> patronList;

    public PatronRegistry() {
        patronList = new HashMap<>();
    }

    public boolean addPatron(Patron patron) {
        if (isDuplicate(patron)) {
            return false;
        }

        patronList.put(patron.getName(), patron);
        return true;
    }

    private boolean isDuplicate(Patron patron) {
        return patronList.containsValue(patron);
    }

    public Patron getPatron(String name) {
        return patronList.get(name);
    }

    public Map<String, Patron> getPatronList() {
        return Collections.unmodifiableMap(patronList);
    }
}