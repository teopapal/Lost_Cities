package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class is responsible for loading the CSV file and storing the historical information in a map.
 * The map is used to retrieve the historical information for a given finding name.
 */
public class Csv {
    private static final Map<String, String> historicalInfoMap = new HashMap<>();

    /**
     * Loads the historical information from the CSV file.
     */
    public static void loadHistoricalInfo() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(Csv.class.getResource("/resources/csvFiles/csv_greek.csv")).openStream()))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    historicalInfoMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    /**
     * Returns the historical information for a given finding name.
     *
     * @param findingName The name of the finding
     * @return The historical information for the given finding name
     */
    public static String getHistoricalInfo(String findingName) {
        return historicalInfoMap.getOrDefault(findingName, "No historical information available.");
    }
}
