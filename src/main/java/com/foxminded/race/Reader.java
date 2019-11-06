package com.foxminded.race;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
public class Reader {

    /**
     * Reads abbreviations.txt amd returns List of racers with name, car, abbreviation.
     *
     * @param fileName - a name of the file
     * @return - List of racers - List<Racer>
     * @throws IOException - constructs an IOException with the specified detail message
     */
    public List<Racer> readAbbreviationsFile(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException("Null was passed to the method...");
        }
        List<Racer> result = new ArrayList<>();
        String currentLine;
        String abbreviation;
        String name;
        String car;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((currentLine = br.readLine()) != null) {
                String[] arrayOfCurrentLine = currentLine.split("_");
                abbreviation = arrayOfCurrentLine[0];
                name = arrayOfCurrentLine[1];
                car = arrayOfCurrentLine[2];
                result.add(new Racer(name, car, abbreviation));
            }
        }
        return result;
    }

    /**
     * Takes log file and converts to a Map with abbreviation as key and date and time as value.
     *
     * @param fileName - a name of the log file
     * @return - a Map with abbreviation as key and date and time as value
     * @throws IOException - constructs an IOException with the specified detail message
     */
    public Map<String, LocalDateTime> convertTextLog(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException("Null was passed to the method...");
        }
        Map<String, LocalDateTime> result = new TreeMap<>();

        DateTimeFormatter inputFormatter = ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        String currentLine;
        String racer;
        LocalDateTime date;

        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            while ((currentLine = buffer.readLine()) != null) {
                racer = currentLine.replaceAll("[^A-Z]", "");
                currentLine = currentLine.replaceAll("[A-Z]", "");
                date = parse(currentLine, inputFormatter);
                result.put(racer, date);
            }
        }
        return result;
    }
}
