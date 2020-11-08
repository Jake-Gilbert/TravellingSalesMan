public class Pheromone {

    double pheromoneLevelForCityPair;
    //A pheromone object will be created for each connected city
    public Pheromone(int i, int j) {
        pheromoneLevelForCityPair = initialisePheromone(i , j);
    }

    private double initialisePheromone(int i, int j) {
        return 0.015;
    }

    private double updatePheromone(double x, double y) {
        return 0.1;
    }

    public void pheromoneDecay(int i, int j, double rateOfEvaporation) {
        pheromoneLevelForCityPair *= rateOfEvaporation;
    }
}
