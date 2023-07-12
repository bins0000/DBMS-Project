package jsp_azure_test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataHandler {

    private Connection conn;

    // Azure SQL connection credentials
    private String server = "bins0000-sql-server.database.windows.net";
    private String database = "cs-dsa-4513-sql-db";
    private String username = "bins0000";
    private String password = "<databaseFall22!>";

    // Resulting connection string
    final private String url =
            String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                    server, database, username, password);

    // Initialize and save the database connection
    private void getDBConnection() throws SQLException {
        if (conn != null) {
            return;
        }

        this.conn = DriverManager.getConnection(url);
    }

    // Return the result of selecting everything from the movie_night table 
    public ResultSet query12() throws SQLException {
        getDBConnection();
        
        final String sqlQuery = "EXEC case12 @salary = ?;";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        return stmt.executeQuery();
    }

    // Inserts a record into the movie_night table with the given attribute values
    public ResultSet getEmployee(Float salary) throws SQLException {

        getDBConnection(); // Prepare the database connection

        // Prepare the SQL statement
        final String sqlQuery = "EXEC case12 @salary = ?;";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        // Replace the '?' in the above statement with the given attribute values
        stmt.setFloat(1, salary);


        return stmt.executeQuery();
    }
    
    // Return the result of selecting everything from the employees table 
    public ResultSet getAllEmployee() throws SQLException {
        getDBConnection();
        
        final String sqlQuery = "SELECT * FROM worker;";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        return stmt.executeQuery();
    }
    
 // Inserts a record into the movie_night table with the given attribute values
    public boolean addWorker(
            String wname, String waddress, String wsalary, String max_no_products) throws SQLException {

        getDBConnection(); // Prepare the database connection

        // Prepare the SQL statement
        final String sqlQuery =
                "INSERT INTO worker " + 
                    "(wname, waddress, wsalary, max_no_products) " + 
                "VALUES " + 
                "(?, ?, ?, ?)";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        // Replace the '?' in the above statement with the given attribute values
        stmt.setString(1, wname);
        stmt.setString(2, waddress);
        stmt.setString(3, wsalary);
        stmt.setString(4, max_no_products);

        // Execute the query, if only one record is updated, then we indicate success by returning true
        return stmt.executeUpdate() == 1;
    }
    
 // Inserts a record into the movie_night table with the given attribute values
    public boolean addQualityController(
            String qname, String qaddress, String qsalary, String qtype) throws SQLException {

        getDBConnection(); // Prepare the database connection

        // Prepare the SQL statement
        final String sqlQuery =
                "INSERT INTO quality_controller " + 
                    "(qname, qaddress, qsalary, qtype) " + 
                "VALUES " + 
                "(?, ?, ?, ?)";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        // Replace the '?' in the above statement with the given attribute values
        stmt.setString(1, qname);
        stmt.setString(2, qaddress);
        stmt.setString(3, qsalary);
        stmt.setString(4, qtype);

        // Execute the query, if only one record is updated, then we indicate success by returning true
        return stmt.executeUpdate() == 1;
    }
    
 // Inserts a record into the movie_night table with the given attribute values
    public boolean addTechnicalStaff(
            String tname, String taddress, String tsalary, String highest_degree, String tech_position) throws SQLException {

        getDBConnection(); // Prepare the database connection

        // Prepare the SQL statement
        final String sqlQuery =
                "INSERT INTO technical_staff " + 
                    "(tname, taddress, tsalary, highest_degree, tech_position) " + 
                "VALUES " + 
                "(?, ?, ?, ?, ?)";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        // Replace the '?' in the above statement with the given attribute values
        stmt.setString(1, tname);
        stmt.setString(2, taddress);
        stmt.setString(3, tsalary);
        stmt.setString(4, highest_degree);
        stmt.setString(5, tech_position);

        // Execute the query, if only one record is updated, then we indicate success by returning true
        return stmt.executeUpdate() == 1;
    }
}

