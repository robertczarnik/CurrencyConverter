package pl.robertczarnik.currencyconverter.currencyconverter;

public enum CurrencyEnum {
    PLN("Złoty"),
    EUR("Euro"),
    USD("US Dollar"),
    GBP("Pound Sterling"),
    NOK("Norwegian Krone"),
    AUD("Australian Dollar"),
    CZK("Czech Koruna");

    private final String name;

    CurrencyEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
