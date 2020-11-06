import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Recombination {

    ArrayList<Integer[]> offsprings;
    public Recombination(Integer[] parentOne, Integer[] parentTwo) {
        offsprings = new ArrayList<>();
        int startCityP1 = parentOne[0];
        int startCityP2 = parentTwo[0];

        Integer[] modifiedP1 = removeStartAndEndCity(parentOne);
        Integer[] modifiedP2 = removeStartAndEndCity(parentTwo);

        Integer[] offspring;
        Integer[] offspring2;


        int spliceStart = ThreadLocalRandom.current().nextInt(0, modifiedP1.length - 2);
        int spliceEnd = ThreadLocalRandom.current().nextInt(spliceStart + 1, modifiedP1.length- 1);

        offspring = fillUsingRandomSplice(modifiedP1, spliceStart, spliceEnd);
        offspring2 = fillUsingRandomSplice(modifiedP2, spliceStart, spliceEnd);

        offspring = takeFromTheOtherParent(offspring, modifiedP2, spliceEnd, spliceStart, startCityP2, startCityP1);
        offspring2 = takeFromTheOtherParent(offspring2, modifiedP1, spliceEnd, spliceStart, startCityP1, startCityP2);

        System.out.println(offspring);
    }

    //Removes start and end city to avoid complications
    private Integer[] removeStartAndEndCity(Integer[] parent) {
        Integer[] modifiedParent = new Integer[parent.length - 1];
        for (int i = 0; i < parent.length - 1; i++) {
            if (!(i == 0 || i == parent.length - 1)) {
                modifiedParent[i] = parent[i];
            }
        }
        return modifiedParent;


    }

    //Adds the previously removed start and end cities
    private Integer[] addStartAndEndCity(Integer[] offspring, int startAndEndCity) {
       // ArrayList<Integer> completedTourAsList = (ArrayList<Integer>) Arrays.asList(offspring.clone());
        ArrayList<Integer> completedTourAsList = new ArrayList<Integer>(Arrays.asList(offspring));
        completedTourAsList.add(0, startAndEndCity);
        completedTourAsList.add(completedTourAsList.size(),  startAndEndCity);
        Integer[] tourWithAllCities = new Integer[16];
        return completedTourAsList.toArray(tourWithAllCities);
    }

    //Fills the offspring between a random range
    private Integer[] fillUsingRandomSplice(Integer[] parent, int spliceStart, int sliceEnd) {
        Integer[] halfFilledRoute = new Integer[parent.length];

        System.arraycopy(parent, spliceStart, halfFilledRoute, spliceStart, sliceEnd - spliceStart);
        return halfFilledRoute;
    }

    //Fills the offspring with the remaining elements from the other parent
    private Integer[] takeFromTheOtherParent(Integer[] offspring, Integer[] otherParent, int spliceEnd, int spliceStart, int missingCity, int startEndCity) {
        Integer[] completedList = offspring.clone();

        int numElementsToCopy = otherParent.length - (spliceEnd - spliceStart) - 1;
        int pointer = spliceEnd;
        int positionInOffspring = spliceEnd;

        while (numElementsToCopy > 0) {
            if (pointer > otherParent.length - 1) {
                pointer = 0;
            }

            //Checks whether an element is contained
            if (!Arrays.asList(completedList).contains(otherParent[pointer]) && otherParent[pointer] != startEndCity) {
                completedList[positionInOffspring] = otherParent[pointer];
                numElementsToCopy--;
                positionInOffspring++;
            }
            else if (!Arrays.asList(completedList).contains(missingCity) && missingCity != startEndCity) {
                completedList[positionInOffspring] = missingCity;
                positionInOffspring++;
            }


            if (positionInOffspring > offspring.length - 1) {
                positionInOffspring = 0;
            }


                pointer++;
        }
        completedList = addStartAndEndCity(completedList, startEndCity);
        return completedList;

    }



}
