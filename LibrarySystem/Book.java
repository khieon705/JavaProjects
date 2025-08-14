import java.util.Objects;

public class Book {
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = StringFormat.formatStoredString(title);
        this.author = StringFormat.formatStoredString(author);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book Title: " + StringFormat.formatOutputString(title) + ", Author: " + StringFormat.formatOutputString(author);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor());
    }

    /* Test
    public static void main(String[] args) {
        Book book = new Book("Calculus", "Stewart");

        System.out.println(book.getAuthor());
        System.out.println(book.getTitle());
        System.out.println(book);
    }
     */
}
