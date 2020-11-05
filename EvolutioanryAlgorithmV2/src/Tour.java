import java.util.ArrayList;
import java.util.Collections;

public class Tour {
    int length;
    ArrayList<Integer> tour = new ArrayList<>();

    public Tour(int length) {
        this.length = length;
        tour = generateRoute(length);
    }

    public ArrayList<Integer> generateRoute(int length) {
        ArrayList<Integer> tour = new ArrayList<>();
        for (int i = 1; i < length; i++) {
            tour.add(i);
        }
        Collections.shuffle(tour);
        tour.add(tour.size(), tour.get(0));
        return tour;
    }

    public ArrayList<Integer> getTour() {
        return tour;
    }
}
