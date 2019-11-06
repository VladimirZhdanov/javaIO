package com.foxminded.race;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
class RacerTest {
    public Racer racer;
    public static final String NAME = "John";
    public static final String CAR = "LADA";
    public static final String ABBREVIATION = "JL";
    public static final LocalTime BEST_LAP_TIME = LocalTime.of(6, 6, 6);

    @BeforeEach
    public void setUp() {
        racer = new Racer(NAME, CAR, ABBREVIATION);
        racer.setBestLapTime(BEST_LAP_TIME);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNullWasPassed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                racer.setBestLapTime(null));
        assertEquals("Null was passed to the method...", exception.getMessage());
    }

    @Test
    public void shouldReturnCurrentNameWhenCallGetName() {
        String actual = racer.getName();
        assertEquals(NAME, actual,
                "Should return current name");
    }

    @Test
    public void shouldReturnCurrentCarNameWhenCallGetCar() {
        String actual = racer.getCar();
        assertEquals(CAR, actual,
                "Should return current car name");
    }

    @Test
    public void shouldReturnCurrentAbbreviationNameWhenCallGetAbbreviation() {
        String actual = racer.getAbbreviation();
        assertEquals(ABBREVIATION, actual,
                "Should return current Abbreviation");
    }

    @Test
    public void shouldReturnCurrentLocalTimeNameWhenCallGetBestLapTime() {
        LocalTime actual = racer.getBestLapTime();
        assertEquals(BEST_LAP_TIME, actual,
                "Should return current best lap time");
    }
}