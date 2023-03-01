import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class testFrame{
    public static void main(String args[]){
        JFrame f = new JFrame();
        testTable2 testT = new testTable2();
        testTable2 testT2 = new testTable2();
        JButton b = new JButton("testTable1");
        JButton c = new JButton("testTable2");
        b.setBounds(130,100,100,40);//x,y, widht, height
        c.setBounds(260,100,100,40);
        f.add(b);//nambahin button
        f.add(c);
        f.setSize(400,500);//width, height
        f.setLayout(null);//using no layout manager
        f.setVisible(true);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                testT.readTable1();
            }
        });
        c.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                testT.readTable2();
            }
        });
    }
}