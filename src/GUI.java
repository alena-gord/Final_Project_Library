import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private LibraryManager manager = new LibraryManager();
    private BookTableModel model = new BookTableModel(manager);
    private JTable table = new JTable(model);

    private JTextField searchField = new JTextField(20);

    public GUI() {
        setTitle("Library Manager");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel top = new JPanel();
        JButton loadBtn = new JButton("Load");
        JButton saveBtn = new JButton("Save");
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");

        top.add(loadBtn);
        top.add(saveBtn);
        top.add(addBtn);
        top.add(deleteBtn);
        top.add(searchField);
        top.add(searchBtn);

        add(top, BorderLayout.NORTH);

        // LOAD
        loadBtn.addActionListener(e -> {
            manager.loadFromFile("books.tsv");
            table.updateUI();
        });

        // SAVE
        saveBtn.addActionListener(e -> {
            manager.saveToFile("books.tsv");
        });

        // ADD
        addBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Title:");
            String author = JOptionPane.showInputDialog("Author:");
            String year = JOptionPane.showInputDialog("Year:");
            String ISBN = JOptionPane.showInputDialog("ISBN:");
            String genre = JOptionPane.showInputDialog("Genre:");
            String status = JOptionPane.showInputDialog("Status:");

            manager.addBook(new Book(title, author, year, ISBN, genre, status));
            table.updateUI();
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            manager.deleteBook(row);
            table.updateUI();
        });

        // SEARCH
        searchBtn.addActionListener(e -> {
            String query = searchField.getText().toLowerCase();

            for (int i = 0; i < manager.getBooks().size(); i++) {
                Book b = manager.getBooks().get(i);

                if (b.getTitle().toLowerCase().contains(query) ||
                        b.getAuthor().toLowerCase().contains(query) ||
                        b.getYear().contains(query)) {

                    table.setRowSelectionInterval(i, i);
                    break;
                }
            }
        });
    }
}
