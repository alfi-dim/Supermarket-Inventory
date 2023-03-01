import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.*;

public class Supermarket {
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    JTable jt = new JTable(defaultTableModel);
    static JButton btnRefreshB,btnRefreshS,btnRefreshK;
    static class panelBarang extends JPanel{
        panelBarang(){
            setBounds(0,0,300,350);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0,5,7,5);
            
            DefaultTableModel defaultTableModel = new DefaultTableModel();
            JTable jt = new JTable(defaultTableModel);
            jt.setDefaultEditor(Object.class, null);
            jt.setBounds(30,40,200,150);
            JScrollPane sp = new JScrollPane(jt);
            btnRefreshB = new JButton("Refresh");
            JButton btnTambah = new JButton("Tambah Data");
            btnTambah.setBounds(350,150,100,40);
            
            JLabel label = new JLabel("Barang");
            label.setFont(new Font("Serif",Font.BOLD,30));
            
            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            add(label,gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            sp.setMinimumSize(new Dimension(450,200));
            sp.setMaximumSize(new Dimension(450,200));
            sp.setPreferredSize(new Dimension(450,200));
            add(sp,gbc);
            
            gbc.gridwidth = 0;
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(btnTambah,gbc);
            gbc.gridx = 0;
            gbc.gridy = 0;
            btnRefreshB.setVisible(false);
            
            gbc.anchor = GridBagConstraints.SOUTHEAST;
            add(btnRefreshB,gbc);
            
            btnTambah.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                    inputBarang iBarang = new inputBarang();
                    prosesSupermarket proses = new prosesSupermarket();
                } 
            });
            btnRefreshB.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   defaultTableModel.setRowCount(0);
                   btnRefreshB.setVisible(false);
                   try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/supermarket","root",""
                        );
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from barang");
                        while(rs.next()){
                            String id = rs.getString("Id_Barang");
                            String Nama = rs.getString("Nama_Barang");
                            int stok = rs.getInt("Stok");
                            int harga = rs.getInt("Harga");
                            String idSupplier = rs.getString("Id_Supplier");
                            
                            Object[] list = {id,Nama,stok,harga,idSupplier};
                            defaultTableModel.addRow(list);
                        }   
                        } catch (Exception es) {
                            System.out.println(es);
                        }
                    } 
            });
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/supermarket","root",""
                );
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from barang");
                
                defaultTableModel.addColumn("Id Barang");
                defaultTableModel.addColumn("Nama Barang");
                defaultTableModel.addColumn("Stok");
                defaultTableModel.addColumn("Harga");
                defaultTableModel.addColumn("Id Supplier");
              
                while(rs.next()){
                    String id = rs.getString("Id_Barang");
                    String Nama = rs.getString("Nama_Barang");
                    int stok = rs.getInt("Stok");
                    int harga = rs.getInt("Harga");
                    String idSupplier = rs.getString("Id_Supplier");
                    
                    Object[] list = {id,Nama,stok,harga,idSupplier};
                    defaultTableModel.addRow(list);
                }   
            } catch (Exception e) {
            System.out.println(e);
            }
            
        }
    }
    static class panelSupplier extends JPanel{
        panelSupplier(){
            setBounds(0,0,300,350);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0,5,7,5);
            
            DefaultTableModel defaultTableModel = new DefaultTableModel();
            JTable jt = new JTable(defaultTableModel);
            jt.setBounds(30,40,200,150);
            JScrollPane sp = new JScrollPane(jt);
            btnRefreshS = new JButton("Refresh");
            JButton btnTambah = new JButton("Tambah Data");
            btnTambah.setBounds(350,150,100,40);
            
            JLabel label = new JLabel("Supplier");
            label.setFont(new Font("Serif",Font.BOLD,30));
            
            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            add(label,gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            sp.setMinimumSize(new Dimension(450,200));
            sp.setMaximumSize(new Dimension(450,200));
            sp.setPreferredSize(new Dimension(450,200));
            add(sp,gbc);
            
            gbc.gridwidth = 0;
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(btnTambah,gbc);
            gbc.gridx = 0;
            gbc.gridy = 0;
            btnRefreshS.setVisible(false);
            
            gbc.anchor = GridBagConstraints.SOUTHEAST;
            add(btnRefreshS,gbc);
            
            btnTambah.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                    inputSupplier iSupplier = new inputSupplier();
                } 
            });
            btnRefreshS.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   defaultTableModel.setRowCount(0);
                   btnRefreshS.setVisible(false);
                   try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/supermarket","root",""
                        );
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from supplier");
                   
                        while(rs.next()){
                            String id = rs.getString("Id_Supplier");
                            String Nama = rs.getString("Nama_Supplier");
                            String NoTelp = rs.getString("No_Telp");
                    
                            Object[] list = {id,Nama,NoTelp};
                            defaultTableModel.addRow(list);
                        }   
                        } catch (Exception es) {
                            System.out.println(es);
                        }
                    } 
            });
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/supermarket","root",""
                );
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from supplier");
                defaultTableModel.addColumn("Id Supplier");
                defaultTableModel.addColumn("Nama Supplier");
                defaultTableModel.addColumn("No. Telepon Supplier");
                
                while(rs.next()){
                    String id = rs.getString("Id_Supplier");
                    String Nama = rs.getString("Nama_Supplier");
                    String NoTelp = rs.getString("No_Telp");
                    
                    Object[] list = {id,Nama,NoTelp};
                    defaultTableModel.addRow(list);
                }   
            } catch (Exception e) {
            System.out.println(e);
            }
            
        }
    }
    
    static class panelKaryawan extends JPanel{
        panelKaryawan(){
            setBounds(0,0,300,350);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0,5,7,5);
            
            DefaultTableModel defaultTableModel = new DefaultTableModel();
            JTable jt = new JTable(defaultTableModel);
            jt.setBounds(30,40,200,150);
            JScrollPane sp = new JScrollPane(jt);
            btnRefreshK = new JButton("Refresh");
            JButton btnTambah = new JButton("Tambah Data");
            btnTambah.setBounds(350,150,100,40);
            
            JLabel label = new JLabel("Karyawan");
            label.setFont(new Font("Serif",Font.BOLD,30));
            
            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            add(label,gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            sp.setMinimumSize(new Dimension(450,200));
            sp.setMaximumSize(new Dimension(450,200));
            sp.setPreferredSize(new Dimension(450,200));
            add(sp,gbc);
            
            gbc.gridwidth = 0;
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(btnTambah,gbc);
            gbc.gridx = 0;
            gbc.gridy = 0;
            btnRefreshK.setVisible(false);
            
            gbc.anchor = GridBagConstraints.SOUTHEAST;
            add(btnRefreshK,gbc);
            
            btnTambah.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                    inputKaryawan iKaryawan = new inputKaryawan();
                } 
            });
            btnRefreshK.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   defaultTableModel.setRowCount(0);
                   btnRefreshK.setVisible(false);
                   try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/supermarket","root",""
                        );
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from karyawan");
                   
                        while(rs.next()){
                            String id = rs.getString("Id_Karyawan");
                            String Nama = rs.getString("Nama_Karyawan");
                    
                            Object[] list = {id,Nama};
                            defaultTableModel.addRow(list);
                        }  
                        } catch (Exception es) {
                            System.out.println(es);
                        }
                    } 
            });
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/supermarket","root",""
                );
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from karyawan");
                defaultTableModel.addColumn("Id Karyawan");
                defaultTableModel.addColumn("Nama Karyawan");
                
                
                 
                while(rs.next()){
                    String id = rs.getString("Id_Karyawan");
                    String Nama = rs.getString("Nama_Karyawan");
                    
                    Object[] list = {id,Nama};
                    defaultTableModel.addRow(list);
                }   
            } catch (Exception e) {
            System.out.println(e);
            }
            
        }
    }
    public static void main(String[] args){
        JPanel pBarang = new panelBarang();
        JPanel pSupplier = new panelSupplier();
        JPanel pKaryawan = new panelKaryawan();
        JTabbedPane menu = new JTabbedPane();
        menu.add("Barang",pBarang);
        menu.add("Supplier",pSupplier);
        menu.add("Karyawan",pKaryawan);
        JFrame f = new JFrame("Supermarket Buah dan Sayur");
        f.add(menu);
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setSize(500,400);
        f.setVisible(true);
    }
}