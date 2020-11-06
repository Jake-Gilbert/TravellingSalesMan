import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ParentSelection {
    Random random;
    public ParentSelection(ArrayList<ArrayList<Integer>> population, int size, Map<Integer, City> cityMap) {
        ArrayList<ArrayList<Integer>> tournament = populateTournament(population, size);
        printTournament(tournament, cityMap);
    }

    //Fills the tournament with random values from the population
    private ArrayList<ArrayList<Integer>> populateTournament(ArrayList<ArrayList<Integer>> population, int size) {
        random = new Random();
        ArrayList<ArrayList<Integer>> tournament = new ArrayList<>(size );
        while (size > 0) {
                int randomTourAtIndex = random.nextInt(population.size());
                if (!tournament.contains(population.get(randomTourAtIndex))) {
                    tournament.add(population.get(randomTourAtIndex));
                    size--;
                }

            }

        return tournament;
    }

    public void printTournament(ArrayList<ArrayList<Integer>> tournament, Map<Integer, City> cityMap) {
        Tour tour = new Tour();
        for (ArrayList<Integer> route : tournament) {
            System.out.println(route.toString());
            System.out.println(tour.getCostOfRoute(cityMap, route));

            System.out.println();
        }
    }

}
