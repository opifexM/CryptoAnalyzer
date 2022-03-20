package base;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Crypto {
    private final List<Character> cryptoBase = new ArrayList<>();

    public List<Character> getCryptoBase() {
        return cryptoBase;
    }

    public int getCryptoBaseIndex() {
        return cryptoBase.size();
    }

    public void loadAlphabet(String alphabet) {
        for (char symbol : alphabet.toCharArray()) {
            cryptoBase.add(symbol);
        }
    }

    public void printAllAlphabet() {
        for (Character symbol : cryptoBase) {
            Logger.logSameLine(symbol + " - " + cryptoBase.indexOf(symbol) + "   ");
        }
        Logger.log("");
        Logger.log("Загружены символы алфавита " + getCryptoBaseIndex() + " шт.");
    }

    public int getKey(Scanner scanner) {
        Logger.log("Введите ключ для шифровки (от 1 до " + getCryptoBaseIndex() + "):");
        String input = scanner.nextLine();
        if (!NumberUtils.isDigits(input)) {
            Logger.log("[ERROR] Неправильный ввод ключа.");
            return 0;
        }
        int key = Integer.parseInt(input);
        if (key < 0 || key > getCryptoBaseIndex()) {
            Logger.log("[ERROR] Ключ не может быть меньше 0 или больше " + getCryptoBaseIndex());
            return 0;
        }
        return key;
    }
}
