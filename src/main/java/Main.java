import base.Crypto;
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

        Scanner scanner = new Scanner(System.in);
        int key = 0;
        String input;

        String textForEncryption = Utils.loadFile(FILE_FOR_ENCRYPTION);
        while (!textForEncryption.isEmpty()) {
            System.out.println("----------------------------------------------------------------");
            if (key == 0) {
                System.out.println("Введите ключ для шифровки (от 1 до " + crypto.getCryptoBaseIndex() + "):");
                input = scanner.nextLine();
                if (!NumberUtils.isDigits(input)) {
                    System.out.println("Неправильный ввод ключа.");
                    continue;
                }
                if (Integer.parseInt(input) < 0 || Integer.parseInt(input) > crypto.getCryptoBaseIndex()) {
                    System.out.println("Ключ не может быть меньше 0 или больше " + crypto.getCryptoBaseIndex());
                    continue;
                }
                key = Integer.parseInt(input);
            } else {
                System.out.println(" Программа Криптоанализатор Шифр Цезаря");
                System.out.println("      Секретный ключ: [" + key + "]");
                System.out.println("[1] - Шифрование Шифром Цезаря");
                System.out.println("[2] - Расшифровка через секретный ключ");
                System.out.println("[3] - Расшифровка перебором словаря");
                System.out.println("[4] - Расшифровка через анализ текста");
                System.out.println("[0] - Выход");
                System.out.println("Выберите действие: ");

                input = scanner.nextLine();
                if ("0".equals(input)) {
                    System.out.println("Завершение работы.");
                    break;
                } else if ("1".equals(input)) {
                    Utils.printOriginText(Utils.cutString(textForEncryption), key);

                    String enText = Encryption.encryption(crypto, textForEncryption, key);
                    Utils.saveToFile(enText, FILE_FOR_DECRYPTION);
                    Encryption.printEncryptionText(Utils.cutString(enText));

                } else if ("2".equals(input)) {
                    Utils.printOriginText(Utils.cutString(textForEncryption), key);

                    String enText = Encryption.encryption(crypto, textForEncryption, key);
                    Encryption.printEncryptionText(Utils.cutString(enText));

                    String deText = Decryption.decryption(crypto, enText, key);
                    Decryption.printDecryptionText(Utils.cutString(deText));

                } else if ("3".equals(input)) {
                    Utils.printOriginText(Utils.cutString(textForEncryption), key);

                    String enText = Encryption.encryption(crypto, textForEncryption, key);
                    Encryption.printEncryptionText(Utils.cutString(enText));

                    int bruteForceKey = BruteForce.bruteForce(crypto, enText);
                    BruteForce.printBruteForce(bruteForceKey);

                    String deText = Decryption.decryption(crypto, enText, bruteForceKey);
                    Decryption.printDecryptionText(Utils.cutString(deText));

                } else if ("4".equals(input)) {
                    Utils.printOriginText(Utils.cutString(textForEncryption), key);

                    String enText = Encryption.encryption(crypto, textForEncryption, key);
                    Encryption.printEncryptionText(Utils.cutString(enText));

                    int analyserKey = Analyser.analyzer(crypto, enText);
                    Analyser.printAnalyse(analyserKey);

                    String deText = Decryption.decryption(crypto, enText, analyserKey);
                    Decryption.printDecryptionText(Utils.cutString(deText));
                }
                System.out.println();
                System.out.println("Нажмите клавишу для продолжения...");
                scanner.nextLine();
            }
        }
    }
}
