import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
public class testInput implements ActionListener{
    JTextField tf1,tf2,tf3,tf4;
    JButton b1,b2;
    
    testInput(){
        JFrame f = new JFrame();
        tf1 = new JTextField();
        tf1.setBounds(50,50,150,20);
        tf2 = new JTextField();
        tf2.setBounds(50,100,150,20);
        tf3 = new JTextField();
        tf3.setBounds(50,150,150,20);
        tf3.setEditable(false);
        tf4 = new JTextField();
        tf4.setBounds(50,200,150,20);
        tf4.setEditable(false);
        b1 = new JButton("Submit");
        b1.setBounds(50,300,100,50);
        b2 = new JButton("Reset");
        b2.setBounds(150,300,100,50);
        b1.addActionListener(this);
        b2.addActionListener(this);
        f.add(tf1);f.add(tf2);f.add(tf3);f.add(tf4);f.add(b1);f.add(b2);
        f.setSize(300,400);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        Random random = new Random();
        int s1 = random.nextInt(1000);
        String s2 = String.format("%03d",s1);
        if(e.getSource() == b1){
            tf3.setText(s1+" "+s2);
            tf4.setText(tf3.getText());
            boolean check = (tf1.getText().trim().isEmpty()) || (tf2.getText().trim().isEmpty());
            if (check){
                tf4.setText("kosong");
            }
        }
        else if(e.getSource() == b2){
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText(tf3.getText());
        }
    }
    
    public static void main(String args[]){
        new testInput();
    }
}