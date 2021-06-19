package soint.messages.writer;


import org.apache.log4j.Logger;
import soint.messages.interfaces.WriterMessages;

import java.util.Optional;


public class LogMessages implements WriterMessages {
    private static final Logger LOGGER = Logger.getLogger(LogMessages.class);

    @Override
    public void logMessage(Optional<String> messageText, boolean message, boolean warning, boolean error) {

        if (message && messageText.isPresent())
            LOGGER.info(messageText.get());
        if (warning && messageText.isPresent())
            LOGGER.warn(messageText.get());
        if (error && messageText.isPresent())
            LOGGER.error(messageText.get());
    }
}
