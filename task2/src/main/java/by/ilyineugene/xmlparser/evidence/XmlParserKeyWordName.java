package by.ilyineugene.xmlparser.evidence;

public enum XmlParserKeyWordName {

    SAX("sax"),
    STAX("stax"),
    DOM("dom");

    private String value;

    XmlParserKeyWordName(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
