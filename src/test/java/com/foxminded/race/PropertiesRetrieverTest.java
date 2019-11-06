package com.foxminded.race;

import java.util.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
class PropertiesRetrieverTest {
    public static final String START_URL = "C:\\Start.log";
    public static final String START_URL_KEY = "url_start_log";
    public static final String END_URL = "C:\\End.log";
    public static final String END_URL_KEY = "url_end_log";
    public static final String ABBREVIATION_URL = "C:\\abbreviations.txt";
    public static final String ABBREVIATION_URL_KEY = "url_abbreviations";
    public PropertiesRetriever properties;

    @Mock
    Properties mockedProperties;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        properties = new PropertiesRetriever();
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNullWasPassed1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                properties.loadProperties((String) null));
        assertEquals("Null was passed to the method...", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNullWasPassed2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                properties.loadProperties((Properties) null));
        assertEquals("Null was passed to the method...", exception.getMessage());
    }

    @Test
    public void shouldReturnStartUrlWhenLoadMockedProperties() {
        when(mockedProperties.getProperty(START_URL_KEY)).thenReturn(START_URL);
        properties.loadProperties(mockedProperties);
        String actualStart = properties.getPathStartLog();

       assertEquals(START_URL, actualStart,
                        "Should return the corrected url to start.log");
    }

    @Test
    public void shouldReturnEndUrlWhenLoadMockedProperties() {
        when(mockedProperties.getProperty(END_URL_KEY)).thenReturn(END_URL);
        properties.loadProperties(mockedProperties);
        String actualEnd = properties.getPathEndLog();

        assertEquals(END_URL, actualEnd,
                        "Should return the corrected url to end.log");
    }

    @Test
    public void shouldReturnAbbreviationUrlWhenLoadMockedProperties() {
        when(mockedProperties.getProperty(ABBREVIATION_URL_KEY)).thenReturn(ABBREVIATION_URL);
        properties.loadProperties(mockedProperties);
        String actualAbbreviation = properties.getPathAbbreviations();

               assertEquals(ABBREVIATION_URL, actualAbbreviation,
                        "Should return the corrected url to abbreviation.txt");
    }
}