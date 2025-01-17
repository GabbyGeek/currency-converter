package com.example.converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Bienvenido al Conversor de Monedas");
            System.out.println("1. Pesos MX a Dólares (USD)");
            System.out.println("2. Pesos MX a Euros (EUR)");
            System.out.println("3. Dólares (USD) a Yenes (JPY)");
            System.out.println("4. Euros (EUR) a Yuan Chino (CNY)");
            System.out.println("5. Pesos Argentinos (ARS) a Reales Brasileños (BRL)");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    convertCurrency("MXN", "USD", scanner);
                    break;
                case 2:
                    convertCurrency("MXN", "EUR", scanner);
                    break;
                case 3:
                    convertCurrency("USD", "JPY", scanner);
                    break;
                case 4:
                    convertCurrency("EUR", "CNY", scanner);
                    break;
                case 5:
                    convertCurrency("ARS", "BRL", scanner);
                    break;
                case 6:
                    exit = true;
                    System.out.println("¡Gracias por usar el conversor! Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
    }

    private static void convertCurrency(String fromCurrency, String toCurrency, Scanner scanner) {
        System.out.print("Ingresa el monto en " + fromCurrency + ": ");
        double amount = scanner.nextDouble();
        double exchangeRate = CurrencyConverter.getExchangeRate(fromCurrency, toCurrency);

        if (exchangeRate != 0) {
            double convertedAmount = amount * exchangeRate;
            System.out.println("El valor de " + amount + " " + fromCurrency + " es " + convertedAmount + " " + toCurrency);
        } else {
            System.out.println("No se pudo obtener el tipo de cambio. Intenta más tarde.");
        }
    }
}
