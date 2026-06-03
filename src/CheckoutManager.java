import javax.swing.*;

 class CheckoutManager {
     public static void handleBook(Book book) {

         if (book.getStatus().equals("Available")) {

             String studentID =
                     JOptionPane.showInputDialog(
                             null,
                             "Enter 5-digit Student ID:"
                     );

             if (studentID == null) {
                 return;
             }

             if (studentID.length() != 5) {

                 JOptionPane.showMessageDialog(
                         null,
                         "Student ID must be 5 digits."
                 );

                 return;
             }

             book.setStatus("Checked Out");
             book.setCheckedOutBy(studentID);

             JOptionPane.showMessageDialog(
                     null,
                     "Book checked out successfully."
             );
         } else {

             Object[] options = {"Check In", "Close"};
             int choice = JOptionPane.showOptionDialog(
                     null,
                     "Checked out by Student ID: " + book.getCheckedOutBy(),
                     "Book Status",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.INFORMATION_MESSAGE,
                     null,
                     options,
                     options[1]
             );

             if (choice == 0) { // Check In clicked
                 String studentID = JOptionPane.showInputDialog(
                         null,
                         "Enter Student ID to check in:"
                 );

                 if (studentID == null) return;

                 if (!studentID.equals(book.getCheckedOutBy())) {
                     JOptionPane.showMessageDialog(
                             null,
                             "Incorrect Student ID. Cannot check in.",
                             "Error",
                             JOptionPane.ERROR_MESSAGE
                     );
                     return;
                 }

                 book.setStatus("Available");
                 book.setCheckedOutBy("");

                 JOptionPane.showMessageDialog(
                         null,
                         "Book checked in successfully."
                 );
             }
         }
     }

 }
