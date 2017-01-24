import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountPlacementFileTransformation {



    private Connection connection;

    AccountPlacementFileTransformation(Connection connection) {


        this.connection = connection;


    }



    boolean fileInBatchFile(String fileName) throws SQLException {

        String query = "SELECT count(*) FROM batch_file WHERE file_name = '" + fileName + "';";
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        int numberOfRows = 0;
        while (result.next()) {
            numberOfRows = result.getInt(1);

        }

        if (numberOfRows == 1) {

            return true;

        }
        else if (numberOfRows == 0) {
            return false;
        }
        else {
            System.err.println("More rows found!");
            return false;
        }
    }

    String fileProcessing(String fileName) throws SQLException {

        String query = "SELECT pentaho_job_action FROM batch_file_job WHERE file_name = '" + fileName + "';";
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        String status = "";
        while (result.next()) {
            status = result.getString(1);

        }
        return status;


    }
}
