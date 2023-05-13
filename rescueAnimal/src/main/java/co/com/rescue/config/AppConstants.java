package co.com.rescue.config;

import java.util.logging.Logger;

public class AppConstants {
    
    public static final PropertiesLoader props = PropertiesLoader.getInstance();
    public static final Logger log = LoggerUtil.getLogger("Rescue.log");
    
    public static final String RUTALOGS = "C:\\xampp\\tomcat\\logs\\";
    public static final String RUTA_PROP = "C:\\xampp\\tomcat\\conf\\props\\Rescues.properties";
    
    public static final String SQL_FACTURAS_NO_DIAN = props.getProperty("SQL_FACTURAS_NO_DIAN");
    
    public static final String IP = props.getProperty("IP","localhost:");
    public static final String PUERTO = props.getProperty("PUERTO","3306/");
    public static final String DB = props.getProperty("DB","rescue");
    public static final String URL = props.getProperty("URL","jdbc:mysql://");
    public static final String USER = props.getProperty("USER","root");
    public static final String PASS = props.getProperty("PASS","");
    public static final String DRIVERCONN = "com.mysql.cj.jdbc.Driver";
    
    public static final String STR_MENSAJE = "strMensaje";
    public static final String STR_TIPO = "strTipo";
    public static final String STR_TIPO_SUCCESS = "success";
    public static final String STR_TIPO_ERROR = "error";
    
}
