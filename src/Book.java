public class Book {
    private String title;
    private String author;
    private String year;
    private String ISBN;
    private String genre;
    private String status;
    private String checkedOutBy;

    public Book(String title, String author, String year, String ISBN, String genre, String status) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.ISBN = ISBN;
        this.genre = genre;
        this.status = status;
    }
    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(String checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getYear() { return year; }
    public String getISBN() { return ISBN; }
    public String getGenre() { return genre; }
    public String getStatus() { return status; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(String year) { this.year = year; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setStatus(String status) { this.status = status; }
}
