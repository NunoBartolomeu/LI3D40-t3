package App.App;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Restictions {
    
    public static Connection getCon() throws SQLException {
        return DriverManager.getConnection(App.getInstance().getConnectionString());
    }

    public static void runAll() throws SQLException{
        equipasTem2ElmMin();
    }

    public static void equipasTem2ElmMin() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_equipasOrdenadas = "select * from equipa order by codigo;";
        final String query_pessoasOrdenadas = "select * from pessoa order by equipa;";
        final String query_deleteTeam = "delete from equipa where codigo = ?";
        final String query_teamToNull = "update pessoa set equipa = null where equipa = ?";

        //Prepared Statements
        Statement s_equipas = conn.createStatement();
        Statement s_pessoas = conn.createStatement();
        
        //ResultSet
        ResultSet rs = null;
        ResultSet rs2 = null;
    
        try { 
            //Get people           
            rs = s_equipas.executeQuery(query_equipasOrdenadas);
            rs2 = s_pessoas.executeQuery(query_pessoasOrdenadas);
            rs2.next();

            while(rs.next()) {
                System.out.println(rs.getInt("codigo"));
                int count=0;

                while(rs2.getInt("equipa") == rs.getInt("codigo")) {
                    count++;
                    if(!rs2.next()) break;
                }
                if (count < 2) {
                    //Apagar equipa
                    PreparedStatement ps_apagarEquipa = conn.prepareStatement(query_deleteTeam);
                    ps_apagarEquipa.setInt(1, rs.getInt("codigo"));
                    ps_apagarEquipa.executeUpdate();
                
                    if (count != 0) { 
                        PreparedStatement ps_teamToNull = conn.prepareStatement(query_teamToNull);
                        ps_teamToNull.setInt(1, rs.getInt("codigo"));
                        ps_teamToNull.executeUpdate(); 
                    }
                }
            }

        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close connection
            if (conn != null) conn.close();
        }
    }

    public void checkDatas() throws SQLException{
        //Connection
        Connection conn = getCon();

        //Querys
        final String query_activos = "select * from activo;";
        final String query_vcomercial = "select * from vcomercial where activo=?;";
        final String query_deleteVcomercial = "delete from vcomercial where activo=? and dtvcomercial=?;";

        //Prepared Statements
        Statement s_activos = conn.createStatement();
        
        //ResultSet
        ResultSet rs = null;

        try { 
            //Get ativos           
            rs = s_activos.executeQuery(query_activos);

            while(rs.next()) {
                String id = rs.getString("id");
                Date dtaquisicao = rs.getDate("dtaquisicao");

                PreparedStatement ps_vcomercial = conn.prepareStatement(query_vcomercial);
                ps_vcomercial.setString(1, id);
                ResultSet rs2 = ps_vcomercial.executeQuery();

                while(rs2.next()) {
                    if (dtaquisicao.compareTo(rs2.getDate("dtvcomercial")) > 0) {
                        PreparedStatement ps_deleteVcomercial = conn.prepareStatement(query_deleteVcomercial);
                        ps_deleteVcomercial.setString(1, id);
                        ps_deleteVcomercial.setDate(2, rs2.getDate("dtvcomercial"));
                        ps_deleteVcomercial.executeUpdate();
                    }
                }
            }
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close connection
            if (conn != null) conn.close();
        }
    }

    public void checkValcusto() {

    }

    public void checkDtfim() throws SQLException{
        //Connection
        Connection conn = getCon();

        //Querys
        final String query_intervencao = "select * from intervencao;";
        final String query_mudarEstado = "update intervencao set estado = 'concluido' where noint=?;";
        
        //Prepared Statements
        Statement s_intervencao = conn.createStatement();
        
        //ResultSet
        ResultSet rs = null;

        try { 
            //Get ativos           
            rs = s_intervencao.executeQuery(query_intervencao);

            while(rs.next()) {
                if (rs.getDate("dtfim") != null) {
                    if (!rs.getString("estado").equals("concluido")) {                       
                        PreparedStatement ps_mudarEstado = conn.prepareStatement(query_mudarEstado);
                        ps_mudarEstado.setString(1, rs.getString("noint"));
                        ps_mudarEstado.executeUpdate();
                    }
                }
            }
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close connection
            if (conn != null) conn.close();
        }
    }

    public void activosPaiFilho(){

    }

    public void checkGerirNaoIntervir(){

    }
}
