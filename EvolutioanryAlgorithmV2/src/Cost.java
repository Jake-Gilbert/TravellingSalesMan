import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cost {
    Map<Integer, City> cityMap;
    public Cost(Map<Integer, City> cityMap){
        this.cityMap = new HashMap<>(cityMap);
    }
    public double getCostOfRoute(ArrayList<Integer> route) {
        double total = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            total += difference(route.get(i), route.get(i + 1));
        }
        BigDecimal bd = new BigDecimal(total);
        bd = bd.round(new MathContext(3));
        total = bd.doubleValue();
        return total;
    }

    public double difference ( int city1, int city2){
        City a = getCity(city1);
        City b = getCity(city2);
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    public City getCity ( int cityNumber){
        return cityMap.get(cityNumber);
    }
}
