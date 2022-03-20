import base.Crypto;
import base.Logger;
import base.Utils;
import commands.Analyser;
import commands.BruteForce;
import commands.Decryption;
import commands.Encryption;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

import static dictionaries.Constants.*;

public class Main {
    public static void main(String[] args) {
        Crypto crypto = new Crypto();
        crypto.loadAlphabet(EN_ALPHABET);
        crypto.loadAlphabet(RU_ALPHABET);
        crypto.loadAlphabet(DIGITS_ALPHABET);
        crypto.loadAlphabet(SYMBOL_ALPHABET);
        crypto.printAllAlphabet();

        int key = 0;
        Scanner scanner = new Scanner(System.in);

        String textForEncryption = Utils.loadFile(FILE_FOR_ENCRYPTION);
        if (textForEncryption.isEmpty()) {
            Logger.log("[ERROR] Файл для шифрования не загружен. Завершение работы программы.");
        }

        while (true) {
            if (key == 0) {
                key = crypto.getKey(scanner);
                continue;
            }
            Logger.log("----------------------------------------------------------------");
            Logger.log(" Программа Криптоанализатор Шифр Цезаря");
            Logger.log("      Секретный ключ: [" + key + "]");
            Logger.log("[1] - Шифрование Шифром Цезаря");
            Logger.log("[2] - Расшифровка через секретный ключ");
            Logger.log("[3] - Расшифровка перебором словаря");
            Logger.log("[4] - Расшифровка через анализ текста");
            Logger.log("[0] - Выход");
            Logger.log("Выберите действие: ");

            String input = scanner.nextLine();
            if (input.equals("0")) {
                Logger.log("Завершение работы.");
                break;
            }
            if (!NumberUtils.isDigits(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) > 4) {
                Logger.log("[ERROR] Неправильный ввод комманды.");
                continue;
            }

            Utils.printOriginText(Utils.cutString(textForEncryption), key);
            String enText = Encryption.encryption(crypto, textForEncryption, key);
            Encryption.printEncryptionText(Utils.cutString(enText));

            switch (input) {
                case "1" -> Utils.saveToFile(enText, FILE_FOR_DECRYPTION);
                case "2" -> {
                    String deText = Decryption.decryption(crypto, enText, key);
                    Decryption.printDecryptionText(Utils.cutString(deText));
                }
                case "3" -> {
                    int bruteForceKey = BruteForce.bruteForce(crypto, enText);
                    BruteForce.printBruteForce(bruteForceKey);
                    String deText = Decryption.decryption(crypto, enText, bruteForceKey);
                    Decryption.printDecryptionText(Utils.cutString(deText));
                }
                case "4" -> {
                    int analyserKey = Analyser.analyzer(crypto, enText);
                    Analyser.printAnalyse(analyserKey);
                    String deText = Decryption.decryption(crypto, enText, analyserKey);
                    Decryption.printDecryptionText(Utils.cutString(deText));
                }
                default -> Logger.log("[ERROR] Неправильный ввод комманды.");
            }
            Logger.log("");
            Logger.log("Нажмите клавишу для продолжения...");
            scanner.nextLine();
        }
    }
}
