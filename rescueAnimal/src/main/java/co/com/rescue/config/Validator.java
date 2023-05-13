package co.com.rescue.config;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

/**
 *
 * @author Davem
 */
public class Validator {
    
    public static Boolean validateIntenetConnection(){
        Boolean response = Boolean.FALSE;
        try {
            URL ruta=new URL("http://www.google.com");
            URLConnection rutaC=ruta.openConnection();
            rutaC.connect();
            response=Boolean.TRUE;
            AppConstants.log.log(Level.INFO, "Despues de validar la conexion a internet: ");
            AppConstants.log.log(Level.INFO, response.toString());
        } catch (IOException e) {
            AppConstants.log.log(Level.SEVERE, "Error en la comprobacion: "+e.getMessage(),e);
        }
        return response;
    }
    
     public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(input.getBytes());
            BigInteger numero = new BigInteger(1, encBytes);
            String encString = numero.toString(16);
            while (encString.length() < 32) {
                encString = "0" + encString;
            }
            return encString;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
