import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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
        table.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
        );

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    int row = table.convertRowIndexToModel(table.getSelectedRow());
                    if (row < 0) return;

                    Book selectedBook = manager.getBooks().get(row);

                    Object[] options = {"Check Out / In", "Edit Book"};
                    int choice = JOptionPane.showOptionDialog(null, "What would you like to do with this book?", "Select Action", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]
                    );

                    if (choice == 0) {
                        CheckoutManager.handleBook(selectedBook);
                        table.repaint();

                    } else if (choice == 1) {
                        Book b = selectedBook;

                        JTextField titleField = new JTextField(b.getTitle());
                        JTextField authorField = new JTextField(b.getAuthor());
                        JTextField yearField = new JTextField(b.getYear());
                        JTextField isbnField = new JTextField(b.getISBN());

                        JComboBox<String> genreField = new JComboBox<>(new String[]{
                                "--Pick Genre--", "Romance", "Mystery", "Science Fiction", "Fantasy", "Biography", "Historical Fiction", "Thriller", "Textbook", "Other"
                        });
                        genreField.setSelectedItem(b.getGenre());

                        JComboBox<String> statusField = new JComboBox<>(new String[]{
                                "Available", "Checked Out"
                        });
                        statusField.setSelectedItem(b.getStatus());
                        statusField.setEnabled(false);

                        JTextField commentsField = new JTextField();

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
                        panel.add(commentsField);

                        while (true) {
                            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Book", JOptionPane.OK_CANCEL_OPTION);

                            if (result != JOptionPane.OK_OPTION) break;

                            if (titleField.getText().trim().isEmpty() ||
                                    authorField.getText().trim().isEmpty() ||
                                    yearField.getText().trim().isEmpty() ||
                                    isbnField.getText().trim().isEmpty() ||
                                    genreField.getSelectedItem().toString().equals("--Pick Genre--")) {

                                JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Missing Info", JOptionPane.WARNING_MESSAGE);

                            } else {
                                b.setTitle(titleField.getText());
                                b.setAuthor(authorField.getText());
                                b.setYear(yearField.getText());
                                b.setISBN(isbnField.getText());
                                b.setGenre(genreField.getSelectedItem().toString());
                                b.setStatus(statusField.getSelectedItem().toString());
                                b.setComments(commentsField.getText());

                                model.fireTableDataChanged();
                                table.repaint();
                                break;
                            }
                        }
                    }
                }
            }

        });

        add(new JScrollPane(table), BorderLayout.CENTER);


        table.setDefaultRenderer(Object.class,
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        Component c =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column);

                        if (row % 2 == 0) {
                            c.setBackground(new Color(255, 255, 255));
                        }
                        else {
                            c.setBackground(new Color(211, 211, 211));
                        }

                        if (isSelected) {
                            c.setBackground(Color.YELLOW);
                        }

                        return c;
                    }
                });



        JPanel top = new JPanel();
        JPanel bottom = new JPanel();

        JButton loadBtn = new JButton("Load");
        JButton saveBtn = new JButton("Save");
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton gameBtn = new JButton("Play Game");
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

        //GAME
        gameBtn.addActionListener(e -> {
            GameLauncher.launchGame();
        });
        // LOAD
        loadBtn.addActionListener(e -> {
            manager.loadFromFile("books.json");
            table.updateUI();
        });

        // SAVE
        saveBtn.addActionListener(e -> {
            manager.saveToFile("books.json");
        });



        // ADD
        addBtn.addActionListener(e -> {
            JTextField titleField = new JTextField();
            JTextField authorField = new JTextField();
            JTextField yearField = new JTextField();
            JTextField isbnField = new JTextField();
            JComboBox genreField = new JComboBox<>(new String[]{
                    "--Pick Genre--", "Romance", "Mystery", "Science Fiction", "Fantasy", "Biography", "Historical Fiction", "Thriller", "Textbook", "Other"
            });
            JComboBox statusField = new JComboBox<>(new String []{
                     "Available", "Checked Out"
            });
            statusField.setEnabled(false);
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

            while (true) {

                int result = JOptionPane.showConfirmDialog(this, panel, "Add Book", JOptionPane.OK_CANCEL_OPTION);

                if (result != JOptionPane.OK_OPTION) {
                    break;
                }

                if (titleField.getText().trim().isEmpty() ||
                        authorField.getText().trim().isEmpty() ||
                        yearField.getText().trim().isEmpty() ||
                        isbnField.getText().trim().isEmpty() ||
                        genreField.getSelectedItem().toString().equals("--Pick Genre--")) {

                    JOptionPane.showMessageDialog(this, "Please fill in ALL fields before adding the book!", "WARNING", JOptionPane.WARNING_MESSAGE);
                } else {
                    manager.addBook(new Book(
                            titleField.getText(),
                            authorField.getText(),
                            yearField.getText(),
                            isbnField.getText(),
                            genreField.getSelectedItem().toString(),
                            statusField.getSelectedItem().toString(),
                            comments.getText()
                    ));
                    model.fireTableDataChanged();
                    break;
                }
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

            table.clearSelection();

            for (int i = 0; i < manager.getBooks().size(); i++) {

                Book b = manager.getBooks().get(i);

                if (b.getTitle().toLowerCase().contains(query) ||
                        b.getAuthor().toLowerCase().contains(query) ||
                        b.getYear().contains(query) ||
                        b.getISBN().toLowerCase().contains(query) ||
                        b.getGenre().toLowerCase().contains(query) ||
                        b.getStatus().toLowerCase().contains(query) ||
                        b.getComments().toLowerCase().contains(query)) {

                    table.addRowSelectionInterval(i, i);
                }
            }
        });
    }

}
