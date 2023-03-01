package anActualProject;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.awt.Insets;
import java.util.Random;

public class inputKaryawan implements ActionListener{
    JTextField idKaryawan, NamaKaryawan;
    JButton Submit, Reset;
    JLabel idLabel, NamaLabel;
    JFrame f = new JFrame("Input Karyawan");
    
    inputKaryawan(){
        f.setSize(400,350);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        titlePanel.setLayout(new BorderLayout(2,2));
        JLabel title = new JLabel("Input Karyawan");
        title.setFont(new Font("Serif", Font.BOLD,20));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title, BorderLayout.CENTER);
        f.add(titlePanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        
        idLabel = new JLabel("Id Karyawan");
        NamaLabel = new JLabel("Nama Karyawan");
        idKaryawan = new JTextField();
        NamaKaryawan = new JTextField();
        Submit = new JButton("Submit");
        Reset = new JButton("Reset");
        Submit.addActionListener(this);
        Reset.addActionListener(this);
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0,5,7,5);
        
        c.gridx = 0;
        c.gridy = 0;
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(idLabel,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        idKaryawan.setEditable(false);
        String IdKaryawan = idGenerator();
        idKaryawan.setText(IdKaryawan);
        formPanel.add(idKaryawan,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        NamaLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(NamaLabel,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        formPanel.add(NamaKaryawan,c);
        
        c.gridx = 0;
        c.gridy = 2;
        formPanel.add(Submit,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        formPanel.add(Reset,c);
        
        f.add(formPanel);
        f.setVisible(true);
        
    }
    private String idGenerator(){
        try{
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/supermarket","root",""
            );
            Statement st = con.createStatement();
            boolean duplicate = true;
            String query;
            ResultSet rs;
            Random random = new Random();
            String randomId = new String();
            int rand,i;
            while (duplicate == true){
                i = 0;
                rand = random.nextInt(1000);
                randomId = String.format("KR%03d", rand);
                query = "SELECT COUNT(*) as row FROM Karyawan WHERE Id_Karyawan LIKE '"+randomId+"';";
                rs = st.executeQuery(query);
                while (rs.next()) {
                    i = rs.getInt("row");
                }
                if(i == 0) {
                    duplicate = false;
                }
            }
            return randomId;
        } catch (SQLException e){
            System.out.println(e);
            return "";
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return "";
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Submit) {
            boolean check = 
                (NamaKaryawan.getText().trim().isEmpty());
                
            if(check) {
                JOptionPane.showMessageDialog(null,"Semua Field Harus Diisi!");
                
            }
            else{
                prosesSupermarket proses = new prosesSupermarket();
                String id = idKaryawan.getText();
                String nama = NamaKaryawan.getText();
                if(proses.insertKaryawan(id,nama)) {
                    JOptionPane.showMessageDialog(null,"Data Berhasil Diinput");
                    Supermarket sp = new Supermarket();
                    sp.btnRefreshK.setVisible(true);;
                    f.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Data Gagal Diinput");
                
                }
            }
        }
        else if(e.getSource() == Reset) {
            idKaryawan.setText("");
            NamaKaryawan.setText("");
        }
    }
    public static void main(String args[]) {
        new inputKaryawan();
    }
}