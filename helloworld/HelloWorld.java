/**
 * ISEL-DEETC
 * Introdução a Sistemas de Informacao
 * user: MPato
 * version 1.0
 * date: january 2022
 */

package helloworld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class HelloWorld {

    public static void main(String[] args) {

        String jdbcURL = "jdbc:postgresql://10.62.73.22:5432/l3d40";
        String username = "l3d40";
        String password = "banana";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            // Step 2 -  Connecting to the Database
            connection = DriverManager.getConnection(jdbcURL, username, password);

            // Step 3 - Execute statements
            statement = connection.createStatement();


            result = statement.executeQuery("select * from jdbcdemo");
            
			PreparedStatement pstmt = connection.prepareStatement("drop table if exists biggerrrrrr;");
            pstmt.executeUpdate();

            System.out.println(result);


            // Step 4 - Get result
            while (result.next()) {
                System.out.print(result.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 5 Close connection
            try {
                // free the resources of the ResultSet
                if (result != null) result.close();
                // free the resources of the Statement
                if (statement != null) statement.close();
                // close connection
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}