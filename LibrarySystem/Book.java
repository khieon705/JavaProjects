public record Book(String title, String author) {
    @Override
    public String toString() {
        return "Book(title=" + title + ", author=" + author + ")";
    }
}