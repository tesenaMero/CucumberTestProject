import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import static org.joda.time.format.DateTimeFormat.forPattern;

public class COLAPaymentPlacementCreator extends CsvFileCreator {

    private String fileNamePrefix = "COLAPaymentPlacement";
    private String csvFileNamePrefix = "COLAPaymentPlacement";
    private Portfolio portfolio;

    public COLAPaymentPlacementCreator(Portfolio portfolio) {
        this.portfolio = portfolio;

    }

    private static final List<String> COLUMN_HEADERS = Arrays.asList(
            "AccountNumber",
            "borrowerCustomerId",
            "TransactionNumber",
            "PaymentStatus",
            "PaymentAmount",
            "PaymentDate",
            "PaidDate",
            "RejectedDate",
            "LastmodifiedDate",
            "paymentSumAmount",
            "currency",
            "paymentType",
            "detail",
            "processedDate",
            "bankReference",
            "TransactionAccountHolderName",
            "processedTimestamp"
    );

    @Override
    protected String generateFinalFileName() {

            String now = forPattern(DATE_FORMAT).print(new DateTime());
            return getPortfolio()  + "_" + getFileNamePrefix() + "_" + now + CSV_EXTENSION;

    }

    @Override
    protected String getCsvFileNamePrefix() {
        return csvFileNamePrefix;
    }

    @Override
    protected String getStartOfTheFile() {
        return null;
    }

    @Override
    protected String getEndOfTheFile() {
        return null;
    }

    @Override
    protected String getFileNamePrefix() {
        return fileNamePrefix;
    }

    @Override
    protected String getPortfolio() {
        return portfolio.getPortfolioName();
    }

    @Override
    protected List<String> buildCsvHeaders() {
        return COLUMN_HEADERS;
    }

    @Override
    protected CsvRow buildCsvRow(Long loanId) {
        return null;
    }
}
