package Modelo;

import java.sql.*;
import java.util.Scanner;

public class Querys {
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

    private void inserirActivo() {
        final String query = "insert into Activo  (id, nome, estado, dtaquisicao, modelo, marca, localizacao, idactivotopo, tipo, empresa, pessoa)" + 
        "values (?, ?, b?, ?, ?, ?, ?, ?, ?, ?, ?),;";
        try {
            Connection conn = getCon();
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            System.out.print("Inserir id do activo: ");
            java.util.Scanner key = new Scanner(System.in);
            String id = key.nextLine();
            pstmt.setString(1, id);
            

            System.out.print("Inserir nome do activo: ");
            String nome = key.nextLine();
            pstmt.setString(2, nome);

            
            System.out.print("Inserir estado do activo: ");
            String estado = key.nextLine();
            pstmt.setInt(3, estado.charAt(0)-'0');

            /*
            System.out.print("Inserir dtaquisicao do activo: ");
            String dtaquisicao = key.nextLine();
            pstmt.setDate(4, date(dtaquisicao));
*/
            
            System.out.print("Inserir modelo do activo: ");
            String modelo = key.nextLine();
            pstmt.setString(5, modelo);

            
            System.out.print("Inserir marca do activo: ");
            String marca = key.nextLine();
            pstmt.setString(6, marca);


            System.out.print("Inserir localizacao do activo: ");
            String localizacao = key.nextLine();
            pstmt.setString(7, localizacao);

            
            System.out.print("Inserir idactivotopo do activo: ");
            String idactivotopo = key.nextLine();
            pstmt.setString(8, idactivotopo);


            System.out.print("Inserir tipo do activo: ");
            String tipo = key.nextLine();
            pstmt.setString(9, tipo);


            System.out.print("Inserir empresa do activo: ");
            String empresa = key.nextLine();
            pstmt.setString(10, empresa);


            System.out.print("Inserir pessoa do activo: ");
            String pessoa = key.nextLine();
            pstmt.setString(11, pessoa);

            //2pstmt.executeUpdate();

            // obter os resultados através de um select
            pstmt.executeUpdate();
        }
        
        catch(SQLException err){
            //Nothing to do. The option was not a valid one. Read another.
        }
    }

    private void substituirElmDeEquipa() {
        final String query = "update pessoa set equipa = ? where id = ?; update pessoa set equipa = ? where id = ?;";
        try {
            Connection conn = getCon();
            PreparedStatement pstmt = conn.prepareStatement(query);
            

            System.out.print("Equipa (codigo) a receber a substituição: ");
            java.util.Scanner key = new Scanner(System.in);
            int equipa = key.nextInt();
            key.nextLine();
            pstmt.setInt(1, equipa);


            System.out.print("Id da pessoa que vai substituir: ");
            key = new Scanner(System.in);
            String id1 = key.nextLine();
            pstmt.setString(2, id1);


            equipa = 0;
            pstmt.setInt(3, equipa);

            
            System.out.print("Id da pessoa a ser substituida: ");
            key = new Scanner(System.in);
            String id2 = key.nextLine();
            pstmt.setString(4, id2);


            //2pstmt.executeUpdate();

            // obter os resultados através de um select
            pstmt.executeUpdate();
        }
        
        catch(SQLException err){
            //Nothing to do. The option was not a valid one. Read another.
        }
    }

    private void desactivarActivo() {
        final String query = "update activo set estado = 0 where id=?;";
        
        try {
            Connection conn = getCon();
            PreparedStatement pstmt = conn.prepareStatement(query);
            System.out.println("Id do activo a desativar: ");
            java.util.Scanner key = new Scanner(System.in);
            String id = key.nextLine();
            pstmt.setString(1, id); //
            //2pstmt.executeUpdate();

            // obter os resultados através de um select
            pstmt.executeUpdate();
        }
        
        catch(SQLException err){
            //Nothing to do. The option was not a valid one. Read another.
        }
    }

    private void custoDeUmActivo() {
        final String dtaquisicaoQuery = "select dtaquisicao from activo where activo=?;";
        final String valorComercialQuery = "select valor from vcomercial where activo=? and dtvcomercial=?;";
        final String valorDasIntervencoesQuery = "select valcusto from intervencao where activo=? and dtvcomercial=?;";
        
        try {
            Connection conn = getCon();
            PreparedStatement dtaquisicaoPS = conn.prepareStatement(dtaquisicaoQuery);
            
            System.out.println("Id do activo: ");
            java.util.Scanner scanner = new Scanner(System.in);
            String id = scanner.nextLine();
            dtaquisicaoPS.setString(1, id);
            Date dtvComercial = dtaquisicaoPS.executeQuery().getDate("dtvcomercial");

            PreparedStatement valorComercialPS = conn.prepareStatement(valorComercialQuery);
            valorComercialPS.setString(1, id);
            valorComercialPS.setDate(2, dtvComercial);
            int valorComercial = valorComercialPS.executeQuery().getInt("valor");
            
            
            PreparedStatement intervencoesPS = conn.prepareStatement(valorDasIntervencoesQuery);
            intervencoesPS.setString(1, id);
            ResultSet result = intervencoesPS.executeQuery(); 
            int valorIntervencoes = 0;
            while (result.next()) {
                valorIntervencoes += result.getInt("valcusto");
            }
            int total = valorComercial + valorIntervencoes;
            System.out.println("O activo tem um valor de: " + total + "€");
        }
        
        catch(SQLException err){
            //Nothing to do. The option was not a valid one. Read another.
        }
    }
}
