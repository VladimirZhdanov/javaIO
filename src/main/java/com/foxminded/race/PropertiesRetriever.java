package com.foxminded.race;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads properties for Calculation class.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
public class PropertiesRetriever {

    /**
     * Path to start log.
     */
    private String pathStartLog;

    /**
     * Path to end log.
     */
    private String pathEndLog;

    /**
     * Path to abbreviation of racers.
     */
    private String pathAbbreviations;

    /**
     * Path to where properties file is placed.
     */
    private static final String PROPERTIES_PATH = "src\\main\\resources\\config.properties";

    /**
     * Load properties.
     *
     * @throws IOException - constructs an IOException with the specified detail message
     */
    public void loadProperties() throws IOException {
        loadProperties(PROPERTIES_PATH);
    }

    /**
     * Load properties with specific path.
     *
     * @param path - path to a properties file
     * @throws IOException - constructs an IOException with the specified detail message
     */
    public void loadProperties(String path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Null was passed to the method...");
        }
        try (FileInputStream fis = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(fis);
            loadProperties(properties);
        }
    }

    /**
     * Load properties with given properties.
     *
     * @param properties - properties
     */
    public void loadProperties(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Null was passed to the method...");
        }
        pathStartLog = properties.getProperty("url_start_log");
        pathEndLog = properties.getProperty("url_end_log");
        pathAbbreviations = properties.getProperty("url_abbreviations");
    }

    /**
     * Gets path to start log.
     *
     * @return path to start log - String
     */
    public String getPathStartLog() {
        return pathStartLog;
    }

    /**
     * Gets path to end log.
     *
     * @return path to end log - String
     */
    public String getPathEndLog() {
        return pathEndLog;
    }

    /**
     * Gets path to abbreviations.
     *
     * @return path to abbreviations - String
     */
    public String getPathAbbreviations() {
        return pathAbbreviations;
    }
}
