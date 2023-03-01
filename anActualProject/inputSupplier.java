package anActualProject;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.awt.Insets;
import java.util.Random;

public class inputSupplier implements ActionListener {
    JTextField idSupplier, NamaSupplier, NoTelpSupplier;
    JButton Submit, Reset;
    JLabel idLabel,NamaLabel,NoTelpLabel;
    JFrame f = new JFrame("Input Supplier");
    inputSupplier() {
        f.setSize(400,350);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        titlePanel.setLayout(new BorderLayout(2,2));
        JLabel title = new JLabel("Input Supplier");
        title.setFont(new Font("Serif", Font.BOLD,20));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title, BorderLayout.CENTER);
        f.add(titlePanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        
        formPanel.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        idLabel = new JLabel("Id Supplier");
        NamaLabel = new JLabel("Nama Supplier");
        NoTelpLabel = new JLabel("Nomor Telpon Supplier");
        idSupplier = new JTextField();
        NamaSupplier = new JTextField();
        NoTelpSupplier = new JTextField();
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
        idSupplier.setEditable(false);
        String IdSupplier = idGenerator();
        idSupplier.setText(IdSupplier);
        formPanel.add(idSupplier,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        NamaLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(NamaLabel,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        formPanel.add(NamaSupplier,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        NoTelpLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(NoTelpLabel,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        formPanel.add(NoTelpSupplier,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        formPanel.add(Submit,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
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
                randomId = String.format("SP%03d", rand);
                query = "SELECT COUNT(*) as row FROM Supplier WHERE Id_Supplier LIKE '"+randomId+"';";
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
        if(e.getSource() == Submit){
            boolean check = 
                (idSupplier.getText().trim().isEmpty()) ||
                (NamaSupplier.getText().trim().isEmpty()) ||
                (NoTelpSupplier.getText().trim().isEmpty());
            
            if(check) {
                JOptionPane.showMessageDialog(null,"Semua Field Harus Diisi!");
                
            }
            else {
                prosesSupermarket proses = new prosesSupermarket();
                String id = idSupplier.getText();
                String nama = NamaSupplier.getText();
                String noTelp = NoTelpSupplier.getText();
            
                if(proses.insertSupplier(id,nama,noTelp)) {
                    JOptionPane.showMessageDialog(null,"Data Berhasil Diinput");
                    Supermarket sp = new Supermarket();
                    sp.btnRefreshS.setVisible(true);;
                    f.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Data Gagal Diinput");
                    
                }
            }
        }
        else if(e.getSource() == Reset) {
            idSupplier.setText("");
            NamaSupplier.setText("");
            NoTelpSupplier.setText("");
        }
    }
    
    public static void main(String args[]){
        new inputSupplier();
    }
}