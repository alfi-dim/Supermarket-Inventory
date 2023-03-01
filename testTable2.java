import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.Color;
public class testTable2 extends testFrame{
    public void readTable1(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/supermarket","root",""
            );
            Statement stmt = con.createStatement();
            JFrame f;
            f = new JFrame();
            ResultSet rs = stmt.executeQuery("select * from barang");
            ResultSetMetaData rsmd = rs.getMetaData();
            int jumColumn = rsmd.getColumnCount();
            int jumRow = 2;
            List<String> columnName = new ArrayList<String>();
            for (int i = 1; i <= jumColumn; i++){
                columnName.add(rsmd.getColumnLabel(i));
            }
            
            
            DefaultTableModel defaultTableModel = new DefaultTableModel();
            defaultTableModel.fireTableDataChanged();
            JTable jt = new JTable(defaultTableModel);
            jt.revalidate();
            JScrollPane sp = new JScrollPane(jt);
            JPanel p = new JPanel();
            p.setBounds(0,0,30,50);
            p.setBackground(Color.red);
            p.add(sp);
            f.add(p);
            JButton btn = new JButton("KLIK");
            btn.setBounds(100,100,100,50);
         
            f.setSize(400,400);
            f.setVisible(true);
            for (int i = 1; i <= jumColumn; i++){
            defaultTableModel.addColumn(columnName.get(i-1));
            }
            while(rs.next()){
                String id = rs.getString("Id_Barang");
                String Nama = rs.getString("Nama_Barang");
                
                Object[] list = {id,Nama};
                defaultTableModel.addRow(list);
            }   
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void readTable2(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testKoneksi","root",""
            );
            Statement stmt = con.createStatement();
            JFrame f;
            f = new JFrame();
            ResultSet rs = stmt.executeQuery("select * from testKoneksi2");
            ResultSetMetaData rsmd = rs.getMetaData();
            int jumColumn = rsmd.getColumnCount();
            int jumRow = 2;
            List<String> columnName = new ArrayList<String>();
            for (int i = 1; i <= jumColumn; i++){
                columnName.add(rsmd.getColumnLabel(i));
            }
            
            
            DefaultTableModel defaultTableModel = new DefaultTableModel();
            JTable jt = new JTable(defaultTableModel);
            jt.setBounds(30,40,200,300);
            JScrollPane sp = new JScrollPane(jt);
            f.add(sp);
            f.setSize(300,400);
            f.setVisible(true);
            for (int i = 1; i <= jumColumn; i++){
            defaultTableModel.addColumn(columnName.get(i-1));
            }
            while(rs.next()){
                int id = rs.getInt("id");
                String Nama = rs.getString("alamat");
                
                Object[] list = {id,Nama};
                defaultTableModel.addRow(list);
            }   
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}