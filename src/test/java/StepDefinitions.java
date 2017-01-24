import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class StepDefinitions {


    private Scenario scenario;
    private static String TEMPDIR = "src\\test\\temp";
    private File csvFile;
    private int limitForWaitingForFilesInSeconds = 20;


    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }


    @Given("^Pentaho is not stucked for (\\w+)$")
    public boolean pentahoIsNotStucked(String portfolioName) throws SQLException {

        PostgreSQLJDBC database = new PostgreSQLJDBC();
        Connection connection = database.getConnection();
        String query = "SELECT status FROM recovery WHERE portfolio_code = '" + portfolioName + "' AND service = 'PENTAHO' AND integration_cycle_name='Preprocessing';";
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            if(result.getString(1).equals("PAUSED")) {
                scenario.write("Pentaho is paused. Recover it before run.");
                Assert.fail();
            }
        }

        return true;
    }

    @Given("^Pentaho doesnt have more than (\\d+) files to process for (\\w+)$")
    public boolean pentahoDoesntHaveOverTheLimitInQueue(int limit, String portfolioName) throws SQLException {
        PostgreSQLJDBC database = new PostgreSQLJDBC();
        Connection connection = database.getConnection();
        String query = "SELECT count(*) FROM batch_file_job WHERE portfolio_code = '" + portfolioName + "' AND pentaho_job_action = 'NEW' OR pentaho_job_action = 'WAIT_FOR_FINISH';";
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        while (result.next()) {
            int toProcess = result.getInt(1);
            if(toProcess>limit) {
                scenario.write("Pentaho is too busy. "+ toProcess +" files to be processed.");
                Assert.fail();
            }
        }

        return true;
    }


    @Given("^I create (\\w+) file for (\\w+)$")
    public void createPlacementFile(String fileName, String portfolioName) {

        Portfolio portfolio = Portfolio.valueOf(portfolioName);

        CsvFileCreator fileCreator = null;
        String finalFileName = "";

        switch (fileName) {
            case "COLAPaymentPlacement" :

                fileCreator = new COLAPaymentPlacementCreator(portfolio);
                finalFileName = fileCreator.generateFinalFileName();
                break;

            case "COLAAccountPlacement" :

                fileCreator = new COLAAccountPlacementCreator(portfolio);
                finalFileName = fileCreator.generateFinalFileName();
                break;
        }


        try {
            fileCreator.init(TEMPDIR,finalFileName);
            csvFile = fileCreator.closeAndGetCsvFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileCreator.closeAndGetCsvFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @When("^I put that file to (\\D+) folder$")
    public void putFileToFTP(String folder) {


        FTPConnection ftpCon = new FTPConnection();
        ChannelSftp channel = ftpCon.establishFTPConnection();
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(csvFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String path = ftpCon.getSFTPROOT() + folder + csvFile.getName();
        String tempPath = ftpCon.getSFTPROOT() + folder + "temp_" + csvFile.getName();
        ftpCon.putFileToFTPandMove(channel,fileStream,path, tempPath);



    }

    @Then("^Pentaho process that file$")
    public boolean pentahoProcessed() throws SQLException, InterruptedException {

        PostgreSQLJDBC database = new PostgreSQLJDBC();
        Connection connection = database.getConnection();
        AccountPlacementFileTransformation transformation = new AccountPlacementFileTransformation(connection);

        int itr = 0;
        while (!transformation.fileInBatchFile(csvFile.getName()) && itr<limitForWaitingForFilesInSeconds) {
            TimeUnit.SECONDS.sleep(1);
            itr++;
        }
        if (itr==limitForWaitingForFilesInSeconds) {
            scenario.write("Processing exceeded " + limitForWaitingForFilesInSeconds + " setting");
            connection.close();
            Assert.fail();
        }

        return true;

    }


    @Then("^File stored in (\\D+) folder$")
    public boolean fileInBackup(String folder) throws SQLException, InterruptedException, SftpException {
        PostgreSQLJDBC database = new PostgreSQLJDBC();
        Connection connection = database.getConnection();
        AccountPlacementFileTransformation transformation = new AccountPlacementFileTransformation(connection);

        int itr = 0;
        while (!transformation.fileProcessing(csvFile.getName()).equals("FINISHED") && itr<limitForWaitingForFilesInSeconds) {
            TimeUnit.SECONDS.sleep(1);
            itr++;
        }
        if (itr==limitForWaitingForFilesInSeconds) {
            scenario.write("Processing exceeded " + limitForWaitingForFilesInSeconds + " setting");
            Assert.fail();
        }
        FTPConnection ftpCon = new FTPConnection();
        ChannelSftp channel = ftpCon.establishFTPConnection();
        String path = ftpCon.getSFTPROOT() + folder + "/" + csvFile.getName() + "*";
        if(!channel.ls(path).toString().contains(csvFile.getName())) {
            scenario.write("File is not in " + folder);
            Assert.fail();
        }

            return true;

    }
}
