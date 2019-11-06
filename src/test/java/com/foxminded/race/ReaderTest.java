package com.foxminded.race;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static java.lang.String.format;
import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
class ReaderTest {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String TEST_TEXT_FIRST = "first text";
    public static final String TEST_TEXT_SECOND = "second text";
    public static final String NB = "NB";
    public static final String BL = "BL";
    public static final String BL_NAME_CAR = "Bill_Lada";
    public static final String NB_NAME_CAR = "Nil_BMW";
    public static final String BL_TIME = "2018-05-24_12:02:58.917";
    public static final String NB_TIME = "2018-05-24_12:02:49.914";
    public Reader reader;
    public Racer racerOne;
    public Racer racerTwo;
    Path output;

    @TempDir
    public static Path tempDir;

    @BeforeEach
    public void setUp() {
        reader = new Reader();
        racerOne = new Racer("Bill", "Lada", "BL");
        racerTwo = new Racer("Nil", "BMW", "NB");
        output = tempDir.resolve("Start.log");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNullWasPassed1() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                reader.readAbbreviationsFile(null));
        assertEquals("Null was passed to the method...", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNullWasPassed2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                reader.convertTextLog(null));
        assertEquals("Null was passed to the method...", exception.getMessage());
    }

    @Test
    public void shouldReturnTrueWhenWeWriteToTestFile() throws IOException {
        Files.writeString(output, TEST_TEXT_FIRST);
        assertTrue(Files.exists(output));
    }

    @Test
    public void shouldReturnCorrectedDataWhenWeCreateTestFileWithDate() throws IOException {
        Files.writeString(output, TEST_TEXT_FIRST + LINE_SEPARATOR + TEST_TEXT_SECOND);
        assertLinesMatch(List.of(TEST_TEXT_FIRST, TEST_TEXT_SECOND), Files.readAllLines(output));
    }

    @Test
    public void shouldReturnsListOfRacerWhenReadTxtFile() throws IOException {
        Files.writeString(output, format("%s_%s%s%s_%s", BL, BL_NAME_CAR, LINE_SEPARATOR, NB, NB_NAME_CAR));
        List<Racer> actual = reader.readAbbreviationsFile(output.toString());
        List<Racer> expected = Arrays.asList(racerOne, racerTwo);

        assertEquals(expected.get(0), actual.get(0),
                "Should return a instance of Racer class");
    }

    @Test
    public void shouldReturnsMapWhenReadLogFile() throws IOException {
        DateTimeFormatter inputFormatter = ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        Files.writeString(output, format("%s%s%s%s%s", BL, BL_TIME, LINE_SEPARATOR, NB, NB_TIME));
        Map<String, LocalDateTime> actual = reader.convertTextLog(output.toString());
        Map<String, LocalDateTime> expected = new TreeMap<>();
        expected.put(BL, parse(BL_TIME, inputFormatter));
        expected.put(NB, parse(NB_TIME, inputFormatter));

        assertEquals(expected.get(NB), actual.get(NB),
                "Should return the same LocalDateTime as expected");
    }
}