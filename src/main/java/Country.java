public enum Country {
    CZ("CZ","Czech Republic"),
    GT("GT","Guatemala"),
    AR("AR","Argentina"),
    MX("MX","Mexico"),
    RO("RO","Romania"),
    DK("DK","Denmark"),
    LT("LT","Lithuania"),
    LV("LV","Latvia"),
    ES("ES","Spain"),
    FI("FI","Finland"),
    DO("DO","Dominican Republic");



    private String abbreviation;
    private String countryNameInEnglish;

    private Country(String abbreviation, String countryNameInEnglish) {

        this.abbreviation = abbreviation;
        this.countryNameInEnglish = countryNameInEnglish;

    }
}
