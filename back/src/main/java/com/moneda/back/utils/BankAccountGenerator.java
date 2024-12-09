package com.moneda.back.utils;
import java.util.Random;
import java.util.stream.Collectors;
public class BankAccountGenerator {
    // Generador de número de cuenta único (10 dígitos)
    public static String generateAccountNumber() {
        return String.format("%010d", (long) (Math.random() * 1_000_000_0000L));
    }

    // Generador de CVU basado en el número de cuenta y otros identificadores
    public static String generateCvu(String accountNumber, String bankCode, String branchCode) {
        String firstBlock = bankCode + branchCode;
        int firstVerifier = calculateModulo10(firstBlock);
        int secondVerifier = calculateModulo10(accountNumber);
        return firstBlock + firstVerifier + accountNumber + secondVerifier;
    }

    // Generador de alias aleatorio de 4 caracteres
    public static String generateAlias() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return new Random().ints(4, 0, chars.length())
                .mapToObj(chars::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    // Calcula el dígito verificador usando Módulo 10
    private static int calculateModulo10(String input) {
        int[] weights = {7, 1, 3}; // Ponderaciones
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            sum += Character.getNumericValue(input.charAt(i)) * weights[i % weights.length];
        }
        return (10 - (sum % 10)) % 10;
    }
}
