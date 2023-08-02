
                             //    THIS IS THE MAIN GUI PAGE OF STUDENTS MANAGEMENT SYSTEM.

//                        students is my local Database name and studentsmanagement is my table name.
//           I have left username and Password of MySql Login to yours respective Sql credentials.So Please put username and Password First.



import com.mysql.cj.jdbc.Driver;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class StudentManagementSystem extends JFrame{

    private JPanel mainpanel;
    private JButton adding;
    private JButton editing;
    private JTextField searchfield;
    private JButton display;
    private JButton search;
    private JTable table1;


    // SEARCH CLASS FOR SEARCHING A STUDENT.

    void searchtable(){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/students","root","password");

            // SEARCHING OF VALUES IN THE TABLE USING SQL COMMAND AS FOLLOWS.

            PreparedStatement pst = con.prepareStatement("select * from studentsmanagement where Name = ?");
            pst.setString(1, searchfield.getText());
            ResultSet rst2 = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rst2));
            if(!rst2.isAfterLast()){
                JOptionPane.showMessageDialog(null,"No Record Found!");
            }
            searchfield.setText("");
            con.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        catch(NumberFormatException ex){
            System.out.println("Field cannot be left Empty!");
        }
    }

    //    DISPLAY CLASS FOR DISPLAYING ALL RECORDS IN STUDENTS FORM.

    void table(){
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/students","root","password");

            // DISPLAYING VALUES OF TABLE USING SQL COMMAND AS FOLLOWS.

            PreparedStatement pst = con.prepareStatement("select * from studentsmanagement order by Name");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //    CONSTRUCTOR TO SHOW MAIN GUI PAGE OF STUDENTS MANAGEMENT SYSTEM.

    public StudentManagementSystem(){
        setContentPane(mainpanel);
        setTitle("Student Management Section");
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //  CALLING OF SECOND FRAME(ADD FRAME) USING LISTENER TO ADD BUTTON.

        adding.addActionListener(e -> new secondframe());

        //  CALLING EDIT USING LISTENER TO EDIT BUTTON.

        editing.addActionListener(e -> new editframe());

        //  CALLING DISPLAY TABLE CLASS FROM ABOVE USING LISTENER TO DISPLAY BUTTON.

        display.addActionListener(e -> {
            table();
        });

       //  CALLING OF SEARCH TABLE CLASS FROM ABOVE USING LISTENER TO SEARCH BUTTON.

        search.addActionListener(e -> {
            searchtable();
        });
    }


    //      DRIVER CLASS TO RUN THE PROGRAM.


    public static void main(String[] args) {

        // CALLING OF METHOD CREATED ABOVE TO DISPLAY GUI TO THE USER.

        new StudentManagementSystem();
    }
}
