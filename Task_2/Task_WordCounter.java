
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task_WordCounter extends JFrame {

    private JLabel jlFileAddress;
    private JTextField textField1;
    private JButton countWordsButton;
    private JPanel MainPanel;
    private JLabel jlText;
    private JTextField textField2;
    private JButton button2;
    private JLabel jltitle;

    public static String file_Reader(String filepath) throws FileNotFoundException {
        File input = new File(filepath);
        Scanner file = new Scanner(input);
        Map<String,Integer> words = new HashMap<>();
        if (!file.hasNext()) {
            return("File is Empty!");
        }
        else {
            file.useDelimiter("[,.?/()!@#%&:;{}$^*+_ ]");
            StringBuilder str = new StringBuilder();
            while (file.hasNext()) {
                str.append(file.next()).append(" ");
            }
            String[] new_str = str.toString().split(" ");
            for (String s : new_str) {
                if (words.containsKey(s)) {
                    words.put(s, 1 + words.get(s));
                } else {
                    words.put(s, 1);
                }
            }
        }
        return("The total number of words are = " +words.size() + "\n" + "Total Count For Each word and Extra WhiteSpaces are : " + words);
    }
    public static String text_Reader(String text){
        Scanner input = new Scanner(text);
        input.useDelimiter("[,.?/()!@#%&:;{}$^*+_ ]");
        StringBuilder str = new StringBuilder();
        while (input.hasNext()) {
            str.append(input.next()).append(" ");
        }
        String[] new_str = str.toString().split(" ");
        Map<String, Integer> words = new HashMap<>();
        for (String s : new_str) {
            if (words.containsKey(s)) {
                words.put(s, 1 + words.get(s));
            } else {
                words.put(s, 1);
            }
        }
        return("Total Words in Your Text are: " + words.size() +"\n"+ "Total Count of Each Words and Extra WhiteSpaces are: "+ words);
    }
    public Task_WordCounter (){
        setContentPane(MainPanel);
        setTitle("Word Counter");
        setSize(340,340);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        countWordsButton.addActionListener(e -> {
            String filename = textField1.getText();
            try {
                JOptionPane.showMessageDialog(Task_WordCounter.this,file_Reader(filename));
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(Task_WordCounter.this,"File Does Not Exists!");
            }
        });
        button2.addActionListener(e -> {
            String text = textField2.getText();
            JOptionPane.showMessageDialog(Task_WordCounter.this,text_Reader(text));
        });
    }

    public static void main(String[] args) {
        new Task_WordCounter();

    }
}
