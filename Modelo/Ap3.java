/** 
ISEL - DEETC
Introdução a Sistemas de Informação
MP,ND, 2014-2022
*/

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
import Model;
interface DbWorker 
{
	void doWork();
}	
class App
{
	private enum Option
	{
		Unknown,
		Exit,
		ListEmployee,
		ListAverageSalary,
		GetMaxSalary,
		RegisterDependent
	}
	private static App __instance = null;
	private String __connectionString;
	private HashMap<Option,DbWorker> __dbMethods;
	private static final String SELECT_CMD = "select name,dateBirth,sex from dbo.Student";
	
	private App()
	{
		__dbMethods = new HashMap<Option,DbWorker>();
		__dbMethods.put(Option.ListEmployee, new DbWorker() {public void doWork() {Model.ListEmployee();}});
		__dbMethods.put(Option.ListAverageSalary, new DbWorker() {public void doWork() {App.this.ListAverageSalary();}});
		__dbMethods.put(Option.GetMaxSalary, new DbWorker() {public void doWork() {App.this.GetMaxSalary();}});
		__dbMethods.put(Option.RegisterDependent, new DbWorker() {public void doWork() {App.this.RegisterDependent();}});

	}
	public static App getInstance() 
	{
		if(__instance == null) 
		{
			__instance = new App();
		}
		return __instance;
	}

	private Option DisplayMenu()
	{ 
		Option option=Option.Unknown;
		try
		{
			System.out.println("Course management");
			System.out.println();
			System.out.println("1. Exit");
			System.out.println("2. List employee");
			System.out.println("3. List average salary by number department");
			System.out.println("4. Get salary max");
			System.out.println("5. Register dependent5");
			System.out.print(">");
			Scanner s = new Scanner(System.in);
			int result = s.nextInt();
			option = Option.values()[result];			
		}
		catch(RuntimeException ex)
		{
			//nothing to do. 
		}
		
		return option;
		
	}
	private final static void clearConsole() throws Exception
	{
	    for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
	    System.out.println("\n");

	}
	private void Login() throws java.sql.SQLException
	{
		Connection con = DriverManager.getConnection(getConnectionString());
		if(con != null)
			con.close();      
		
	}
	public void Run() throws Exception
	{
		Login ();
		Option userInput = Option.Unknown;
		do
		{
			clearConsole();
			userInput = DisplayMenu();
			clearConsole();		  	
			try
			{		
				__dbMethods.get(userInput).doWork();		
				System.in.read();		
				
			}
			catch(NullPointerException ex)
			{
				//Nothing to do. The option was not a valid one. Read another.
			}
			
		}while(userInput!=Option.Exit);
	}

	public String getConnectionString()
	{
		return __connectionString;			
	}
	public void setConnectionString(String s) 
	{
		__connectionString = s;
	}

	/**
		To implement from this point forward. Do not need to change the code above.
	-------------------------------------------------------------------------------		
		IMPORTANT:
	--- DO NOT MOVE IN THE CODE ABOVE. JUST HAVE TO IMPLEMENT THE METHODS BELOW ---
	-------------------------------------------------------------------------------
	
	*/
	
}

public class Ap3
{
	public static void main(String[] args) throws SQLException,Exception
	{
		String url =  "jdbc:postgresql://10.62.73.22:5432/?user=test_1&password=test_1&ssl=false";
		App.getInstance().setConnectionString(url);
		App.getInstance().Run();
	}
}

/* -------------------------------------------------------------------------------- 
private class Connect {
	private java.sql.Connection con = null;
    private final String url = "jdbc:sqlserver://";
    private final String serverName = "localhost";
    private final String portNumber = "1433";
    private final String databaseName = "aula03";
    private final String userName = "matildepato";
    private final String password = "xxxxxxx";

    // Constructor
    public Connect() {
    }

    private java.sql.Connection getConnection() {
        try {
            con = java.sql.DriverManager.getConnection(url, user, pwd);
            if (con != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }
        return con;
    }
1
    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 --------------------------------------------------------------------------------
 */