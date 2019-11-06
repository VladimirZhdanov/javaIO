package com.foxminded.race;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
class CalculationTest {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public Calculation calculation;
    public List<Racer> listOfRacers;
    public Racer racerOne;
    public Racer racerTwo;
    public Racer racerThree;
    public Map<String, LocalDateTime> startLog;
    public Map<String, LocalDateTime> endLog;

    @BeforeEach
    public void setUp() {
        calculation = new Calculation();

        racerOne = new Racer("Bill", "Lada", "BL");
        racerTwo = new Racer("Nil", "BMW", "NB");
        racerThree = new Racer("Vlad", "Tesla", "VT");
        listOfRacers = Arrays.asList(racerOne, racerTwo, racerThree);

        startLog = new TreeMap<>();
        endLog = new TreeMap<>();
        startLog.put("BL", LocalDateTime.of(2019, 1, 1, 0, 0, 0, 0));
        endLog.put("BL", LocalDateTime.of(2019, 1, 1, 0, 7, 8, 666));
        startLog.put("NB", LocalDateTime.of(2019, 1, 1, 0, 3, 0, 0));
        endLog.put("NB", LocalDateTime.of(2019, 1, 1, 0, 8, 7, 86));
        startLog.put("VT", LocalDateTime.of(2019, 1, 1, 0, 45, 5, 0));
        endLog.put("VT", LocalDateTime.of(2019, 1, 1, 0, 58, 8, 636));

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNullWasPassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculation.getBestLapTimeRacers(null, null, null));
        assertEquals("Null was passed to the method...", exception.getMessage());
    }

    @Test
    public void shouldReturnSortedStringWith3LinesWhenListOfRacersWithBestLapTimeWasPassed() {
        StringBuilder expected = new StringBuilder();
        expected.append(" 1. Nil                  | BMW                          | 05:07.000")
                .append(LINE_SEPARATOR)
                .append(" 2. Bill                 | Lada                         | 07:08.000")
                .append(LINE_SEPARATOR)
                .append(" 3. Vlad                 | Tesla                        | 13:03.000")
                .append(LINE_SEPARATOR);

        List<Racer> listOfRacersWithBestLapTime = calculation.getBestLapTimeRacers(startLog, endLog, listOfRacers);
        String actual = calculation.getResult(listOfRacersWithBestLapTime);

        assertEquals(expected.toString(), actual,
                "Should return a sorted list of racers (3 lines) as String");
    }
}