import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ParentSelection {
    Random random;
    private ArrayList<Tour> tournament;
    private ArrayList<Tour> parents;

    public ParentSelection(ArrayList<Tour> population, int size, Map<Integer, City> cityMap) {
        tournament = populateTournament(population, size);
        parents = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            parents.add(selectFastestParent(tournament, cityMap));
        }


    }

    //Fills the tournament with random values from the population
    private ArrayList<Tour> populateTournament(ArrayList<Tour> population, int size) {
        random = new Random();
        ArrayList<Tour> tournament = new ArrayList<>(size);
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
    public ArrayList<Tour> getParents() {
        return parents;
    }

    //Chooses the fastest parent
    private Tour selectFastestParent(ArrayList<Tour> tournamentPop, Map<Integer, City> cityMap) {
        Tour shortest = tournamentPop.get(0);
        for (int i = 0; i < tournamentPop.size() - 1; i++) {
            if (shortest.getCostOfRoute(cityMap) > tournamentPop.get(i).getCostOfRoute(cityMap)) {
                shortest = tournamentPop.get(i);
                tournamentPop.remove(i);
            }
        }
        return shortest;
    }

    public ArrayList<Tour> getTournament() {
        return tournament;
    }


}


