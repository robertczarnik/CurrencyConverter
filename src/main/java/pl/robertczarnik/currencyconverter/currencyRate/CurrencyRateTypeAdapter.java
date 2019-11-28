package pl.robertczarnik.currencyconverter.currencyRate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Adapter needed for mapping json to flat object (without arrays)
 */
class CurrencyRateTypeAdapter extends TypeAdapter<CurrencyRate> {

    @Override
    public CurrencyRate read(JsonReader in) throws IOException {
        final CurrencyRate currencyRate = new CurrencyRate();

        in.beginObject();

        while (in.hasNext()) {
            String name = in.nextName();

            if (name.startsWith("currency")) {
                currencyRate.setCurrency(in.nextString());
            } else if (name.equals("rates")) {
                in.beginArray();
                in.beginObject();
            } else if (name.startsWith("mid")) {
                currencyRate.setMid(in.nextDouble());
            } else {
                in.nextString();
            }
        }

        in.endObject();
        in.endArray();

        in.endObject();

        return currencyRate;
    }

    @Override
    public void write(JsonWriter out, CurrencyRate value) throws IOException {
        throw new RuntimeException("CurrencyRateTypeAdapter's write method not implemented!");
    }
}
