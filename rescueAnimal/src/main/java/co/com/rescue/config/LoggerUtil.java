package co.com.rescue.config;

import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Utilitario que permite estandarizar el logging 
 * @author  Equipo tecnico Amazonas
 */
public class LoggerUtil{
    
    public static Logger getLogger(String loggerName) {
        Hashtable<String, Logger> loggers = new Hashtable();
        Logger logger = Logger.getLogger(loggerName);
        Handler consoleHandler = new ConsoleHandler(); //Estableceremos un manejador de errores
        Handler fileHandler = null;
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        try {
            
            if (fileHandler == null) {
                boolean append = true;
                fileHandler = new FileHandler(AppConstants.RUTALOGS+loggerName, append);
                fileHandler.setFormatter(simpleFormatter);
            }
            
            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            consoleHandler.setLevel(Level.ALL); //Este comando comenzará el manejador de errores
            fileHandler.setLevel(Level.ALL); //Este comando se ejecuta para comenzar el proceso de registro del logger
            // En ambos comando se debe colocar el nivel de registro de errores en el log por ende se colocar Level.ALL
            loggers.put(loggerName, logger);
        } catch (IOException e) {
            AppConstants.log.log(Level.SEVERE, "Error iniciando Logs: "+e.getMessage(), e);
        }
        return logger;
    }
}