package pl.robertczarnik.currencyconverter.currencyconverter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/convert")
@RestController
class CurrencyConverterController {

    private CurrencyConverterService currencyConverterService;

    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping("/available")
    public Map<CurrencyEnum,String> getAvailableCurrencies(){
        return currencyConverterService.getAvailabeCurrencies();
    }

    @GetMapping("")
    public double convert(@RequestParam Double amount,@RequestParam String from,@RequestParam String to){
        return currencyConverterService.convert(amount,from,to);
    }
}
