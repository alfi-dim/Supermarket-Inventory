import java.sql.*;  

public class prosesSupermarket{
    Connection con;
    Statement st;
    ResultSet rs;
    ResultSetMetaData rsmd;
    String query;
    boolean s = false;
    public boolean insertSupplier(String id, String nama, String noTelp){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/supermarket","root",""
            );
            st = con.createStatement();
            query = "INSERT INTO Supplier VALUES ('"+id+"','"+nama+"','"+noTelp+"');";
            st.executeUpdate(query);
            con.close();
            return true;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean insertKaryawan(String id, String nama){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/supermarket","root",""
            );
            st = con.createStatement();
            query = "INSERT INTO Karyawan VALUES ('"+id+"', '"+nama+"')";
            st.executeUpdate(query);
            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean insertBarang(String id, String nama, int stok, int harga, String getSupplier) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/supermarket","root",""
            );
            st = con.createStatement();
            String idSupplier = "";
            query = "SELECT id_Supplier FROM Supplier WHERE Nama_Supplier LIKE '"+getSupplier+"';";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                idSupplier = rs.getString("id_Supplier");
            }
            
            query = "INSERT INTO Barang VALUES ('"+id+"','"+nama+"',"+stok+","+harga+",'"+idSupplier+"');";
            st.executeUpdate(query);
            con.close();
            s = true;
            return true;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;
        }
    }
}
