package anActualProject;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.awt.Insets;
import java.util.Random;
import java.util.ArrayList;

public class inputBarang implements ActionListener{
    
    JTextField idBarang,NamaBarang,StokBarang,HargaBarang;
    JButton Submit, Reset;
    JLabel idLabel,NamaLabel,StokLabel,HargaLabel,SupplierLabel;
    JComboBox comboIdSupplier;
    ArrayList<String> list = new ArrayList<String>();
    String query;
    JFrame f = new JFrame("Input Barang");
    
    inputBarang(){
        f.setSize(400,350);
        f.setLocationRelativeTo(null);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        titlePanel.setLayout(new BorderLayout(2,2));
        JLabel title = new JLabel("Input Barang");
        title.setFont(new Font("Serif", Font.BOLD,20));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title, BorderLayout.CENTER);
        f.add(titlePanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        
        formPanel.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        idLabel = new JLabel("Id Barang");
        NamaLabel = new JLabel("Nama Barang");
        StokLabel = new JLabel("Stok Barang");
        HargaLabel = new JLabel("Harga Barang");
        SupplierLabel = new JLabel("Id Supplier");
        idBarang = new JTextField();
        NamaBarang = new JTextField();
        StokBarang = new JTextField();
        HargaBarang = new JTextField();
        comboIdSupplier = new JComboBox();
        Submit = new JButton("Submit");
        Reset = new JButton("Reset");
        Submit.addActionListener(this);
        Reset.addActionListener(this);
        
        getIdSupplier();
        
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
        idBarang.setEditable(false);
        String IdBarang = idGenerator();
        idBarang.setText(IdBarang);
        formPanel.add(idBarang,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        NamaLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(NamaLabel,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        formPanel.add(NamaBarang,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        StokLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(StokLabel,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        formPanel.add(StokBarang,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        HargaLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(HargaLabel,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        formPanel.add(HargaBarang,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        SupplierLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(SupplierLabel,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        DefaultComboBoxModel model = new DefaultComboBoxModel(list.toArray());
        comboIdSupplier.setModel(model);
        formPanel.add(comboIdSupplier,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        formPanel.add(Submit,c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
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
                randomId = String.format("BR%03d", rand);
                query = "SELECT COUNT(*) as row FROM Barang WHERE Id_Barang LIKE '"+randomId+"';";
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
    void getIdSupplier(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/supermarket","root",""
            );
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("select Nama_Supplier from Supplier");
            
            while(rs.next()){
                String namaSupplier = rs.getString("Nama_Supplier");
                list.add(namaSupplier);
            }  
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Submit){
            boolean check = 
                (NamaBarang.getText().trim().isEmpty()) ||
                (StokBarang.getText().trim().isEmpty()) ||
                (HargaBarang.getText().trim().isEmpty());
            
            if(check) {
                JOptionPane.showMessageDialog(null,"Semua Field Harus Diisi!");
                
            }
            else{
                prosesSupermarket proses = new prosesSupermarket();
                String id = idBarang.getText();
                String nama = NamaBarang.getText();
                int stok = Integer.parseInt(StokBarang.getText());
                int harga = Integer.parseInt(HargaBarang.getText());
            
                String getSupplier = comboIdSupplier.getSelectedItem().toString();
            
                if(proses.insertBarang(id,nama,stok,harga,getSupplier)) {
                    JOptionPane.showMessageDialog(null,"Data Berhasil Diinput");
                    Supermarket sp = new Supermarket();
                    sp.btnRefreshB.setVisible(true);;
                    f.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Data Gagal Diinput");
                
                }
            }
            
        }
        else if(e.getSource() == Reset) {
            NamaBarang.setText("");
            StokBarang.setText("");
            HargaBarang.setText("");
        }
    }
    public static void main(String args[]){
        new inputBarang();
    }
}