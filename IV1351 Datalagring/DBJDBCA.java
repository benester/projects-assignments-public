/* This program is part of the project in the 'datalagring' course.
** it should be able to do 3 things as of now
* 1. Show all rental compounds
* 2. Show all the houses in a rental compund when the user enters an ID
* 3. Add a house to the database, when the user enters information
*
*
*/

import java.sql.*;
import java.util.Scanner;

public class DBJDBCA
{
    // DB connection variable
    static protected Connection con;
    // DB access variables
    private String URL = "jdbc:ucanaccess://C:/Users/benja/Desktop/labb/ProjektEtapp2.accdb";
    private String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private String userID = "root";
    private String password = "bunny";

    // method for establishing a DB connection
    public void connect()
    {
        try
        {
            //create a connection to the database
            con = DriverManager.getConnection(URL);
            // Set the auto commit of the connection to false.
            // An explicit commit will be required in order to accept
            // any changes done to the DB through this connection.
            con.setAutoCommit(false);
			//Some logging
			System.out.println("Connected to " + URL + " using "+ driver);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void showRentalCompounds() throws Exception
    {
        // Local variables
        String query;
        ResultSet rs;
        Statement stmt;

        // Set the SQL statement into the query variable query
        query = "SELECT * FROM Förening";

        // Create a statement associated to the connection con.
        // The new statement is placed in the variable stmt.
        stmt = con.createStatement();

        // Execute the SQL statement that is stored in the variable query
        // and store the result in the variable rs.
        rs = stmt.executeQuery(query);

        System.out.println("The result (All the differant rental compounds):");

        // Loop through the result set and print the results.
        // The method next() returns false when there are no more rows.
        while (rs.next())
        {
            System.out.println("Rental compound number: " + rs.getString("orgNr") + "Rental compound Name:" + rs.getString("namn"));
        }

        // Close the variable stmt and release all resources bound to it
        // Any ResultSet associated to the Statement will be automatically closed too.
        stmt.close();
    }

    public void showAllHousesFromRentalCompound() throws Exception
    {
        // Local variables
        String query;
        ResultSet rs;
        PreparedStatement stmt;
        String orgNr;

        // Create a Scanner in order to allow the user to provide input.
        Scanner in = new Scanner(System.in);

        // This is the old way (Java 1.4 or earlier) for reading user input:
        // Create a BufferedReader in order to allow the user to provide input.
        // java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        // Ask the user to specify a value for organization nr
        System.out.print("Please enter the organization nr: ");
        // Retrieve the value and place it in the variable orgNr
        orgNr = in.nextLine();

        // Set the SQL statement into the query variable query
        query = "SELECT * FROM Fastighet WHERE föreningsOrgNr = ?";

        // Create a statement associated to the connection and the query.
        // The new statement is placed in the variable stmt.
        stmt = con.prepareStatement(query);

        // Provide the value for the questonmark in the SQL statement.
        // The value of the variable orgNr will be sent to the database manager
        // through the variables stmt and con.
        stmt.setString(1, orgNr);

        // Execute the SQL statement that is prepared in the variable stmt
        // and store the result in the variable rs.
        rs = stmt.executeQuery();

        System.out.println("The result (Houses in the Rental Compound: " + orgNr + "):");

        // Loop through the result set and print the results.
        // The method next() returns false when there are no more rows.
        while (rs.next())
        {
            System.out.println("org nr: "+ rs.getString("föreningsOrgNr") + " " + rs.getString("adress") + " " + rs.getString("postNr") + " " + rs.getString("postOrt") + " " + rs.getString("byggår") + " Floors: " + rs.getString("antalVåningar"));
        }

        // Close the variable stmt and release all resources bound to it
        // Any ResultSet associated to the Statement will be automatically closed too.
        stmt.close();
    }
    public void insertNewHouse() throws Exception
    {
        // Local variables
        String query;
        PreparedStatement stmt;
        String orgNr;
        String adress;
        String postOrt;
        String postNr;
        String byggår;
        String antalVåningar;

        // Create a Scanner in order to allow the user to provide input.
        Scanner in = new Scanner(System.in);

        // Ask the user to specify a value for org nr.
        System.out.print("Enter org number: ");
        // Retrieve the value and place it in the variable orgnr.
        orgNr = in.nextLine();
        // Ask the user to specify a value for adress
        System.out.print("Enter Adress: ");
        // Retrieve the value and place it in the variable adress.
        adress = in.nextLine();
        // Ask the user to specify a value for Postal area.
        System.out.print("Enter Postal area: ");
        // Retrieve the value and place it in the variable postOrt.
        postOrt = in.nextLine();
        //Ask the user to specify a value for Postal number
        System.out.print("Enter Postal number: ");
        // Retrieve the value and place it in the variable postNr.
        postNr = in.nextLine();
        //Ask the user to specify a value for the year the building was built
        System.out.print("Enter the year the building was built: ");
        // Retrieve the value and place it in the variable byggår.
        byggår = in.nextLine();
        //Ask the user to specift a value for the amount of floors in the building 
        System.out.print("Enter the amount of floors in the building ");
        // Retrieve the value and place it in the variable antalVåningar.
        antalVåningar = in.nextLine();

        in.close();

        // Set the SQL statement into the query variable query
        query = "INSERT INTO Fastighet (föreningsOrgNr, adress, postort, postNr, byggår, antalVåningar) VALUES (?, ?, ?, ?, ?, ?)";
        
        // Create a statement associated to the connection and the query.
        // The new statement is placed in the variable stmt.
        stmt = con.prepareStatement(query);

        // Provide the values for the ?'s in the SQL statement.
        //Each ? will be replaced in the order that these variables occur
        stmt.setString(1, orgNr);
        stmt.setString(2, adress);
        stmt.setString(3, postOrt);
        stmt.setString(4, postNr);
        stmt.setString(5, byggår);
        stmt.setString(6, antalVåningar);

        // Execute the SQL statement that is prepared in the variable stmt
        stmt.executeUpdate();

        // Close the variable stmt and release all resources bound to it
        stmt.close();
    }

    public static void main(String[] argv) throws Exception
    {
        // Create a new object of this class.
        DBJDBCA t = new DBJDBCA();

        // Call methods on the object t.
		System.out.println("-------- connect() ---------");
        t.connect();
		System.out.println("-------- showRentalCompounds() ---------");
        t.showRentalCompounds();
		System.out.println("-------- showAllHousesFromRentalCompound() ---------");
        t.showAllHousesFromRentalCompound();
		System.out.println("-------- insertNewHouse() ---------");
        t.insertNewHouse();

        // Commit the changes made to the database through this connection.
        con.commit();
        // Close the connection.
        con.close();
    }
}