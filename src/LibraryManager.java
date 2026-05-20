import java.io.*; // Import BufferedReader, FileReader, PrintWriter, FileWriter
import java.util.*;

public class LibraryManager {
    private ArrayList<Book> books = new ArrayList<>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    // IMPORT TSV
    public void loadFromFile(String filename) {
        books.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { //use try to account of errors right away
            String line;

            while ((line = br.readLine()) != null) { //br is a buffered reader, so it reads line by line, stores it in a string, and returns it, makes sure the line is not null at the end.
                String[] parts = line.split("|");

                if (parts.length == 6) {
                    books.add(new Book(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // SAVE TSV
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {

            for (Book b : books) {
                pw.println(b.getTitle() + "|" + b.getAuthor() + "|" + b.getYear() + "|" + b.getISBN() + "|" + b.getGenre() + "|" + b.getStatus());
            }

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void deleteBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
        }
    }

    public Book getBook(int index) {
        return books.get(index);
    }
}

//note for Mr.Unland, we didn't really learn about working with files and java, and I am not sure if we will, but time is running out, so I alowed myself some creative freedom in this one.
