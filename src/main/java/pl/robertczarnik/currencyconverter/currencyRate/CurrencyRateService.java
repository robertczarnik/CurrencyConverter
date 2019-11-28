package pl.robertczarnik.currencyconverter.currencyRate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyRateService {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson;


    public CurrencyRateService() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(CurrencyRate.class, new CurrencyRateTypeAdapter());
        this.gson = builder.create();
    }

    /**
     * If currency code is valid get actual currency rate from nbp api otherwise get Optional.empty()
     * @param code the currency code e.g. EUR
     * @return an Optional with CurrencyRate instance or Optional.empty()
     */
    private Optional<CurrencyRate> getActualCurrencyRateOptional(String code){
        String url = new HttpUrl.Builder()
                .scheme("http")
                .host("api.nbp.pl")
                .addPathSegments("api/exchangerates/rates/A/" + code + "/")
                .addQueryParameter("format", "json")
                .build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();


        try {
            Response response = client.newCall(request).execute();

            String json;
            if(response.isSuccessful()){
                json = Objects.requireNonNull(response.body()).string();
            }else{
                return Optional.empty(); // if data not found return Optional.empty()
            }

            return Optional.of(gson.fromJson(json,CurrencyRate.class));

        } catch (IOException e) {
            return Optional.empty(); // if someting went wrong return Optional.empty()
        }
    }

    /**
     * Get a list of of CurrencyRate objects
     * @param currencyList list with currency codes
     * @return list of CurrencyRate objects
     */
    public List<CurrencyRate> getActualCurrencyRate(List<String> currencyList){
        return currencyList.stream()
                .map(this::getActualCurrencyRateOptional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
