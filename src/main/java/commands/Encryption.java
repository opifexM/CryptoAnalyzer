package commands;

import base.Crypto;
import base.Logger;

public class Encryption {
    private Encryption() {
    }

    public static String encryption(Crypto crypto, String text, int key) {
        if (key < 0 || key > crypto.getCryptoBaseIndex()) {
            Logger.log("Ключ не может быть меньше 0 или больше " + crypto.getCryptoBaseIndex());
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
        Logger.log("[Encryption] " + text);
    }

}
