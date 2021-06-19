package soint.messages;

import org.apache.commons.cli.*;
import org.apache.log4j.PropertyConfigurator;
import soint.messages.interfaces.JDBCArgsKeys;
import soint.messages.writer.GenericMessageWriter;

import java.util.HashMap;

public class Demo {

    public static void main(String[] args) throws Exception {

        Options options = new Options();
        Option msg = new Option("msg", "msg", true, "Mensaje a escribir");
        msg.setRequired(true);
        options.addOption(msg);

        Option logToFileParam = new Option("logToFileParam", "logToFileParam", true, "Log en fichero");
        logToFileParam.setRequired(true);
        options.addOption(logToFileParam);

        Option logToConsoleParam = new Option("logToConsoleParam", "logToConsoleParam", true, "Log en consola");
        logToConsoleParam.setRequired(true);
        options.addOption(logToConsoleParam);

        Option logToDatabaseParam = new Option("logToDatabaseParam", "logToDatabaseParam", true, "Log en base de datos");
        logToDatabaseParam.setRequired(true);
        options.addOption(logToDatabaseParam);

        Option logMessageParam = new Option("logMessageParam", "logMessageParam", true, "Texto es mensaje");
        logMessageParam.setRequired(true);
        options.addOption(logMessageParam);

        Option logWarningParam = new Option("logWarningParam", "logWarningParam", true, "Texto es advertencia");
        logWarningParam.setRequired(true);
        options.addOption(logWarningParam);

        Option logErrorParam = new Option("logErrorParam", "logErrorParam", true, "Texto es error");
        logErrorParam.setRequired(true);
        options.addOption(logErrorParam);


        Option driver = new Option("driver", "driver", true, "Ip JDBC Server");
        driver.setRequired(false);
        options.addOption(driver);

        Option host = new Option("host", "host", true, "Ip JDBC Server");
        host.setRequired(false);
        options.addOption(host);

        Option port = new Option("port", "port", true, "Puerto JDBC Server");
        port.setRequired(true);
        options.addOption(port);

        Option dbname = new Option("dbname", "dbname", true, "Nombre de la Base de Datos");
        dbname.setRequired(false);
        options.addOption(dbname);

        Option username = new Option("username", "username", true, "Usuario JDBC");
        username.setRequired(true);
        options.addOption(username);

        Option password = new Option("password", "password", true, "Contraseña JDBC");
        password.setRequired(false);
        options.addOption(password);

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Opciones de Mensaje ", options);
            System.exit(1);
            return;
        }

        Boolean logErrorParam1 = Boolean.parseBoolean(cmd.getOptionValue("logErrorParam"));
        Boolean logMessageParam1 = Boolean.parseBoolean(cmd.getOptionValue("logMessageParam"));
        Boolean logWarningParam1 = Boolean.parseBoolean(cmd.getOptionValue("logWarningParam"));
        Boolean logToDatabaseParam1 = Boolean.parseBoolean(cmd.getOptionValue("logToDatabaseParam"));
        Boolean logToFileParam1 = Boolean.parseBoolean(cmd.getOptionValue("logToFileParam"));
        Boolean logToConsoleParam1 = Boolean.parseBoolean(cmd.getOptionValue("logToConsoleParam"));

        HashMap<String, String> dbParamsMap = new HashMap<>();

        String driver1 = cmd.getOptionValue("driver");
        String host1 = cmd.getOptionValue("host");
        String port1 = cmd.getOptionValue("port");
        String dbname1 = cmd.getOptionValue("dbname");
        String user1 = cmd.getOptionValue("username");
        String pass1 = cmd.getOptionValue("password");

        dbParamsMap.put(JDBCArgsKeys.DRIVERS, driver1);

        dbParamsMap.put(JDBCArgsKeys.HOST, host1);
        dbParamsMap.put(JDBCArgsKeys.PORT, port1);
        dbParamsMap.put(JDBCArgsKeys.DB_NAME, dbname1);
        dbParamsMap.put(JDBCArgsKeys.USER, user1);
        dbParamsMap.put(JDBCArgsKeys.PASS, pass1);
        GenericMessageWriter demo = new GenericMessageWriter(logToFileParam1, logToConsoleParam1, logToDatabaseParam1, logMessageParam1, logWarningParam1, logErrorParam1, dbParamsMap);
        String msg1 = cmd.getOptionValue("msg");
        demo.LogMessage(msg1);

    }


}

