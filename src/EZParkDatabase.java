import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class EZParkDatabase {
    public static Connection connect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/ezparkevents";
        String user = "root";
        String password = "Stcape01022022!";
        return DriverManager.getConnection(url, user, password);
    }

    // This method is the query statement we'd make to return rows in SQL.
    public static void fetchEvents(User user) throws Exception {
        // Establish the connection when fetchEvents is called
        Connection conn = connect();
        Statement stmt = conn.createStatement();

        // Execute the query with the WHERE clause to filter by closest location to user.
        ResultSet rs = stmt.executeQuery("SELECT * FROM events WHERE location = " + user.getLocation());
        while (rs.next()) {
            System.out.println("Event: " + rs.getString("event_name") + "\n" +
                    "Location: " + rs.getString("location"));
        }
        conn.close();
    }
}
