package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/** This class is used to establish the database connection. */
public abstract class JDBC {

    //Set up for the jdbc Url
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName ;  //+ "?connectionTimeZone = SERVER"

    //Variable for the driver set up
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    //Variable to access the database using the username and password
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";

    //Variable for the connection reference type
    public static Connection connection;

    //Establish connection wih the database
    public static void openConnection() {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("connection successful");

        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());

        }
    }

    //Close the connection to the database
    public static void closeConnection() {

        try{

            connection.close();
            System.out.println("connection close");

        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());

        }

    }



}
