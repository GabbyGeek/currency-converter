package com.example.converter;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/convert", new ConversionHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor iniciado en el puerto 8080...");
    }

    static class ConversionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String[] params = query.split("&");
            String fromCurrency = params[0].split("=")[1];
            String toCurrency = params[1].split("=")[1];
            double amount = Double.parseDouble(params[2].split("=")[1]);

            double exchangeRate = CurrencyConverter.getExchangeRate(fromCurrency, toCurrency);
            double convertedAmount = amount * exchangeRate;

            JSONObject response = new JSONObject();
            response.put("from", fromCurrency);
            response.put("to", toCurrency);
            response.put("amount", amount);
            response.put("convertedAmount", convertedAmount);

            String jsonResponse = response.toString();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponse.length());

            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();
        }
    }
}
