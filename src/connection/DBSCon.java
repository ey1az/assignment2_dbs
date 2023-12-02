package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBSCon {
    public static Connection connect() {
        String URL = "jdbc:postgresql://localhost:5432/as2_dbs";
        String user = "postgres";
        String password = "admin1234";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}