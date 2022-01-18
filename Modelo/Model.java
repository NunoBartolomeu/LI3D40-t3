/*package Modelo;

import java.sql.*;
import java.util.Scanner;

public class Model {
            
    private void printResults(ResultSet rs) throws SQLException {
        final int TAB_SIZE = 8;
        ResultSetMetaData meta = rs.getMetaData();
        int columnsCount = meta.getColumnCount();
        StringBuffer sep = new StringBuffer("\n");

        // header
        for (int i = 1; i <= columnsCount; i++) {
            System.out.print(meta.getColumnLabel(i));
            System.out.print("\t");
            for (int j = 0; j < meta.getColumnDisplaySize(i)+TAB_SIZE; j++) {
                sep.append('-');
            }
        }
        System.out.println(sep);
        // Step 4 - Get result
        while (rs.next()) {
            // results print
            for (int i = 1; i <= columnsCount; i++) {
                System.out.print(rs.getObject(i));
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private Connection getCon() throws SQLException {
        return DriverManager.getConnection(App.getInstance().getConnectionString());
    }

    private void ListEmployee() {
        //System.out.println("ListEmployee()");
        final String query = "select fname, lname from employee where salary > ?;";
        try {
            Connection conn = getCon();
            PreparedStatement pstmt = conn.prepareStatement(query);
            System.out.println("Insert salary");
            java.util.Scanner key = new Scanner(System.in);
            double salary = key.nextDouble();
            pstmt.setDouble(1,salary); //
            //2pstmt.executeUpdate();

            // obter os resultados através de um select
            printResults(pstmt.executeQuery());
        }
        
        catch(SQLException err){
            //Nothing to do. The option was not a valid one. Read another.
        }
    }
    private void ListAverageSalary(){
        //TODO: Implement
        System.out.println("ListAverageSalary()");
    }
    private void GetMaxSalary()
    {
        //TODO: Implement
        System.out.println("GetMaxSalary()");
    }
    private void RegisterDependent()
    {
        //TODO: Implement
        System.out.println("RegisterDependent()");
    }
}
*/