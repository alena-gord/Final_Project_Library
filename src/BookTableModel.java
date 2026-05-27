import javax.swing.table.AbstractTableModel; //Using this because it's easier to implement, as it stores like a spreadsheet.'

public class BookTableModel extends AbstractTableModel {

    private LibraryManager manager;
    private String[] columns = {"Title", "Author", "Year", "ISBN", "Genre", "Status"};

    public BookTableModel(LibraryManager manager) {
        this.manager = manager;
    }

    @Override //all of the ovverides because of AbstractTableModel
    public int getRowCount() {
        return manager.getBooks().size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Book b = manager.getBooks().get(row);

        return switch (col) {
            case 0 -> b.getTitle();
            case 1 -> b.getAuthor();
            case 2 -> b.getYear();
            case 3 -> b.getISBN();
            case 4 -> b.getGenre();
            case 5 -> b.getStatus();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Book b = manager.getBooks().get(row);

        switch (col) {
            case 0 -> b.setTitle(value.toString());
            case 1 -> b.setAuthor(value.toString());
            case 2 -> b.setYear(value.toString());
            case 3 -> b.setISBN(value.toString());
            case 4 -> b.setGenre(value.toString());
            case 5 -> b.setStatus(value.toString());
        }

        fireTableCellUpdated(row, col);
    }
}