
                               //    THIS IS A EDIT FRAME OF GUI PAGE

//                        students is my local Database name and studentsmanagement is my table name.
//           I have left username and Password of MySql Login to yours respective Sql credentials.So Please put username and Password First.




import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class editframe extends JFrame {
    private JPanel thirdpanel;
    private JLabel editname;
    private JTextField textField1;
    private JLabel editroll;
    private JTextField textField2;
    private JLabel editgrade;
    private JTextField textField3;
    private JLabel editphone;
    private JTextField textField4;
    private JButton submit;

    //   CONSTRUCTOR OF EDIT FRAME CLASS.
    public editframe(){
        setContentPane(thirdpanel);
        setTitle("Edit Student's Details");
        setSize(380,380);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        // ADDING LISTENER TO SUBMIT BUTTON ALONG WITH CONNECTION OF THE DATABASE INTO TE PROGRAM.


        submit.addActionListener(e -> {
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/students","root","password");

                // UPDATING/EDITING OF VALUES IN TABLE USING SQL COMMAND AS FOLLOWS.

                PreparedStatement pst = con.prepareStatement("Update studentsmanagement set RollNO=?,Grade=?,PhNo=? where Name =?");
                String name = textField1.getText();
                int rol = Integer.parseInt(textField2.getText());
                String grade = textField3.getText();
                int ph = Integer.parseInt(textField4.getText());
                pst.setInt(1, rol);
                pst.setString(2,grade);
                pst.setInt(3, ph);
                pst.setString(4,name);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(editframe.this,"Record Edited!");
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField1.requestFocus();
                con.close();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(editframe.this,"Fields Cannot be left Empty!");
            }
        });
    }


    public static void main(String[] args) {
        new editframe();
    }
}
