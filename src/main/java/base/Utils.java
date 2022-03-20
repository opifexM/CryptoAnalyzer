package base;

import java.io.*;

public class Utils {

    private Utils() {
    }

    public static String loadFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bruteForceDataBase = new BufferedReader(new FileReader(fileName))) {
            String s;
            int index = 0;
            while ((s = bruteForceDataBase.readLine()) != null) {
                stringBuilder.append(s).append("\n");
                index++;
            }
            Logger.log("Загружен файл (" + fileName + ") для шифрования " + index + " строк.");
        } catch (IOException ex) {
            Logger.log("[ERROR] Ошибка загрузки файла для шифрования " + ex.getMessage());
        }
        return stringBuilder.toString();
    }

    public static void printOriginText(String text, int key) {
        Logger.log("");
        Logger.log("[Orig. text] " + text);
        Logger.log("[Orig. text] Ключ шифроваия: (" + key + ")");
    }

    public static String cutString(String text) {
        int maxIndex = Math.min(text.length(), 300);
        return text.substring(0, maxIndex);
    }

    public static void saveToFile(String text, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(text);
            Logger.log("Записан зашифрованный файл (" + fileName + ").");
        } catch (IOException ex) {
            Logger.log("[ERROR] Ошибка записи зашифрованного файла" + ex.getMessage());
        }
    }
}
