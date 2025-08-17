import java.util.HashMap;
import java.util.Map;

public class BookCollection {
    private final Map<Book, Integer> bookCollection;

    public BookCollection() {
        bookCollection = new HashMap<>();
    }

    public void addBook(Book book, int numberOfCopies) {
        bookCollection.merge(book, numberOfCopies, Integer::sum);
    }

    public boolean hasBook(Book book) {
        return bookCollection.containsKey(book);
    }

    public boolean isAvailable(Book book) {
        return bookCollection.get(book) > 0;
    }

    public void reduceCopy(Book book) {
        bookCollection.replace(book, bookCollection.get(book) - 1);
    }

    public void increaseCopy(Book book) {
        bookCollection.replace(book, bookCollection.get(book) + 1);
    }
}
