package commands;

import base.Crypto;
import base.Logger;

public class Decryption {
    private Decryption() {
    }

    public static String decryption(Crypto crypto, String text, int key) {
        if (key < 0 || key > crypto.getCryptoBaseIndex()) {
            Logger.log("Ключ не может быть меньше 0 или больше " + crypto.getCryptoBaseIndex());
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
        Logger.log("[Decryption] " + text);
    }
}
