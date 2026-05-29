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
        JPanel bottom = new JPanel();

        JButton loadBtn = new JButton("Load");
        JButton saveBtn = new JButton("Save");
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton gameBtn = new JButton("Game");
        JButton scanBtn = new JButton("Scanner");


        top.add(loadBtn);
        top.add(saveBtn);
        top.add(addBtn);
        top.add(deleteBtn);
        top.add(searchField);
        top.add(searchBtn);
        bottom.add(gameBtn);
        bottom.add(scanBtn);


        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);


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
//            String title = JOptionPane.showInputDialog("Title:");
//            String author = JOptionPane.showInputDialog("Author:");
//            String year = JOptionPane.showInputDialog("Year:");
//            String ISBN = JOptionPane.showInputDialog("ISBN:");
//            String genre = JOptionPane.showInputDialog("Genre:");
//            String status = JOptionPane.showInputDialog("Status:");
//
//            manager.addBook(new Book(title, author, year, ISBN, genre, status));
//            table.updateUI();
            JTextField titleField = new JTextField();
            JTextField authorField = new JTextField();
            JTextField yearField = new JTextField();
            JTextField isbnField = new JTextField();
            JComboBox genreField = new JComboBox<>(new String[]{
                    "--Pick Genre--", "Romance", "Mystery", "Science Fiction", "Fantasy", "Biography", "Historical Fiction", "Thriller", "Textbook", "Other"
            });
            JComboBox statusField = new JComboBox<>(new String []{
                    "--Update Status--", "Available", "Checked Out"
            });
            JTextField comments = new JTextField();

            JPanel panel = new JPanel(new GridLayout(8, 2, 10, 15));
            panel.add(new JLabel("Title:"));
            panel.add(new JLabel("Author:"));
            panel.add(titleField);
            panel.add(authorField);
            panel.add(new JLabel("Year:"));
            panel.add(new JLabel("ISBN:"));
            panel.add(yearField);
            panel.add(isbnField);
            panel.add(new JLabel("Genre:"));
            panel.add(new JLabel("Status:"));
            panel.add(genreField);
            panel.add(statusField);
            panel.add(new JLabel("Comments:"));
            panel.add(comments);

            int result = JOptionPane.showConfirmDialog(this, panel, "Add Book", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                manager.addBook(new Book(
                        titleField.getText(),
                        authorField.getText(),
                        yearField.getText(),
                        isbnField.getText(),
                        genreField.getSelectedItem().toString(),
                        statusField.getSelectedItem().toString()
                ));
                model.fireTableDataChanged();
            }
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
