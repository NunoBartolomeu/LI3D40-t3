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
        System.out.println("Restrição: Equipas Tem De Ter No Minimo 2 Elementos");
        checkDatas();
        System.out.println("Restrição: Um Activo Nao Pode Ter Um Valor Comercial Antes De Ser Adquirido");
        checkValcusto();
        System.out.println("Restrição: Custos De Intervenção Nao Podem Ser Superiores Ao Custo Comercial De Um Activo Ativo");
        checkDtfim();
        System.out.println("Restriction: Active Interventions Can't Have a End Date");
        activosPaiFilho();
        System.out.println("Restriction: Asset Parenting Types Must Be Respected");
        checkGerirNaoIntervir();
        System.out.println("Restriction: The Asset Manager Can't Be Part of the Team That Makes an Intervention on the Asset");
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

            conn.setAutoCommit(false);
            boolean last = false;

            while(rs.next()) {
                if(last) break;

                int count=0;

                while(rs2.getInt("equipa") == rs.getInt("codigo")) {
                    count++;
                    if(!rs2.next()){
                        last = true;
                        break;
                    } 
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
            conn.commit();
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            conn.setAutoCommit(true);

            //Close statemenes
            if(s_equipas != null) s_equipas.close();
            if(s_pessoas != null) s_pessoas.close();

            //Close Result Set
            if (rs != null) rs.close();
            if (rs2 != null) rs.close();

            //Close connection
            if (conn != null) conn.close();
        }
    }

    public static void checkDatas() throws SQLException{
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

    public static void checkValcusto() throws SQLException{
        //Connection
        Connection conn = getCon();

        //Querys
        final String query_activos = "select * from activo;";
        final String query_dtaquisicao = "select dtaquisicao from activo where id=?;";
        final String query_valComer = "select valor from vcomercial where activo=? and dtvcomercial=?;";
        final String query_valorInter = "select valcusto from intervencao where activo=?;";
        final String query_updtState = "update activo set estado = b'0' where id=?;";
        final String query_updtDtfim = "update intervencao set estado = 'concluido' where activo = ?;" + "update intervencao set dtfim = CURRENT_DATE where activo = ?;";  
        
        //ResultSet
        ResultSet rs = null;
            
        try {

            //Prepared Statements
            PreparedStatement ps_ativos = conn.prepareStatement(query_activos);
            PreparedStatement ps_dtaquisicao = conn.prepareStatement(query_dtaquisicao);
            PreparedStatement ps_valComer = conn.prepareStatement(query_valComer);
            PreparedStatement ps_valInter = conn.prepareStatement(query_valorInter); 
            PreparedStatement ps_updtState = conn.prepareStatement(query_updtState); 
            PreparedStatement ps_updtDtfim = conn.prepareStatement(query_updtDtfim); 

            rs = ps_ativos.executeQuery();

            while(rs.next()) {
                String id = rs.getString("id");
                
                ps_dtaquisicao.setString(1, id);
                ResultSet dtaqui = ps_dtaquisicao.executeQuery();
                if(!dtaqui.next()) continue;
                java.sql.Date sqlDate = dtaqui.getDate("dtaquisicao");

                ps_valComer.setString(1, id);
                ps_valComer.setDate(2, sqlDate);
                ResultSet valorC = ps_valComer.executeQuery();
                if(!valorC.next()) continue;
                int valorComercial = valorC.getInt("valor");

                ps_valInter.setString(1, id);
                ResultSet valorI = ps_valInter.executeQuery();
                if(!valorI.next()) continue;
                int valIntervencao = valorI.getInt("valcusto");

                if(valIntervencao < valorComercial){
                    ps_updtState.setString(1, id);
                    ps_updtState.executeUpdate();
                    
                    ps_updtDtfim.setString(1, id);
                    ps_updtDtfim.setString(2, id);
                    ps_updtDtfim.executeUpdate();
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

    public static void checkDtfim() throws SQLException{
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
            //Get intervencoes         
            rs = s_intervencao.executeQuery(query_intervencao);

            while(rs.next()) {
                if (rs.getDate("dtfim") != null) {
                    if (!rs.getString("estado").equals("concluido")) {                       
                        PreparedStatement ps_mudarEstado = conn.prepareStatement(query_mudarEstado);
                        ps_mudarEstado.setInt(1, rs.getInt("noint"));
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

    public static void activosPaiFilho() throws SQLException{
        //Connection
        Connection conn = getCon();

        //Querys
        final String query_activos = "select * from activo;"; //1º pegar todos os ativos
        final String query_activoPai = "select * from activo where id=?;";
        final String query_updtA = "update activo set tipo=? where id=?";

        //ResultSet
        ResultSet rs = null;

        try {
            //Prepared Statements
            PreparedStatement ps_ativos = conn.prepareStatement(query_activos);
            PreparedStatement ps_ativoPai = conn.prepareStatement(query_activoPai);
            PreparedStatement ps_updtA = conn.prepareStatement(query_updtA);

            rs = ps_ativos.executeQuery();

            while(rs.next()) {

                String id = rs.getString("id");
                String idativoTopo = rs.getString("idactivotopo");

                if (id != idativoTopo){

                    ps_ativoPai.setString(1, idativoTopo);
                    ResultSet ativoP = ps_ativoPai.executeQuery();
                    if(!ativoP.next()) continue;
                    int tipoPai = ativoP.getInt("tipo");

                    if(tipoPai != rs.getInt("tipo")){
                        ps_updtA.setInt(1, ativoP.getInt("tipo"));
                        ps_updtA.setString(2, id);
                        ps_updtA.executeUpdate();
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

    public static void checkGerirNaoIntervir() throws SQLException{        
        //Connection
        Connection conn = getCon();

        //Querys
        final String query_activos = "select * from activo;";
        final String query_pessoa = "select * from pessoa where id=?;";
        final String query_integerevencao = "select * from inter_equipa where equipa=?;";
        final String query_interActivo = "select * from intervencao where noint=?;";
        final String query_deleteIntervencao = "delete from intervencao where noint=?;";

        //Prepared Statements
        Statement s_activos = conn.createStatement();

        //ResultSet
        ResultSet rs = null;

        try { 
            //Get ativos           
            rs = s_activos.executeQuery(query_activos);

            while(rs.next()) {
                //Get id and manager
                String activo = rs.getString("id");
                int pessoa = rs.getInt("pessoa");

                //Get team
                PreparedStatement ps_pessoa = conn.prepareStatement(query_pessoa);
                ps_pessoa.setInt(1, pessoa);
                ResultSet rs2 = ps_pessoa.executeQuery();
                if(!rs2.next()) continue;
                int equipa = rs2.getInt("equipa");

                //Get all inerventions
                PreparedStatement ps_integerevencao = conn.prepareStatement(query_integerevencao);
                ps_integerevencao.setInt(1, equipa);
                ResultSet rs3 = ps_integerevencao.executeQuery();
                
                while (rs3.next()){
                    //Get noint
                    int noint = rs3.getInt("integerervencao");
                    
                    PreparedStatement ps_interActivo = conn.prepareStatement(query_interActivo);
                    ps_interActivo.setInt(1, noint);
                    ResultSet rs4 = ps_interActivo.executeQuery();
                    if(rs4.next()){
                        if (activo.equals(rs4.getString("activo"))){
                            PreparedStatement ps_deleteIntervencao = conn.prepareStatement(query_deleteIntervencao);
                            ps_deleteIntervencao.setInt(1, noint);
                            ps_deleteIntervencao.executeUpdate();
                    }
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
}
