package soint.messages.interfaces;

import java.util.Optional;

public interface WriterMessages {

   void logMessage(Optional<String> messageText, boolean message, boolean warning, boolean error);
}
