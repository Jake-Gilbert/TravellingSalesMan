import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

        public Map<Integer, City> populateMap() {

            Map<Integer, City> tempMap = new HashMap<>();
            final String csvFile = "/Users/Jake/Documents/CS/CS3910/EvolutionaryAlgorithm/src/ulysses16.csv";
            BufferedReader csvReader = null;
            String line = "";

            try {
                csvReader = new BufferedReader(new java.io.FileReader(csvFile));
                while ((line = csvReader.readLine()) != null) {
                    String[] coordinates = line.split(",");

                    if (coordinates[0].matches("\\d+") && coordinates.length == 3) {
                        int cityIndex = Integer.parseInt(coordinates[0]) - 1;
                        tempMap.put(cityIndex, new City(Double.parseDouble(coordinates[1]), Double.parseDouble(coordinates[2])));
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempMap;
        }
    }

