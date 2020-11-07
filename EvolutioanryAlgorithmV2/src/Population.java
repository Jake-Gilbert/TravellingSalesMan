import java.util.ArrayList;

public class Population {

    public ArrayList<Tour> population;

    public Population(int populationSize, int tourLength) {
        population = initialisePopulation(populationSize, tourLength);
    }

    //Initialises population based on the size of the pop and the route length
    public ArrayList<Tour> initialisePopulation(int populationSize, int tourLength) {
            ArrayList<Tour> temp = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) { ;
                temp.add(new Tour(tourLength));
            }
            return temp;
    }

}
