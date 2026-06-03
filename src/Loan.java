public class Loan {
    private Book book;
    private String studentID;

    public Loan(Book book, String studentID) {
        this.book = book;
        this.studentID = studentID;
    }

    public Book getBook() {
        return book;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
