package App.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class Model {
    
    public static void printResults(ResultSet rs) throws SQLException {
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

    public static Connection getCon() throws SQLException {
        return DriverManager.getConnection(App.getInstance().getConnectionString());
    }

    public static void defaulter() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_X = "the query string";
        
        //Prepared Statements
        PreparedStatement ps_X = conn.prepareStatement(query_X);
        
        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */
            
            System.out.print("Inserir X: ");
            String x = in.nextLine();
            ps_X.setString(1, x);

            //Execute Querys
            ps_X.executeUpdate();

            //Show Results
            printResults(ps_X.executeQuery());
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_X != null) ps_X.close();

            //Close connection
            if (conn != null) conn.close();
       }
    }

    public static void inserirAtivo() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_NovoActivo = "insert into ACTIVO (id, nome, estado, dtaquisicao, modelo, marca, localizacao, idactivotopo, tipo, empresa, pessoa)" + 
                                        "values (?, ?, b?, ?, ?, ?, ?, ?, ?, ?, ?);";
        final String result = "select id, nome from ACTIVO;";
        
        //Prepared Statements
        PreparedStatement ps_NovoActivo = conn.prepareStatement(query_NovoActivo);
        PreparedStatement pstmt2 = conn.prepareStatement(result);
        
        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */
            
            System.out.print("Inserir id do activo: ");
            String id = in.nextLine();
            ps_NovoActivo.setString(1, id);
            
            System.out.print("Inserir nome do activo: ");
            String nome = in.nextLine();
            ps_NovoActivo.setString(2, nome);

            System.out.print("Inserir estado do activo: ");
            String estado = in.nextLine();
            System.out.print(estado.charAt(0)-'0');
            ps_NovoActivo.setInt(3, estado.charAt(0)-'0');

            System.out.print("Inserir dtaquisicao do activo: ");
            String dtaquisicao = in.nextLine();
            ps_NovoActivo.setDate(4, java.sql.Date.valueOf(dtaquisicao));
            
            System.out.print("Inserir modelo do activo: ");
            String modelo = in.nextLine();
            ps_NovoActivo.setString(5, modelo);
            
            System.out.print("Inserir marca do activo: ");
            String marca = in.nextLine();
            ps_NovoActivo.setString(6, marca);

            System.out.print("Inserir localizacao do activo: ");
            String localizacao = in.nextLine();
            ps_NovoActivo.setString(7, localizacao);
            
            System.out.print("Inserir idactivotopo do activo: ");
            String idactivotopo = in.nextLine();
            ps_NovoActivo.setString(8, idactivotopo);

            System.out.print("Inserir tipo do activo: ");
            String tipo = in.nextLine();
            ps_NovoActivo.setString(9, tipo);

            System.out.print("Inserir empresa do activo: ");
            String empresa = in.nextLine();
            ps_NovoActivo.setString(10, empresa);

            System.out.print("Inserir pessoa do activo: ");
            String pessoa = in.nextLine();
            ps_NovoActivo.setString(11, pessoa);

            
            //Execute Querys
            ps_NovoActivo.executeUpdate();

            //Show Results
            printResults(pstmt2.executeQuery());
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_NovoActivo != null) ps_NovoActivo.close();

            //Close connection
            if (conn != null) conn.close();
       }
    }


    public static void substituirEquipa() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_updateEquipa = "update pessoa set equipa = ? where id = ?; update pessoa set equipa = ? where id = ?;";
        
        //Prepared Statements
        PreparedStatement ps_updateSubstituir = conn.prepareStatement(query_updateEquipa);
        PreparedStatement ps_updateSubstituta = conn.prepareStatement(query_updateEquipa);
        

        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */

            System.out.print("Equipa (codigo) a receber a substituição: ");
            int equipa = in.nextInt();
            ps_updateSubstituir.setInt(1, equipa);

            System.out.print("Id da pessoa que vai substituir: ");
            int id1 = in.nextInt();
            ps_updateSubstituir.setInt(2, id1);

            //equipa = 0;
            ps_updateSubstituta.setInt(1, equipa);

            System.out.print("Id da pessoa a ser substituida: ");
            int id2 = in.nextInt();
            ps_updateSubstituta.setInt(2, id2);

            //Execute Querys
            ps_updateSubstituta.executeUpdate();

            //Show Results
            printResults(ps_updateSubstituta.executeQuery());
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_updateSubstituta != null) ps_updateSubstituta.close();

            //Close connection
            if (conn != null) conn.close();
       }
    }

    public static void desativarAtivo() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String q_EstadoPara0 = "update activo set estado = b'0' where id=?;";

        //Prepared Statements
        PreparedStatement ps_EstadoPara0 = conn.prepareStatement(q_EstadoPara0);
        PreparedStatement ps_teste1 = conn.prepareStatement(q_EstadoPara0);
        
        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */
            
            System.out.println(ps_teste1);
            ps_teste1.setString(1, "d6548");
            System.out.println(ps_teste1);

            ps_teste1.executeUpdate();
            
            System.out.print("Id do activo a desativar: ");
            String id = in.nextLine();
            ps_EstadoPara0.setString(1, id);

            //Execute Querys
            ps_EstadoPara0.executeUpdate();

            //Show Results
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_EstadoPara0 != null) ps_EstadoPara0.close();
 
            //Close connection
            if (conn != null) conn.close();
       }
    }

    public static void custoDeUmActivo() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_dtaquisicao = "select * from activo where id=?;";
        final String query_valComer = "select valor from vcomercial where activo=? and dtvcomercial=?;";
        final String query_valorInter = "select valcusto from intervencao where activo=? and dtvcomercial=?;";

        //Prepared Statements
        PreparedStatement ps_dtaquisicao = conn.prepareStatement(query_dtaquisicao);
        PreparedStatement ps_valComer = conn.prepareStatement(query_valComer);
        PreparedStatement ps_valInter = conn.prepareStatement(query_valorInter);
        
        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */
            
            System.out.println("Id do activo: ");
            String id = in.nextLine();
            ps_dtaquisicao.setString(1, id);
            

            ps_valComer.setString(1, id);
            String dtvComercial = in.nextLine();
            ps_valComer.setDate(2, java.sql.Date.valueOf(dtvComercial));
            int valorComercial = ps_valComer.executeQuery().getInt("valor");
            
            
            ps_valInter.setString(1, id);
            rs = ps_valInter.executeQuery(); 
            int valorIntervencoes = 0;
            while (rs.next()) {
                valorIntervencoes += rs.getInt("valcusto");
            }
            int total = valorComercial + valorIntervencoes;
            System.out.println("O activo tem um valor de: " + total + "€");
            
            //Execute Querys
            ps_dtaquisicao.executeUpdate();

            //Show Results
            printResults(ps_dtaquisicao.executeQuery());
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

    public static void pessoasIntervencao() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_pessInter = "select pessoa.nome " +
                                        "from activo join pessoa on activo.pessoa = pessoa.id " +
                                        "where activo.nome =? " +
                                        "union " +
                                        "select p.nome " +
                                        "from activo a join intervencao i on a.id = i.activo join inter_equipa ie on i.noint = ie.integerervencao join pessoa p on p.equipa = ie.equipa " +
                                        "where a.nome =?;";
        
        //Prepared Statements
        PreparedStatement ps_pessInter = conn.prepareStatement(query_pessInter);
        
        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */
            
            System.out.println("Nome do activo: ");
            String nome = in.nextLine();
            ps_pessInter.setString(1, nome);
            ps_pessInter.setString(2, nome);

            //Execute Querys
            ps_pessInter.executeUpdate();

            //Show Results
            printResults(ps_pessInter.executeQuery());
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_pessInter != null) ps_pessInter.close();

            //Close connection
            if (conn != null) conn.close();
       }
    }

    public static void ativosGeridosIntervidos() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_ativosGI = "with manuel as ( " +
                                "select id, equipa " +
                                    "from pessoa p " +
                                    "where nome =? " +
                                "), " +
                               "geriu as ( " +
                                "select nome " +
                                    "from activo a join manuel m on a.pessoa = m.id " +
                                ")"	+
                                "select nome " +
                                "from inter_equipa natural join manuel join intervencao on integerervencao = noint join activo on activo.id = activo "+
                                "union " +
                                "select nome " +
                                "from geriu;";
        
        //Prepared Statements
        PreparedStatement ps_ativosGI = conn.prepareStatement(query_ativosGI);
        
        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */
            
            System.out.println("Nome da pessoa: ");
            String nome = in.nextLine();
            ps_ativosGI.setString(1, nome);

            //Execute Querys
            ps_ativosGI.executeUpdate();

            //Show Results
            printResults(ps_ativosGI.executeQuery());
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_ativosGI != null) ps_ativosGI.close();

            //Close connection
            if (conn != null) conn.close();
       }
    }

    public static void responsaveisGestores() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_RespGest = "with responsaveis as ( " +
                                "select p.id, p.nome, p.profissao " +
                                "from pessoa p join equipa on p.id = responsavel " +
                                "), " +
                                "gestores as ( " +
                                "select r.id, r.nome, r.profissao " +
                                "from responsaveis r join activo on activo.pessoa = r.id " +
                                ")" +
                                "select distinct nome, profissao, telefone " +
                                "from gestores join tel_pessoa tp on gestores.id = tp.pessoa; ";
        
        //Prepared Statements
        PreparedStatement ps_RespGest = conn.prepareStatement(query_RespGest);
        
        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */

            //Execute Querys
            ps_RespGest.executeUpdate();

            //Show Results
            printResults(ps_RespGest.executeQuery());
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_RespGest != null) ps_RespGest.close();

            //Close connection
            if (conn != null) conn.close();
       }
    }

    public static void intervencoesProg() throws SQLException{
        //Connection
        Connection conn = getCon();
        
        //Querys
        final String query_InterProg = "select id, nome, descricao " +
        "from activo inner join INTERVENCAO on id = activo " +
        "where (dtinicio > CURRENT_DATE and dtinicio < CURRENT_DATE + interval ? );";
        
        //Prepared Statements
        PreparedStatement ps_InterProg = conn.prepareStatement(query_InterProg);
        
        //ResultSet
        ResultSet rs = null;

        //Scanner
        Scanner in = new Scanner(System.in);            
            
        try {
            /*
                User Input
            1 - Ask Question
            2 - Register Answer
            3 - Add to Prepared Statement
            */
            
            System.out.println("Inserir intervalo de tempo com unidades: year, month, week, day");
            System.out.println("Exemplo: '5 days', '2 months 3 days'");
            System.out.println("Intervalo de tempo: ");
            String intervalo = in.nextLine();
            ps_InterProg.setString(1, intervalo);

            //Execute Querys
            ps_InterProg.executeUpdate();

            //Show Results
            printResults(ps_InterProg.executeQuery());
        }

        catch(SQLException err){
            System.out.println("Error detected: ");
            System.out.println(err);
        }

        finally{
            //Close Result Set
            if (rs != null) rs.close();

            //Close all Prepared Statements
            if (ps_InterProg != null) ps_InterProg.close();

            //Close connection
            if (conn != null) conn.close();
       }
    }
}