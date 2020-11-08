public class Main {

    public static void main(String[] args) {

            Pheromone[][] pheromoneArray = initialisePheromoneArray(4);
            System.out.println("a");
    }


    private static Pheromone[][] initialisePheromoneArray(int tourLength) {
        Pheromone[][] temporaryArray = new Pheromone[tourLength][tourLength];
        for (int i = 0; i < tourLength; i++) {
            for (int j = 0; j < tourLength - 1; j++) {
                temporaryArray[i][j] = new Pheromone(i, j);
            }
        }
        return temporaryArray;
    }
}
