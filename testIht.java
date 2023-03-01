import javax.swing.*;
import java.awt.*;

public class testIht{
    static class testPanel extends JPanel{
        
        
        testPanel(){
            setBounds(0,0,200,200);
            setBackground(Color.gray);
            
            JButton b1 = new JButton();
            b1.setBounds(50,50,50,50);
            add(b1);
        }
    }
    static class testPanel2 extends JPanel{
        
        
        testPanel2(){
            setBounds(0,0,200,200);
            setBackground(Color.red);
            
            JButton b1 = new JButton();
            b1.setBounds(50,50,50,50);
            add(b1);
        }
    }
    
    public static void main(String args[]){
        JPanel p = new testPanel();
        JPanel p2 = new testPanel2();
        JTabbedPane tp = new JTabbedPane();
        tp.add("Panel 1",p);
        tp.add("Panel 2",p2);
        JFrame f = new JFrame();
        f.add(tp);
        f.setSize(300,300);
        f.setVisible(true);
    }
}