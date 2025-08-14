public record Book(String title, String author) {
    public Book(String title, String author) {
        this.title = StringFormat.formatStoredString(title);
        this.author = StringFormat.formatStoredString(author);
    }

    @Override
    public String toString() {
        return "Book Title: " + StringFormat.formatOutputString(title) + ", Author: " + StringFormat.formatOutputString(author);
    }
}
