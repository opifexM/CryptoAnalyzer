package commands;

import base.Crypto;
import base.Logger;

import java.util.*;

public class Analyser {
    private Analyser() {
    }

    public static int analyzer(Crypto crypto, String enText) {
        Logger.log("[Analyzer  ] Начало анализа.");
        Map<Character, Integer> numbersOfCharacter = new HashMap<>();

        for (int i = 0; i < enText.length(); i++) {
            char symbol = enText.charAt(i);
            if (numbersOfCharacter.containsKey(symbol)) {
                numbersOfCharacter.put(symbol, numbersOfCharacter.get(symbol) + 1);
            } else {
                numbersOfCharacter.put(symbol, 1);
            }
        }
        char popularSymbol = 0;
        int topIndex = 0;
        for (Map.Entry<Character, Integer> item : numbersOfCharacter.entrySet()) {
            if (item.getValue() > topIndex) {
                topIndex = item.getValue();
                popularSymbol = item.getKey();
            }
        }

        Logger.log("[Analyzer  ] Возможный пробел символ [" + popularSymbol + "]. Встречался " + topIndex + " раз.");
        int popularSymbolKey = 0;
        int spaceSymbolKey = 0;
        int key = 0;
        for (Character symbol : crypto.getCryptoBase()) {
            if (symbol == popularSymbol) {
                popularSymbolKey = key;
            }
            if (symbol == ' ') {
                spaceSymbolKey = key;
            }
            key++;
        }
        Logger.log("[Analyzer  ] Символ [пробел] имеет ключ " + spaceSymbolKey);
        Logger.log("[Analyzer  ] Символ [" + popularSymbol + "] имеет ключ " + popularSymbolKey);
        int tryKey;
        if (spaceSymbolKey + popularSymbolKey > crypto.getCryptoBaseIndex()) {
            tryKey = spaceSymbolKey + popularSymbolKey - crypto.getCryptoBaseIndex() + 2;
        } else {
            tryKey = spaceSymbolKey + popularSymbolKey;
        }
        Logger.log("[Analyzer  ] Возможный ключ сдвига: " + tryKey);
        Logger.log("[Analyzer  ] Конец анализа.");
        return tryKey;
    }

    public static void printAnalyse(int key) {
        Logger.log("[Analyzer  ] Попытка дешифровки с ключем (" + key + ")");
    }

}
