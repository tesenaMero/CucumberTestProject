



public enum Portfolio {

    CZ_Zaplo(Country.CZ,"CZ_Zaplo"),
    CZ_Kimbi(Country.CZ,"CZ_Kimbi"),
    RO_Zaplo(Country.RO,"RO_Zaplo"),
    RO_Onnen(Country.RO,"RO_Onnen"),
    MX_Vivus(Country.MX,"MX_Vivus"),
    AR_Vivus(Country.AR,"AR_Vivus"),
    DK_Vivus(Country.DK,"DK_Vivus"),
    DK_Zaplo(Country.DK,"DK_Zaplo"),
    ES_Zaplo(Country.ES,"ES_Zaplo"),
    ES_Vivus(Country.ES,"ES_Vivus"),
    DO_Vivus(Country.DO,"DO_Vivus");



    private Country country;
    private String portfolioName;


    private Portfolio(Country country, String portfolioName) {

        this.country = country;
        this.portfolioName = portfolioName;


    }

    public String getPortfolioName() {
        return portfolioName;
    }
}
