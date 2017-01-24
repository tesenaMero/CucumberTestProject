import static java.io.File.*;
import static org.apache.commons.lang.StringUtils.*;
import static org.joda.time.format.DateTimeFormat.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CsvFileCreator {

    private static final Logger LOG = LoggerFactory.getLogger(CsvFileCreator.class);
    private static final String CSV_ENCODING = "UTF-8";
    public static final String CSV_EXTENSION = ".csv";
    private static final String ZIP_EXTENSION = ".zip";
    public static final String DATE_FORMAT = "yyyyMMdd_HHmm";
    private static final String HOURS_FORMAT = "yyyyMMddHHmmss";

    private CsvWriter csvWriter;
    private File csvFile;
    private int totalItemsCount;

    public void init(String filePath, String csvFileName) throws FileNotFoundException {
        csvFile = createCsvFile(filePath, csvFileName);
        totalItemsCount = 0;
        csvWriter = new CsvWriter(new FileOutputStream(csvFile), CSV_ENCODING);
        csvWriter.writeRow(buildCsvHeaders());
    }

    protected abstract String generateFinalFileName();

    public File closeAndGetCsvFile() throws IOException {
        csvWriter.close();
        LOG.info("created CSV file : " + csvFile + ", entries: " + totalItemsCount);
        return csvFile;
    }

    public int getTotalItemsCount() {
        return totalItemsCount;
    }

    public final void addToCsvPackage(Long id) {
        csvWriter.writeRow(buildCsvRow(id));
        totalItemsCount++;
    }

    protected abstract String getCsvFileNamePrefix();

    protected abstract String getStartOfTheFile();

    protected abstract String getEndOfTheFile();

    protected abstract String getFileNamePrefix();

    protected abstract String getPortfolio();

    protected abstract List<String> buildCsvHeaders();

    protected abstract CsvRow buildCsvRow(Long loanId);


    private File createCsvFile(String filePath, String fileName) {
        return isEmpty(filePath) ? new File(fileName) : new File(filePath + separator, fileName);
    }

}