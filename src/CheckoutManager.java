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
         }
         else {

             JOptionPane.showMessageDialog(
                     null,
                     "Checked out by Student ID: "
                             + book.getCheckedOutBy()
             );
         }
     }
}
