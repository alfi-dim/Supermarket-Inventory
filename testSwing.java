import javax.swing.*;
import java.awt.event.*;

class testSwing{
    public static void main(String args[]) {
        JFrame f = new JFrame();
        
        JButton b = new JButton("click");
        b.setBounds(130,100,100,40);//x,y, widht, height
        
        f.add(b);//nambahin button
        
        f.setSize(400,500);//width, height
        f.setLayout(null);//using no layout manager
        f.setVisible(true);
        
        JFrame f2 = new JFrame("test");
        f2.setSize(200,300);
        f2.setLayout(null);
        f2.setVisible(false);
        
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f2.setVisible(true);
            }
        });
    }
}