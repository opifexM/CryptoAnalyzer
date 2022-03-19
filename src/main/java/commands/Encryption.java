package commands;

import base.Crypto;

public class Encryption {
    public static String encryption(Crypto crypto, String text, int key) {
        if (key < 0 || key > crypto.getCryptoBaseIndex()) {
            System.out.println("Ключ не может быть меньше 0 или больше " + crypto.getCryptoBaseIndex());
            return "";
        }
        StringBuilder encryptionText = new StringBuilder();
        for (char symbol : text.toCharArray()) {
            int symbolIndex = crypto.getCryptoBase().indexOf(symbol);
            if (symbolIndex < 0) {
                encryptionText.append(symbol);
                continue;
            }
            if (symbolIndex + key >= crypto.getCryptoBaseIndex()) {
                symbolIndex = symbolIndex + key - crypto.getCryptoBaseIndex();
            } else {
                symbolIndex += key;
            }
            encryptionText.append(crypto.getCryptoBase().get(symbolIndex));
        }


        return encryptionText.toString();
    }

    public static void printEncryptionText(String text) {
        System.out.println("[Encryption] " + text) ;
    }

}
