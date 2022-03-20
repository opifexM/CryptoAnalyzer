package base;

public class Logger {
    private Logger() {
    }

    public static void log(String text) {
        System.out.println(text);
    }

    public static void logSameLine(String text) {
        System.out.print(text);
    }
}
