package app.utils;

import java.io.FileWriter;

public class Logger {
    private static final String LOG_FILE_PATH = "src/main/resources/logs/logs.txt";

    private Logger(){}

    private static void writeInLogFile(String log) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true)){
            fileWriter.append(String.format("%s%n", log));
        }
        catch (Exception e) {
            exception(e);
        }
    }

    public static void exception(Exception exception){
        Logger.writeInLogFile(exception.getMessage());
    }
    public static  void log(String log) { Logger.writeInLogFile(log); }
}
