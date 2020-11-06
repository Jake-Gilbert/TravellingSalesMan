import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class ParentSelection {
    Random random;
    private ArrayList<Integer[]> tournament;
    private ArrayList<Integer[]> parents;

    public ParentSelection(ArrayList<Integer[]> population, int size, Map<Integer, City> cityMap) {
        tournament = populateTournament(population, size);
        parents = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parents.add(selectParent(tournament, cityMap));
        }


    }

    //Fills the tournament with random values from the population
    private ArrayList<Integer[]> populateTournament(ArrayList<Integer[]> population, int size) {
        random = new Random();
        ArrayList<Integer[]> tournament = new ArrayList<>(size);
        int sizeCheck = size;
        while (size > 0) {
            int randomTourAtIndex = random.nextInt(population.size());
            if (!tournament.contains(population.get(randomTourAtIndex))) {
                tournament.add(population.get(randomTourAtIndex));
                size--;
            }

        }

        return tournament;
    }

    //Returns the parent pair
    public ArrayList<Integer[]> getParents() {
        return parents;
    }

    //Chooses the fastest parent
    private Integer[] selectParent(ArrayList<Integer[]> tournamentPop, Map<Integer, City> cityMap) {
        Tour tour = new Tour();
        Integer[] shortest = tournamentPop.get(0);
        for (int i = 0; i < tournamentPop.size() - 1; i++) {
            if (tour.getCostOfRoute(cityMap, shortest) > tour.getCostOfRoute(cityMap, tournamentPop.get(i))) {
                shortest = tournamentPop.get(i);
                tournamentPop.remove(i);
            }
        }
        return shortest;
    }

    public ArrayList<Integer[]> getTournament() {
        return tournament;
    }

    public void printTournament(ArrayList<Integer[]> tournament, Map<Integer, City> cityMap) {
        Tour tour = new Tour();
        for (Integer[] route : tournament) {
            System.out.println(Arrays.toString(route));
            System.out.println(tour.getCostOfRoute(cityMap, route));
            System.out.println();
        }
    }

}


