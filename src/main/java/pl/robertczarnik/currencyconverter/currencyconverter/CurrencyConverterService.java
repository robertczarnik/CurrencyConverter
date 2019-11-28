package pl.robertczarnik.currencyconverter.currencyconverter;

import org.springframework.stereotype.Service;
import pl.robertczarnik.currencyconverter.currencyRate.CurrencyRate;
import pl.robertczarnik.currencyconverter.currencyRate.CurrencyRateService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class CurrencyConverterService {

    private static List<CurrencyEnum> enumCurrencies = Arrays.asList(CurrencyEnum.values());
    private static List<String> enumCurrenciesString = Arrays.asList(getNames(CurrencyEnum.class));
    private CurrencyRateService currencyRateService;


    public CurrencyConverterService(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    /**
     * Get all available currencies
     *
     * @return map of currency code and currency name
     */
    Map<CurrencyEnum, String> getAvailabeCurrencies() {
        HashMap<CurrencyEnum, String> availableCurrencies = new HashMap<>();

        enumCurrencies.forEach(s -> availableCurrencies.put(s, s.getName()));

        return availableCurrencies;
    }

    /**
     * Convert money from {@code from} currency to {@code to} currency
     *
     * @param amount value of converted money
     * @param from   currency code
     * @param to     currency code
     * @return money in {@code to} currency
     */
    double convert(Double amount, String from, String to) {
        double result = 0.0;

        if (from.equals(to)) return amount; // from and to are the same

        if (from.equals("PLN")) {
            if (enumCurrenciesString.contains("PLN")) {
                List<CurrencyRate> list = currencyRateService.getActualCurrencyRate(List.of(to));
                if (list.size() == 1) {
                    double to_factor = list.get(0).getMid();
                    result = amount / to_factor;
                }
            }
        } else if (to.equals("PLN")) {
            if (enumCurrenciesString.contains("PLN")) {
                List<CurrencyRate> list = currencyRateService.getActualCurrencyRate(List.of(from));
                if (list.size() == 1) {
                    double from_factor = list.get(0).getMid();
                    result = amount * from_factor;
                }
            }
        } else {
            if (enumCurrenciesString.containsAll(List.of(from, to))) {
                List<CurrencyRate> list = currencyRateService.getActualCurrencyRate(List.of(from, to));
                if (list.size() == 2) {
                    double to_factor = list.get(0).getMid();
                    double from_factor = list.get(1).getMid();
                    result = amount / from_factor * to_factor;
                }
            }
        }

        return Math.round(result * 100.0) / 100.0; // round up to 2 decimal places
    }

    private static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
