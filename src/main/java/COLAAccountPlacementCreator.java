import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import static org.joda.time.format.DateTimeFormat.forPattern;


public class COLAAccountPlacementCreator extends CsvFileCreator {


    private String fileNamePrefix = "COLAAccountPlacement";
    private String csvFileNamePrefix = "COLAAccountPlacement";
    private Portfolio portfolio;

    public COLAAccountPlacementCreator(Portfolio portfolio) {
        this.portfolio = portfolio;

    }


    private static final List<String> COLUMN_HEADERS = Arrays.asList(
            "AccountNumber",
            "borrowerCustomerId",
            "coBorrowerCustomerId",
            "brandIndicator",
            "portfolioIndicator",
            "productType",
            "statusCode",
            "openDate",
            "creditLine",
            "apr",
            "balance",
            "currentDue",
            "overlimitAmount",
            "borrowerFirstName",
            "borrowerMiddleName",
            "borrowerLastName",
            "borrowerHomeAddress1",
            "borrowerHomeAddress2",
            "borrowerHomeAddress3",
            "borrowerHomeCity",
            "borrowerHomeState",
            "borrowerHomeZip",
            "borrowerHomeEmailAddress",
            "borrowerHomePhone",
            "borrowerOtherAddress1",
            "borrowerOtherAddress2",
            "borrowerOtherAddress3",
            "borrowerOtherCity",
            "borrowerOtherState",
            "borrowerOtherZip",
            "borrowerOtherEmailAddress",
            "borrowerOtherPhone",
            "borrowerWorkAddress1",
            "borrowerWorkAddress2",
            "borrowerWorkAddress3",
            "borrowerWorkCity",
            "borrowerWorkState",
            "borrowerWorkZip",
            "borrowerWorkPhone",
            "borrowerWorkEmailAddress",
            "borrowerDateofBirth",
            "borrowerSSN",
            "borrowerGender",
            "borrowerBureauScore",
            "borrowerClientConsentDate",
            "borrowerDoNotContactFlag",
            "coBorrowerFirstName",
            "coBorrowerMiddleName",
            "coBorrowerLastName",
            "coBorrowerHomeAddress1",
            "coBorrowerHomeAddress2",
            "coBorrowerHomeAddress3",
            "coBorrowerHomeCity",
            "coBorrowerHomeState",
            "coBorrowerHomeZip",
            "coBorrowerHomeEmailAddress",
            "coBorrowerHomePhone",
            "coBorrowerOtherAddress1",
            "coBorrowerOtherAddress2",
            "coBorrowerOtherAddress3",
            "coBorrowerOtherCity",
            "coBorrowerOtherState",
            "coBorrowerOtherZip",
            "coBorrowerOtherEmailAddress",
            "coBorrowerOtherPhone",
            "coBorrowerWorkAddress1",
            "coBorrowerWorkAddress2",
            "coBorrowerWorkAddress3",
            "coBorrowerWorkCity",
            "coBorrowerWorkState",
            "coBorrowerWorkZip",
            "coBorrowerWorkPhone",
            "coBorrowerWorkEmailAddress",
            "coBorrowerDateofBirth",
            "coBorrowerSSN",
            "coBorrowerGender",
            "coBorrowerBureauScore",
            "coBorrowerClientConsentDate",
            "coBorrowerDoNotContactFlag",
            "lastPaymentAmount",
            "lastPaymentDate",
            "billingCycle",
            "billingDate",
            "daysPastDue",
            "cyclesPastDue",
            "bucketAmount1",
            "bucketAmount2",
            "bucketAmount3",
            "bucketAmount4",
            "bucketAmount5",
            "bucketAmount6",
            "bucketAmount7",
            "maxDelinquencyDays",
            "reasonForDelinquency",
            "lastReageDate",
            "lastWorkoutReageDate",
            "pastDue",
            "secondToLastReageDate",
            "secondToLastWorkoutReageDate",
            "lastReageType",
            "reageCounter",
            "chargeOffDate",
            "chargeOffAmount",
            "originalChargeOffAmount",
            "chargeOffReasonCode",
            "originalCreditor",
            "riskScore",
            "behaviorScore",
            "randomDigits",
            "fixedPaymentAmount",
            "currentPrincipalAmount",
            "currentInitialComissionAmount",
            "currentPenaltyAmount",
            "currentComissionAmount",
            "currentInterestAmount",
            "currentDuePrincipalAmount",
            "currentDueInitialCommissionAmount",
            "currentDueInterestAmount",
            "drawChannelID",
            "distributionChannelID",
            "comments",
            "extensionCount",
            "currentDueDate",
            "originalDueDate",
            "originalPrincipalAmount",
            "originalInitialComission",
            "originalInterestRate",
            "originalRenouncementDate",
            "originalCurrency",
            "contractNumber",
            "preferredBankContactBankName",
            "preferredBankContactAccountNumber",
            "preferredBankContactSwiftCode",
            "brokenFlag",
            "borrowerTitle",
            "borrowerLanguage",
            "borrowerPersonalId",
            "coBorrowerTitle",
            "coBorrowerLanguage",
            "coBorrowerPersonalId",
            "contractLinkSOR",
            "borrowerLinkSOR",
            "extensionFeeAmount1",
            "extensionFeeTerm1",
            "extensionFeeTermUnit1",
            "extensionFeeAmount2",
            "extensionFeeTerm2",
            "extensionFeeTermUnit2",
            "extensionFeeAmount3",
            "extensionFeeTerm3",
            "extensionFeeTermUnit3",
            "extensionFeeAmount4",
            "extensionFeeTerm4",
            "extensionFeeTermUnit4",
            "extensionFeeAmount5",
            "extensionFeeTerm5",
            "extensionFeeTermUnit5",
            "borrowerScore1",
            "borrowerScoreType1",
            "borrowerScore2",
            "borrowerScoreType2",
            "coBorrowerScore1",
            "coBorrowerScoreType1",
            "coBorrowerScore2",
            "coBorrowerScoreType2",
            "borrowerHomePhoneStatus",
            "borrowerOtherPhoneStatus",
            "borrowerWorkPhoneStatus",
            "coBorrowerHomePhoneStatus",
            "coBorrowerOtherPhoneStatus",
            "coBorrowerWorkPhoneStatus",
            "incomeFrequency",
            "nextIncomeDate",
            "monthlyIncomeAmount",
            "monthlyOtherCreditAgreementAmount",
            "monthlyExpenseAmount",
            "borrowerMaritalStatus",
            "coBorrowerMaritalStatus",
            "loanObject",
            "loanObjectDetail",
            "loanObjectValueAmount",
            "loanObjectIsCollateralFlag",
            "collateralObject",
            "collateralObjectDetail",
            "collateralObjectValueAmount",
            "CPAFlag",
            "borrowerDeathFlag",
            "borrowerDeathDate",
            "borrowerIDCardType",
            "borrowerIDCardNumber",
            "borrowerIDCardIssuer",
            "borrowerIDCardIssueDate",
            "borrowerIDCardExpirationDate",
            "borrowerIDCardIssuePlace",
            "borrowerMobilePhone",
            "coBorrowerMobilePhone",
            "accountStatus",
            "endDate",
            "firstMissedPaymentDate",
            "lastMissedPaymentDate",
            "financedAmount",
            "borrowerHomeAddress4"

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
