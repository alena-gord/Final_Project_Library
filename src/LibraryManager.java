import java.io.*;  // Import BufferedReader, FileReader, PrintWriter, FileWriter
import java.util.ArrayList;

public class LibraryManager {

    private ArrayList<Book> books = new ArrayList<>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    // SAVE JSON
    public void saveToFile(String filename) {

        try (PrintWriter //try to prevent errors
                     pw = new PrintWriter( //pw stands for print writer, used to intreract with the file
                             new FileWriter(filename))) {

            pw.println("[");

            for (int i = 0; i < books.size(); i++) {

                Book b = books.get(i);

                pw.print("{");

                pw.print("\"title\":\"" + b.getTitle() + "\",");
                pw.print("\"author\":\"" + b.getAuthor() + "\",");
                pw.print("\"year\":\"" + b.getYear() + "\",");
                pw.print("\"ISBN\":\"" + b.getISBN() + "\",");
                pw.print("\"genre\":\"" + b.getGenre() + "\",");
                pw.print("\"status\":\"" + b.getStatus() + "\"");

                pw.print("}");

                if (i < books.size() - 1) {
                    pw.println(",");
                }
            }

            pw.println();
            pw.println("]"); //all this is to make the file orgonized as a json file

        } catch (IOException e) {
            System.out.println("Error saving file."); //error handler
        }
    }

    // LOAD JSON
    public void loadFromFile(String filename) {

        books.clear();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (line.equals("[") || line.equals("]")) {
                    continue;
                }

                if (line.endsWith(",")) {
                    line = line.substring(0, line.length() - 1);
                }

                line = line.replace("{", "");
                line = line.replace("}", "");

                String[] parts = line.split(",");

                String title = "";
                String author = "";
                String year = "";
                String ISBN = "";
                String genre = "";
                String status = "";

                for (String p : parts) {

                    String[] keyValue = p.split(":");

                    String key = keyValue[0]
                            .replace("\"", "")
                            .trim();

                    String value = keyValue[1]
                            .replace("\"", "")
                            .trim();

                    if (key.equals("title")) {
                        title = value;
                    }

                    if (key.equals("author")) {
                        author = value;
                    }

                    if (key.equals("year")) {
                        year = value;
                    }

                    if (key.equals("ISBN")) {
                        ISBN = value;
                    }

                    if (key.equals("genre")) {
                        genre = value;
                    }

                    if (key.equals("status")) {
                        status = value;
                    }
                }

                books.add(new Book(
                        title,
                        author,
                        year,
                        ISBN,
                        genre,
                        status
                ));
            }

        } catch (IOException e) {
            System.out.println("Error loading file.");
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
}
