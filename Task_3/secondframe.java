
                         //           THIS IS A ADD FRAME OF GUI PAGE


//                        students is my local Database name and studentsmanagement is my table name.
//           I have left username and Password of MySql Login to yours respective Sql credentials.So Please put username and Password First.



import com.mysql.cj.jdbc.Driver;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class secondframe extends JFrame {
    private JPanel secondpanel;
    private JLabel jlname;
    private JTextField textname;
    private JLabel rollno;
    private JTextField textroll;
    private JLabel grade;
    private JTextField textgrade;
    private JLabel phone;
    private JTextField textphone;
    private JButton submit;

    // CONSTRUCTOR OF SECOND FRAME CLASS.

    public secondframe(){
        setContentPane(secondpanel);
        setTitle("Add Student's Details");
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //  ADDING LISTENER TO SUBMIT BUTTON ALONG WITH CONNECTION OF THE DATABASE WITH THE PROGRAM.


        submit.addActionListener(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/students","root","password");
                System.out.println("Connection Established");
                String name = textname.getText();
                int rol = Integer.parseInt(textroll.getText());
                String grade = textgrade.getText();
                int ph = Integer.parseInt(textphone.getText());

                // ADDITION OF VALUES IN TABLE USING SQL COMMAND AS FOLLOWS.

                PreparedStatement pst = con.prepareStatement("insert into studentsmanagement(Name,RollNo,Grade,PhNo)values(?,?,?,?)");
                pst.setString(1,name);
                pst.setInt(2, rol);
                pst.setString(3,grade);
                pst.setInt(4, ph);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(secondframe.this,"Records inserted!");
                textname.setText("");
                textroll.setText("");
                textgrade.setText("");
                textphone.setText("");
                textname.requestFocus();
                con.close();

            } catch (SQLException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(secondframe.this,"Fields Cannot be left Empty!");
            }
            catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        new secondframe();
    }
}
