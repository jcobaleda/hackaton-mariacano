package co.com.rescue.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Davem
 */
public class DatabaseUtil {
    
    private static final String IP = AppConstants.IP;
    private static final String PUERTO = AppConstants.PUERTO;
    private static final String DB = AppConstants.DB;
    private static final String URL = AppConstants.URL;
    private static final String USER = AppConstants.USER;
    private static final String PASS = AppConstants.PASS;

    public DatabaseUtil() {
        /**
         * Se crea clase conexion para enlazar hacia la base de datos.
         */
    }
    
    public Connection getConexion(){
        Connection con = null;
        try{
            AppConstants.log.log(Level.INFO, "Iniciando conexion a la base de datos ");
            Class.forName(AppConstants.DRIVERCONN );
            con = (Connection) DriverManager.getConnection(URL+IP+PUERTO+DB, USER, PASS);
            AppConstants.log.log(Level.INFO, "Conexion exitosa ");
        }catch(SQLException e){
            AppConstants.log.log(Level.SEVERE, "Error en la base de datos: " + e.getMessage(), e);
        }catch(ClassNotFoundException cnfe){
            AppConstants.log.log(Level.SEVERE, "Error en la ejecucion: " + cnfe.getMessage(), cnfe);
        }
        return con;
    }
}
