package co.com.rescue.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;

public class PropertiesLoader {

    public static final String VDEFAULT = " vDefalult=";
    private static final String CONFIGURATION_FILE = AppConstants.RUTA_PROP;

    public PropertiesLoader() {
        load();
    }

    public static PropertiesLoader getInstance() {
        if (instance == null)
			synchronized (PropertiesLoader.class) {
            instance = new PropertiesLoader();
        }
        return instance;
    }

    public Properties load() {
        FileInputStream f = null;
        try {
            f = new FileInputStream(CONFIGURATION_FILE);
            properties.load(f);
        } catch (IOException e) {
            AppConstants.log.log(Level.SEVERE, "error cargando archivo de propiedades {0}", e.getMessage());

        } finally {
            try {
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                AppConstants.log.log(Level.SEVERE, "error cargando archivo de propiedades {0}", e.getMessage());
            }
        }
        return properties;
    }

    public String getProperty(String prop) {
        String value = properties.getProperty(prop);
        if (value == null) {
            AppConstants.log.log(Level.SEVERE, "[util][PropertiesLoader][getProperty]ERROR propiedad NULA {0}", prop);
        } else {
            value = value.trim();
        }
        return value;
    }

    public String getProperty(String prop, String vDefalult) {
        AppConstants.log.log(Level.INFO, "[util][PropertiesLoader][getProperty] prop=" + VDEFAULT + "{1}", new Object[]{prop, vDefalult});
        String value = "";
        try {
            value = properties.getProperty(prop, vDefalult);
            if (value == null) {
                AppConstants.log.log(Level.SEVERE, "[util][PropertiesLoader][getProperty] ERROR propiedad NULA {0}", prop);
                AppConstants.log.log(Level.SEVERE, "Error Asignando variable por defecto a la propiedad " + VDEFAULT + "{1}", new Object[]{prop, vDefalult});
            }
        } catch (Exception e) {
            AppConstants.log.log(Level.SEVERE, "[util][PropertiesLoader][getProperty] ERROR propiedad NULA {0} {1}", new Object[]{prop, e});
            AppConstants.log.log(Level.SEVERE, " [util][PropertiesLoader][getProperty] Error Asignando variable por defecto a la propiedad " + VDEFAULT + "{1}", new Object[]{prop, vDefalult});
        }
        return value;

    }

    public Enumeration getPropertyNames() {
        return properties.propertyNames();
    }

    public void putProperty(String key, String value) {
        properties.put(key, value);
    }

    public Properties getProperties() {
        return properties;
    }

    private static PropertiesLoader instance;
    private static Properties properties = new Properties();
}
