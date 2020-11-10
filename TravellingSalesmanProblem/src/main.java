import java.util.Map;

public class main {

    static Map<Integer, City> cities;
    public static void main(String[] args) throws InterruptedException {
        populateMap();
        Salesman salesman = new Salesman(cities);
        salesman.randomRoutes(17, 1000);

    }

    private static void populateMap() {
        FileReader fileReader = new FileReader();
        cities = fileReader.populateMap();
    }
}
