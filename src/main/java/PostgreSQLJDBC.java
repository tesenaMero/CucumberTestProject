import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLJDBC {


    private static String dbIP = "";
    private static String port = "";
    private static String db = "";
    private static String user = "";
    private static String password = "";
    private Connection connection;





    PostgreSQLJDBC() {

        connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://" + dbIP + ":" + port + "/" + db,
                            user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+ ": " +e.getMessage());
        }

    }

    Connection getConnection() {
        return connection;
    }


}