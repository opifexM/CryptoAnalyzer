package commands;

import base.Crypto;

import java.util.*;

public class Analyser {
    public static int analyzer(Crypto crypto, String enText) {
        System.out.println("[Analyzer  ] Начало анализа.");
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

        System.out.println("[Analyzer  ] Возможный пробел символ [" + popularSymbol + "]. Встречался " + topIndex + " раз.");
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
        System.out.println("[Analyzer  ] Cимвол [пробел] имеет ключ " + spaceSymbolKey);
        System.out.println("[Analyzer  ] Cимвол [" + popularSymbol + "] имеет ключ " + popularSymbolKey);
        int tryKey;
        if (spaceSymbolKey + popularSymbolKey > crypto.getCryptoBaseIndex()) {
            tryKey = spaceSymbolKey + popularSymbolKey - crypto.getCryptoBaseIndex() + 2;
        } else {
            tryKey = spaceSymbolKey + popularSymbolKey;
        }
        System.out.println("[Analyzer  ] Возможный ключ сдвига: " + tryKey);
        System.out.println("[Analyzer  ] Конец анализа.");
        return tryKey;
    }

    public static void printAnalyse(int key) {
        System.out.println("[Analyzer  ] Попытка дешифровки с ключем (" + key + ")");
    }

}
