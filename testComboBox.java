import javax.swing.*;
import java.util.ArrayList;
import java.sql.*;
import java.awt.Component;
import java.awt.event.*;

public class testComboBox implements ActionListener{
    JButton btn = new JButton("ASD");
    JFrame f = new JFrame();
    JTextField test = new JTextField();
    JComboBox testCombo = new JComboBox();
    ArrayList<String> value = new ArrayList<String>();
    
    testComboBox(){
        
        testR();
        testCombo.setBounds(50,50,90,20);
        DefaultComboBoxModel model = new DefaultComboBoxModel(value.toArray());
        testCombo.setModel(model);
        //testR te = new testR();
        //String nama = te.Nama;
        test.setBounds(50,100,90,20);
        
        btn.setBounds(50,150,100,50);
        btn.addActionListener(this);
        f.add(testCombo);
        f.add(test);
        f.add(btn);
        f.setLayout(null);
        f.setSize(300,300);
        f.setVisible(true);
    }
    
     void testR(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testKoneksi","root",""
            );
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from testKoneksi");
            
            while(rs.next()){
                int id = rs.getInt("id");
                
                String Nama = rs.getString("nama");
                value.add(Nama);
            }  
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == btn){
            String text = testCombo.getSelectedItem().toString();
            test.setText(text);
        }
    }
    public static void main(String args[]) {
        new testComboBox();
    }
}