package soint.messages.writer;

import java.util.HashMap;
import java.util.Optional;

public class GenericMessageWriter {

    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private static boolean logToDatabase;
    private boolean initialized;
    private static HashMap<String, String> dbParams;

    private static ConsoleMessages consoleMessages;
    private static DataBaseMessages dataBaseMessages;
    private static LogMessages logMessages;

    public GenericMessageWriter(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
                boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, HashMap dbParamsMap) {

        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
        dbParams = dbParamsMap;
    }
    public static void init() {
        consoleMessages = new ConsoleMessages();
        logMessages = new LogMessages();
        dataBaseMessages = new DataBaseMessages(dbParams);
    }

    public void LogMessage(String messageText) throws Exception {
        if (messageText == null || messageText.length() == 0) {
            return;
        }
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }
        if ((!logError && !logMessage && !logWarning)) {
            throw new Exception("Error or Warning or Message must be specified");
        }

        Optional<String> msg = Optional.ofNullable(messageText);
        init();
        if (logToFile) {
            logMessages.logMessage(msg, logMessage,logWarning, logError );
        }
        if (logToConsole) {
            consoleMessages.logMessage(msg, logMessage,logWarning, logError );
        }

        if (logToDatabase) {
            dataBaseMessages.logMessage(msg, logMessage,logWarning, logError );
        }
    }

}
