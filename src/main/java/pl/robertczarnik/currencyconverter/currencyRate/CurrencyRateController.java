package pl.robertczarnik.currencyconverter.currencyRate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/currency-rate")
@RestController
class CurrencyRateController {

    private CurrencyRateService currencyRateService;

    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @GetMapping("")
    List<CurrencyRate> getActualCurrencyRate(@RequestParam final List<String> list){
        return currencyRateService.getActualCurrencyRate(list);
    }
}
