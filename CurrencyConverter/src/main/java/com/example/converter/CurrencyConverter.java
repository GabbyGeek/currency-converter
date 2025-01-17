package com.example.converter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/87095ca5186c493d0496baa3/latest/";

    public static double getExchangeRate(String fromCurrency, String toCurrency) {
        try {
            String urlString = API_URL + fromCurrency;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");
            return conversionRates.getDouble(toCurrency);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
