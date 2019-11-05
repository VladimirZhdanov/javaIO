package com.foxminded.race;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.toIntExact;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.time.temporal.ChronoUnit.YEARS;
import static java.util.Collections.sort;
import static java.util.stream.Collectors.toList;

/**
 * Calculation the result of Q1/Q2 stage race.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
public class Calculation {

    /**
     * Line separator depends on user's OS.
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Returns String of result of Q1 and Q2 stage race.
     *
     * @return - String of result of Q1 and Q2 stage race - String
     */
    public String getResult(List<Racer> racers) {
        if (racers == null) {
            throw new IllegalArgumentException("Null was passed to the method...");
        }
        StringBuilder result = new StringBuilder();

        final int[] i = {0};
        DateTimeFormatter formatter = ofPattern("mm:ss.nnn");
        racers.forEach(racer -> {
                    i[0]++;
                    if (i[0] == 16) {
                        result.append(String.format("%67s", " ").replace(" ", "-"))
                                .append(LINE_SEPARATOR);
                    }
                    result.append(String.format("%2s. %-20s | %-28s | %s%s",
                            i[0], racer.getName(), racer.getCar(), racer.getBestLapTime().format(formatter), LINE_SEPARATOR));
                }
        );
        return result.toString();
    }

    /**
     * Returns list of racers with best lap time.
     *
     * @param startLog - start log - Map
     * @param endLog - end log - Map
     * @param listOfRacers - list of racers
     * @return - list of racers with best lap time
     */
    public List<Racer> getBestLapTimeRacers(Map<String, LocalDateTime> startLog, Map<String, LocalDateTime> endLog, List<Racer> listOfRacers) {
        if (startLog == null || endLog == null || listOfRacers == null) {
            throw new IllegalArgumentException("Null was passed to the method...");
        }
        List<Racer> result;

        Map<String, LocalTime> mapBestLapTime = new HashMap<>();
        LocalTime lapTime;

        for (Map.Entry<String, LocalDateTime> valueStart : startLog.entrySet()) {
            for (Map.Entry<String, LocalDateTime> valueEnd : endLog.entrySet()) {
                if (valueStart.getKey().equals(valueEnd.getKey())) {
                    lapTime = calculateLapTime(valueStart.getValue(), valueEnd.getValue());
                    mapBestLapTime.put(valueStart.getKey(), lapTime);
                }
            }
        }

        mapBestLapTime.forEach((key, value) -> {
            for (Racer oRacer : listOfRacers) {
                if (oRacer.getAbbreviation().equals(key)) {
                    oRacer.setBestLapTime(value);
                }
            }
        });

        result = listOfRacers.stream().filter(racer -> racer.getBestLapTime() != null).collect(toList());
        sort(result);
        return result;
    }

    /**
     * Calculates lap time by given start time and end time.
     *
     * @param startTime - start time - LocalDateTime
     * @param endTime - end time - LocalDateTime
     * @return - time of the lap - LocalTime
     */
    private LocalTime calculateLapTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Null was passed to the method...");
        }
        LocalDateTime tempTime = startTime;

        long years = tempTime.until(endTime, YEARS);
        tempTime = tempTime.plusYears(years);

        long months = tempTime.until(endTime, MONTHS);
        tempTime = tempTime.plusMonths(months);

        long days = tempTime.until(endTime, DAYS);
        tempTime = tempTime.plusDays(days);

        long hours = tempTime.until(endTime, HOURS);
        tempTime = tempTime.plusHours(hours);

        long minutes = tempTime.until(endTime, MINUTES);
        tempTime = tempTime.plusMinutes(minutes);

        long seconds = tempTime.until(endTime, SECONDS);
        tempTime = tempTime.plusSeconds(seconds);

        long milliSeconds = tempTime.until(endTime, MILLIS);

        return LocalTime.of(toIntExact(hours), toIntExact(minutes), toIntExact(seconds), toIntExact(milliSeconds));
    }
}
