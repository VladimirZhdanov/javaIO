package com.foxminded.race;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Racer information.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
public class Racer implements Comparable<Racer> {

    /**
     * Name of a racer.
     */
    private String name;

    /**
     * Car of a racer.
     */
    private String car;

    /**
     *  Abbreviation of a racer.
     */
    private String abbreviation;

    /**
     * Best lap time of a racer.
     */
    private LocalTime bestLapTime;

    /**
     * Constructor of the class.
     *
     * @param name - name of a racer - String
     * @param car - car of a racer - String
     * @param abbreviation - abbreviation of a racer - String
     */
    public Racer(String name, String car, String abbreviation) {
        this.name = name;
        this.car = car;
        this.abbreviation = abbreviation;
    }

    /**
     * Sets best lap time.
     *
     * @param bestLapTime - best lap time - LocalTime
     */
    public void setBestLapTime(LocalTime bestLapTime) {
        if (bestLapTime == null) {
            throw new IllegalArgumentException("Null was passed to the method...");
        }
        this.bestLapTime = bestLapTime;
    }

    /**
     * Gets a name.
     *
     * @return - a name - String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a car name.
     *
     * @return - a car name - String
     */
    public String getCar() {
        return car;
    }

    /**
     * Gets a abbreviation of a racer.
     *
     * @return - a abbreviation of a racer - String
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Gets the best lap time of a racer.
     *
     * @return - best lap time of a racer - LocalTime
     */
    public LocalTime getBestLapTime() {
        return bestLapTime;
    }

    @Override
    public int compareTo(Racer otherRacer) {
        int result = 0;
        if (this.getBestLapTime().isAfter(otherRacer.getBestLapTime())) {
            result = 1;
        }
        if (this.getBestLapTime().isBefore(otherRacer.getBestLapTime())) {
            result = -1;
        }
        return result;
    }

    @Override
    public boolean equals(Object otherRacer) {
        if (this == otherRacer) {
            return true;
        }
        if (otherRacer == null || getClass() != otherRacer.getClass()) {
            return false;
        }
        Racer racer = (Racer) otherRacer;
        return Objects.equals(name, racer.name)
                && Objects.equals(car, racer.car)
                && Objects.equals(abbreviation, racer.abbreviation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, car, abbreviation);
    }
}
