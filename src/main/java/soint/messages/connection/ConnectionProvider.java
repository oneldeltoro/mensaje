package soint.messages.connection;


import soint.messages.interfaces.JDBCArgsKeys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class ConnectionProvider {


    private static final int VALIDITY_CHECK_TIMEOUT_S = 5;

    private Connection connection;

    public ConnectionProvider() {
        this.connection = null;
    }

    public Connection getValidConnection(HashMap<String, String> dbParamsMap) throws SQLException, ClassNotFoundException {

        if (connection == null) {
            newConnection(dbParamsMap);
        } else if (!connection.isValid(VALIDITY_CHECK_TIMEOUT_S)) {

            closeQuietly();
            newConnection(dbParamsMap);
        }

        return connection;
    }


    private void newConnection(HashMap<String, String> dbParamsMap) throws ClassNotFoundException, SQLException {
        String url = getURLBase(dbParamsMap);
        String username = dbParamsMap.get(JDBCArgsKeys.USER);
        String password = dbParamsMap.get(JDBCArgsKeys.PASS);
        if (dbParamsMap.containsValue("oracle")) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        connection = DriverManager.getConnection(url, username, password);
        onConnect(connection);
    }

    public synchronized void closeQuietly() {

        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException sqle) {
                System.out.println(sqle);
            }
        }
    }

    protected void onConnect(Connection connection) throws SQLException {
        ResultSet tables = connection.getMetaData().getTableTypes();
        if (tables != null) {
            System.out.println("The database connection is valid...");
        }
    }

    public static String getURLBase(HashMap<String, String> dbParamsMap) {
        String base = null;

        String driver = dbParamsMap.get(JDBCArgsKeys.DRIVERS);
        String host = dbParamsMap.get(JDBCArgsKeys.HOST);
        String port = dbParamsMap.get(JDBCArgsKeys.PORT);
        String dbName = dbParamsMap.get(JDBCArgsKeys.DB_NAME);
        switch (driver) {
            case "postgresql":
                base = new StringBuilder().append("jdbc:postgresql://").append(host).append(":")
                        .append(port).append("/").append(dbName).toString();
                break;
            case "oracle":
                base = new StringBuilder().append("jdbc:oracle:thin:@").append(host).append(":")
                        .append(port).append("/").append(dbName).toString();
                break;

            case "mysql":
                base = new StringBuilder().append("jdbc:mysql://").append(host).append(":")
                        .append(port).append("/").append(dbName).toString();
                break;
            default:
                throw new IllegalArgumentException("Driver no soportado");

        }
        return base;

    }

}
