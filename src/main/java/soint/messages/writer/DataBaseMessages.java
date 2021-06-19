package soint.messages.writer;

import lombok.Cleanup;
import lombok.SneakyThrows;
import soint.messages.connection.ConnectionProvider;
import soint.messages.interfaces.WriterMessages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Level;

public class DataBaseMessages implements WriterMessages {

    HashMap<String, String> dbParamsMap;
    ConnectionProvider provider = new ConnectionProvider();

    public DataBaseMessages(HashMap<String, String> dbParamsMap) {
        this.dbParamsMap = dbParamsMap;
    }

    @Override
    @SneakyThrows
    public void logMessage(Optional<String> messageText, boolean message, boolean warning, boolean error) {
        @Cleanup Connection con = provider.getValidConnection(dbParamsMap);
        try {
            String query = new StringBuilder().append("insert into Log_Values (message, level,date) values (?, ?,?)").toString();
            @Cleanup PreparedStatement insert = con.prepareStatement(query);


            if (message && messageText.isPresent()) {
                insert.setString(1, messageText.get());
                insert.setString(2, Level.INFO.getName());
                insert.setString(3, new Date().toString());

                insert.executeUpdate();
            }
            if (warning && messageText.isPresent()) {
                insert.setString(1, messageText.get());
                insert.setString(2, Level.WARNING.getName());
                insert.setString(3, new Date().toString());
                insert.executeUpdate();
            }
            if (error && messageText.isPresent()) {
                insert.setString(1, messageText.get());
                insert.setString(2, Level.SEVERE.getName());
                insert.setString(3, new Date().toString());
                insert.executeUpdate();
            }

        } catch (SQLException se) {
            throw new IllegalArgumentException("open() failed." + se.getMessage(), se);
        } catch (Exception e) {

            throw new IllegalArgumentException(e);
        }
    }


}

