package kala.alarm.client;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.omg.CORBA.StringHolder;

/**
 * Internal class used to read configuration files. Not part of the public API.
 */
class ConfigReader {
    private final String filename;

    public ConfigReader(String filename) {
        this.filename = filename;
    }

    public String readString(String key, String defaultValue) {
        String value = readProperty(key);
        return value != null ? value : defaultValue;
    }

    public int readInt(String key, int defaultValue) {
        Integer value = Integer.valueOf(readProperty(key));
        return value != null ? value : defaultValue;
    }

    private String readProperty(String key) {
        String value = null;
        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration(filename);
            value = (String) configuration.getProperty(key);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return value;
    }
}
