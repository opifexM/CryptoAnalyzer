package base;

import java.util.ArrayList;
import java.util.List;

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
            System.out.print(symbol + " - " + cryptoBase.indexOf(symbol) + "   ");
        }
        System.out.println();
        System.out.println("Загружены символы алфавита " + getCryptoBaseIndex() + " шт.");
    }


}
