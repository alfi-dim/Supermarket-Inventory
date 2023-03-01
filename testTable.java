import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
class testTable{
    
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testKoneksi","root",""
            );
            Statement stmt = con.createStatement();
            JFrame f;
            f = new JFrame();
            ResultSet rs = stmt.executeQuery("select * from testKoneksi");
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
                String Nama = rs.getString("nama");
                
                Object[] list = {id,Nama};
                defaultTableModel.addRow(list);
            }   
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}