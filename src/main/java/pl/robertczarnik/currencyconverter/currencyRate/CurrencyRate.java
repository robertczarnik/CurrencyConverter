package pl.robertczarnik.currencyconverter.currencyRate;


public class CurrencyRate {
    private String currency;
    private Double mid;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }
}
