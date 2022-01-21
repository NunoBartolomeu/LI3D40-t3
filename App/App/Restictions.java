package App.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Classes.Pessoa;

public class Restictions {
    
    public static Connection getCon() throws SQLException {
        return DriverManager.getConnection(App.getInstance().getConnectionString());
    }

    public static void main(String[] args){
        //Connection
        Connection conn = getCon();
                
        //Querys
        final String query_equipasOrdenadas = "select * from equipa order by codigo;";
        final String query_pessoasOrdenadas = "select * from pessoa order by equipa;";
        
        //Prepared Statements
        Statement s_equipas = conn.createStatement();
        Statement s_pessoas = conn.createStatement();
        
        //ResultSet
        ResultSet rs = null;
        ResultSet rs2 = null;
    
        try { 
            //Get people           
            rs = s_equipas.executeQuery(query_equipasOrdenadas);
            s_pessoas.executeQuery(query_pessoasOrdenadas);

            //Key team, value num of team members
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            while (rs.next()){ map.put(rs.getInt("codigo"), 0); }

            while (rs2.next()){ 
                map.put( rs2.getInt("equipa"), map.get(rs2.getInt("equipa"))+1 );
            }
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_dtaquisicao != null) ps_dtaquisicao.close();

            //Close connection
            if (conn != null) conn.close();
        }
    }

    public void equipasTem2ElmMin(){

    }

    public void dtvcomercialMenorQueDtaquisicao(){

    }

    public void checkValcusto(){

    }

    public void checkDtfim(){

    }

    public void activosPaiFilho(){

    }

    public void checkGerirNaoIntervir(){

    }
}
