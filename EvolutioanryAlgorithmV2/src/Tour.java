import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Tour {
    int length;

    public Tour() {
    }
    public Integer[] generateRoute(int length) {

        ArrayList<Integer> tour = new ArrayList<>(length);
        for (int i = 0; i < length - 1; i++) {
            tour.add(i);
        }
        Collections.shuffle(tour);
        tour.add(tour.size(), tour.get(0));
        Integer[] temp = new Integer[length];
        return tour.toArray(temp);
    }


    public double getCostOfRoute(Map<Integer, City> cityMap, Integer[] route) {
        double total = 0.0;
        for (int i = 0; i < route.length - 1; i++) {
            total += difference(cityMap, route[i], route[i+1]);
        }
        BigDecimal bd = new BigDecimal(total);
        bd = bd.round(new MathContext(3));
        total = bd.doubleValue();
        return total;
    }

    public double difference (Map<Integer, City> cityMap, int city1, int city2){
        City a = getCity(cityMap, city1);
        City b = getCity(cityMap, city2);
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    public City getCity (Map<Integer, City> cityMap,  int cityNumber){
        return cityMap.get(cityNumber);
    }
}
