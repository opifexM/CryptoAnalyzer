package commands;

import base.Crypto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dictionaries.Constants.BRUTE_FORCE_DICTIONARY;

public class BruteForce {
    private static final List<String> wordsDB = new ArrayList<>();

    public static int bruteForce(Crypto crypto, String enText) {
        loadWordsDB();
        System.out.println("[BruteForce] Начало перебора. Ключи от 0 до " + crypto.getCryptoBaseIndex() + ".");

        int bruteForceKey = 0;
        int bruteForceKeyGoodIndex = 0;
        System.out.print("");
        for (int key = 0; key <= crypto.getCryptoBaseIndex(); key++) {
            int currentGoodIndex = 0;
            String tryDecryption = Decryption.decryption(crypto, enText, key);
            String[] arrDecryption = tryDecryption.split(" ");
            for (String wordDecryption : arrDecryption) {
                for (String wordFromDB : wordsDB) {
                    if (wordFromDB.equals(wordDecryption)) {
                        currentGoodIndex++;
                    }
                }
            }
            if (currentGoodIndex > 0) {
                if (bruteForceKeyGoodIndex < currentGoodIndex) {
                    bruteForceKeyGoodIndex = currentGoodIndex;
                    bruteForceKey = key;
                }
                System.out.println();
                System.out.println("[BruteForce] При ключе (" + key + ") найдено " + currentGoodIndex + " совпадений со словарем.");
                System.out.print("");
            }
            System.out.print("+");
        }
        System.out.println();
        System.out.println("[BruteForce] Конец перебора.");
        return bruteForceKey;
    }

    private static void loadWordsDB() {
        try (BufferedReader bruteForceDataBase = new BufferedReader(new FileReader(BRUTE_FORCE_DICTIONARY))) {
            String s;
            int index = 0;
            while ((s = bruteForceDataBase.readLine()) != null) {
                wordsDB.add(s);
                index++;
            }
            System.out.println("[BruteForce] Загружено " + index + " слов в словарь перебора.");
        } catch (IOException ex) {
            System.out.println("[ERROR] Ошибка загрузки словаря для перебора " + ex.getMessage());
        }
    }

    public static void printBruteForce(int key) {
        System.out.println("[BruteForce] Попытка дешифровки с ключем (" + key + ")");
    }
}
