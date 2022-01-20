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

    public static void substituirEquipa(){
        System.out.println("substituirEquipa()");

        final String query = "update pessoa set equipa = ? where id = ?; update pessoa set equipa = ? where id = ?;";
        
        try {
            Connection conn = getCon();
            PreparedStatement pstmt = conn.prepareStatement(query);
            

            System.out.print("Equipa (codigo) a receber a substituição: ");
            java.util.Scanner key = new Scanner(System.in);
            int equipa = key.nextInt();
            pstmt.setInt(1, equipa);


            System.out.print("Id da pessoa que vai substituir: ");
            key = new Scanner(System.in);
            int id1 = key.nextInt();
            pstmt.setInt(2, id1);


            //equipa = 0;
            pstmt.setInt(3, equipa);

            
            System.out.print("Id da pessoa a ser substituida: ");
            key = new Scanner(System.in);
            int id2 = key.nextInt();
            pstmt.setInt(4, id2);

            System.out.print(pstmt);
            //2pstmt.executeUpdate();

            // obter os resultados através de um select
            pstmt.executeUpdate();
        }
        
        catch(SQLException err){
            //Nothing to do. The option was not a valid one. Read another.
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

    public static void custoDeUmActivo() {
        System.out.println("custoDeUmActivo()");
/*
        final String dtaquisicaoQuery = "select * from activo where id=?;";
        final String valorComercialQuery = "select valor from vcomercial where activo=? and dtvcomercial=?;";
        final String valorDasIntervencoesQuery = "select valcusto from intervencao where activo=? and dtvcomercial=?;";

        try {
            Connection conn = getCon();

            PreparedStatement dtaquisicaoPS = conn.prepareStatement(dtaquisicaoQuery);
            System.out.println("Id do activo: ");
            Scanner scanner = new Scanner(System.in);
            String id = scanner.nextLine();
            dtaquisicaoPS.setString(1, id);
            

            PreparedStatement valorComercialPS = conn.prepareStatement(valorComercialQuery);
            valorComercialPS.setString(1, id);
            //String dtvComercial = scanner.nextLine();
            valorComercialPS.setDate(2, java.sql.Date.valueOf(dtvComercial));
            int valorComercial = valorComercialPS.executeQuery().getInt("valor");
            
            
            PreparedStatement intervencoesPS = conn.prepareStatement(valorDasIntervencoesQuery);
            intervencoesPS.setString(1, id);
            result = intervencoesPS.executeQuery(); 
            int valorIntervencoes = 0;
            while (result.next()) {
                valorIntervencoes += result.getInt("valcusto");
            }
            int total = valorComercial + valorIntervencoes;
            System.out.println("O activo tem um valor de: " + total + "€");
        
        } catch(SQLException err)
        {
            //Nothing to do. The option was not a valid one. Read another.
        }*/
    }

    public static void pessoasIntervencao(){
        System.out.println("pessoasIntervencao()");

        final String query = "select pessoa.nome " +
        "from activo join pessoa on activo.pessoa = pessoa.id " +
        "where activo.nome =? " +
        "union " +
        "select p.nome " +
        "from activo a join intervencao i on a.id = i.activo join inter_equipa ie on i.noint = ie.integerervencao join pessoa p on p.equipa = ie.equipa " +
        "where a.nome =?;";

        try {
            Connection conn = getCon();

            PreparedStatement PSactivo = conn.prepareStatement(query);
            System.out.println("Nome do activo: ");
            Scanner scanner = new Scanner(System.in);
            String nome = scanner.nextLine();
            PSactivo.setString(1, nome);
            PSactivo.setString(2, nome);

            printResults(PSactivo.executeQuery());
        
        } catch(SQLException err)
        {
            //Nothing to do. The option was not a valid one. Read another.
        }
    }

    public static void ativosGeridosIntervidos(){
        System.out.println("ativosGeridosIntervidos()");

        final String query = "with manuel as ( " +
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

        try {
            Connection conn = getCon();

            PreparedStatement activoGI = conn.prepareStatement(query);
            System.out.println("Nome da pessoa: ");
            Scanner scanner = new Scanner(System.in);
            String nome = scanner.nextLine();
            activoGI.setString(1, nome);

            printResults(activoGI.executeQuery());
        
        } catch(SQLException err)
        {
            //Nothing to do. The option was not a valid one. Read another.
        }
    }

    public static void responsaveisGestores(){
        System.out.println("responsaveisGestores()");

        final String query = "with responsaveis as ( " +
        "select p.id, p.nome, p.profissao " +
        "from pessoa p join equipa on p.id = responsavel " +
        "), " +
        "gestores as ( " +
        "select r.id, r.nome, r.profissao " +
        "from responsaveis r join activo on activo.pessoa = r.id " +
        ")" +
        "select distinct nome, profissao, telefone " +
        "from gestores join tel_pessoa tp on gestores.id = tp.pessoa; ";


        try {
            Connection conn = getCon();

            PreparedStatement respG = conn.prepareStatement(query);
            
            printResults(respG.executeQuery());
        
        } catch(SQLException err)
        {
            //Nothing to do. The option was not a valid one. Read another.
        }
    }

    public static void intervencoesProg(){
        System.out.println("intervencoesProg()");

        final String query = "select id, nome, descricao " +
        "from activo inner join INTERVENCAO on id = activo " +
        "where (dtinicio > CURRENT_DATE and dtinicio < CURRENT_DATE + interval ? );";
        

        try {
            Connection conn = getCon();

            PreparedStatement intervencaoP = conn.prepareStatement(query);
            System.out.println("Inserir intervalo de tempo com unidades: year, month, week, day");
            System.out.println("Exemplo: '5 days', '2 months 3 days'");
            System.out.println("Intervalo de tempo: ");
            Scanner scanner = new Scanner(System.in);
            String intervalo = scanner.nextLine();
            intervencaoP.setString(1, intervalo);

            System.out.println(intervencaoP);
            
            printResults(intervencaoP.executeQuery());
        
        } catch(SQLException err)
        {
            //Nothing to do. The option was not a valid one. Read another.
        }
    }
}
