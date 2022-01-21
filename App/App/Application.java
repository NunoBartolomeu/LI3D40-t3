package App.App;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

interface DbWorker {
	void doWork() throws SQLException;
}	

class App {

	Connection con = null;
	
	private enum Option {
		Unknown,
		Exit,
		insertAtivo,
		subsEquipa,
		deactivateAtivo,
		costAtivo,
		pessoasInt,
		ativosGI,
		responsibleG,
		programmedInt, 
		restricoes,
	}

	private static App __instance = null;
	
	private String __connectionString;
	
	private HashMap <Option,DbWorker> __dbMethods;
	
	private App() {
		__dbMethods = new HashMap<Option,DbWorker>();
		__dbMethods.put(Option.restricoes, new DbWorker() {public void doWork() throws SQLException {Model.restricoes();}});
		__dbMethods.put(Option.insertAtivo, new DbWorker() {public void doWork() throws SQLException {Model.inserirAtivo();}});
		__dbMethods.put(Option.subsEquipa, new DbWorker() {public void doWork() throws SQLException {Model.substituirEquipa();}});
		__dbMethods.put(Option.deactivateAtivo, new DbWorker() {public void doWork() throws SQLException {Model.desativarAtivo();}});
		__dbMethods.put(Option.costAtivo, new DbWorker() {public void doWork() throws SQLException {Model.custoDeUmActivo();}});
		__dbMethods.put(Option.pessoasInt, new DbWorker() {public void doWork() throws SQLException {Model.pessoasIntervencao();}});
		__dbMethods.put(Option.ativosGI, new DbWorker() {public void doWork() throws SQLException {Model.ativosGeridosIntervidos();}});
		__dbMethods.put(Option.responsibleG, new DbWorker() {public void doWork() throws SQLException {Model.responsaveisGestores();}});
		__dbMethods.put(Option.programmedInt, new DbWorker() {public void doWork() throws SQLException {Model.intervencoesProg();}});
	}

	public static App getInstance() {
		if(__instance == null) { __instance = new App(); }
		return __instance;
	}

	private Option DisplayMenu() { 
		Option option=Option.Unknown;
		try {
			System.out.println("Course management");
			System.out.println();
			System.out.println("1. Exit");
			System.out.println("2. Inserir um novo ativo");
			System.out.println("3. Substituir elemento de uma equipa");
			System.out.println("4. Colocar ativo fora de serviço");
			System.out.println("5. Custo total de um ativo");
			System.out.println("6. Listar pessoas que estão a realizar intervação no ativo");
			System.out.println("7. Listar ativos que uma pessoa geriu ou fez intervenção");
			System.out.println("8. Listar responsáveis de equipa, gestores de pelo menos um ativo");
			System.out.println("9. Listar intervenções programadas");
			System.out.print(">");
			
			Scanner s = new Scanner(System.in);
			int result = s.nextInt();
			option = Option.values()[result];			
		}

		catch(RuntimeException ex) {
			//nothing to do. 
		}

		return option;		
	}

	private final static void clearConsole() throws Exception {
	    //console is 80 columns and 25 lines
		for (int y = 0; y < 25; y++) System.out.println("\n");
	}
	
	private void Login() throws java.sql.SQLException { con = DriverManager.getConnection(getConnectionString()); }

	private void Logout() throws java.sql.SQLException { if(con != null) con.close(); }

	public void Run() throws Exception {
		Login ();
		Option userInput = Option.Unknown;
		do {
			__dbMethods.get(Option.restricoes).doWork();
			clearConsole();
			userInput = DisplayMenu();
			clearConsole();		  	
			try {		
				__dbMethods.get(userInput).doWork();		
				System.in.read();		
			}

			catch(NullPointerException ex) {
				//Nothing to do. The option was not a valid one. Read another.
			}
			
		} while(userInput!=Option.Exit);

		Logout();
	}

	public String getConnectionString() { return __connectionString; }

	public void setConnectionString(String s) { __connectionString = s; }

	/**
		To implement from this point forward. Do not need to change the code above.
	-------------------------------------------------------------------------------		
		IMPORTANT:
	--- DO NOT MOVE IN THE CODE ABOVE. JUST HAVE TO IMPLEMENT THE METHODS BELOW ---
	-------------------------------------------------------------------------------
	**/
}

public class Application {
    public static void main(String[] args) throws SQLException, Exception {
        String url = "jdbc:postgresql://10.62.73.22:5432/?user=l3d40&password=banana&ssl=false";
		App.getInstance().setConnectionString(url);
		App.getInstance().Run();
    }
}
