package soint.messages.writer;

import soint.messages.interfaces.WriterMessages;

import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleMessages implements WriterMessages {
    ConsoleHandler ch = new ConsoleHandler();
    private static Logger logger = Logger.getLogger(ConsoleMessages.class.getCanonicalName());

    @Override
    public void logMessage(Optional<String> messageText, boolean message, boolean warning, boolean error) {
        if (message && messageText.isPresent())
            logger.log(Level.INFO,messageText.get());
        if (warning && messageText.isPresent())
            logger.log(Level.WARNING,messageText.get());
        if (error && messageText.isPresent())
            logger.log(Level.SEVERE,messageText.get());
    }
}
