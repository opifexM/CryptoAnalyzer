package commands;

import base.Crypto;

public class Decryption {
    public static String decryption(Crypto crypto, String text, int key) {
        if (key < 0 || key > crypto.getCryptoBaseIndex()) {
            System.out.println("Ключ не может быть меньше 0 или больше " + crypto.getCryptoBaseIndex());
            return "";
        }

        StringBuilder decryptionText = new StringBuilder();
        for (char symbol : text.toCharArray()) {
            int symbolIndex = crypto.getCryptoBase().indexOf(symbol);
            if (symbolIndex < 0) {
                decryptionText.append(symbol);
                continue;
            }
            if (symbolIndex - key < 0) {
                symbolIndex = crypto.getCryptoBaseIndex() - (key - symbolIndex);
            } else {
                symbolIndex -= key;
            }
            decryptionText.append(crypto.getCryptoBase().get(symbolIndex));
        }
        return decryptionText.toString();
    }

    public static void printDecryptionText(String text) {
        System.out.println("[Decryption] " + text);
    }
}
