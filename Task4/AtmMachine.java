import javax.swing.*;
import java.util.*;

                         /*A Class named as UserAccount To change or Modify user account details.*/
class UserAccount {

                   /* Here a Sql Program can also be Created to store user account details Permanently in a Database/Table.
                      But It was Just Asked to Make an Interface of ATM functions,So a Prototype has been created.*/
     public static Map account(){
         Map<String,Long> acc = new HashMap<>();
         acc.put("24AD4561", 16000098L);
         acc.put("8796CD05",1765344L);        /* Here These Amount values are Redundant for each run of the Program.*/
         acc.put("6574RT83",10000000L);
         return acc;
    }

    /* Method WithDrawl of ATM MACHINE*/
     public static Object WithDrawl(String AcNO, Long amount){
         Map check = account();
         if(!check.containsKey(AcNO)){
             return (" No Such Account Found! ");
         }else {
             Long CurrentValue = (Long) check.get(AcNO);
             long NewValue = CurrentValue - amount;
             if (amount >= 30000) {
                 return (" Amount Exceeding Withdrawal Limit!! ");
             } else if (CurrentValue <= amount) {
                 return (" Amount Exceeding Total Balance Amount!! ");
             } else if (NewValue == 0) {
                 return (" Your Account Balance will become Zero and this is against our Bank Policies ! " + " Please Decrease your withdrawing limit");
             } else {
                 check.put(AcNO, NewValue);
                 return (" Amount Withdrawn: " + amount + "\n" + "New Balance: " + NewValue);
             }
         }
     }

     /* Method Deposit of ATM MACHINE*/

     public static Object Deposit(String AcNO,Long amount){
         Map check = account();
         if(!check.containsKey(AcNO)){
             return (" No Such Account Found! ");
         }
         else {
             Long CurrentValue = (Long) check.get(AcNO);
             Long NewValue = CurrentValue + amount;
             check.put(AcNO, NewValue);
             return(" Amount Added: " + amount + "\n" + " New Balance: " + NewValue);
         }
     }

     /* Method CheckBalance of ATM MACHINE*/

     public static Object CheckBalance(String AcNo){
         Map check = account();
         if(!check.containsKey(AcNo)){
             return (" No Such Account Found! ");
         }else {
             return (check.get(AcNo));
         }
     }
}

                                     /* Class AtmMachine to represent GUI for ATM MACHINE */

 public class AtmMachine extends JFrame {

    private JPanel MainPanel;
    private JLabel jlPin;
    private JTextField AccountText;
    private JLabel jlAmount;
    private JTextField amountText;
    private JButton WDButton;
    private JButton DPButton;
    private JLabel jlChecking;
    private JTextField CheckText;
    private JButton CBButton;
    private JLabel jlTitle;
    private JSeparator separator;

            /* Constructor to Create GUI */
    public AtmMachine(){
        setContentPane(MainPanel);
        setTitle("ATM MACHINE");
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

            /* Action Listeners for different Buttons of GUI along with calling of methods of User Account Class */

        WDButton.addActionListener(e -> {
            try {
                String AcNO = AccountText.getText();
                Long amount = (long) Integer.parseInt(amountText.getText());
                JOptionPane.showMessageDialog(AtmMachine.this, UserAccount.WithDrawl(AcNO, amount));
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(AtmMachine.this, " Field is Empty!");
            }
            AccountText.requestFocus();
        });

        DPButton.addActionListener(e -> {
            try{
            String AcNO = AccountText.getText();
            Long amount = (long)Integer.parseInt(amountText.getText());
            JOptionPane.showMessageDialog(AtmMachine.this,UserAccount.Deposit(AcNO,amount));
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(AtmMachine.this, " Field is Empty!");
            }
            AccountText.requestFocus();
        });

        CBButton.addActionListener(e -> {
            try {
                String AcNo = CheckText.getText();
                JOptionPane.showMessageDialog(AtmMachine.this, UserAccount.CheckBalance(AcNo));
            }
            catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(AtmMachine.this, " Field is Empty!");
            }
            CheckText.requestFocus();
        });
    }

                /* Driver/Main Function of Program */

    public static void main(String[] args) {

        new AtmMachine();
    }
}


