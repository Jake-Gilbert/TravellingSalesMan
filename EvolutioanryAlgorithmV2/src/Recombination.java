import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Recombination {

    ArrayList<Tour> offsprings;
    public Recombination(Tour parentOne, Tour parentTwo) {
        offsprings = new ArrayList<>();
        int startCityP1 = parentOne.tour[0];
        int startCityP2 = parentTwo.tour[0];

        Tour modifiedP1 = removeStartAndEndCity(parentOne);
        Tour modifiedP2 = removeStartAndEndCity(parentTwo);

        Tour offspring;
        Tour offspring2;


        int spliceStart = ThreadLocalRandom.current().nextInt(0, modifiedP1.tour.length - 2);
        int spliceEnd = ThreadLocalRandom.current().nextInt(spliceStart + 1, modifiedP1.tour.length- 1);

        offspring = fillUsingRandomSplice(modifiedP1, spliceStart, spliceEnd);
        offspring2 = fillUsingRandomSplice(modifiedP2, spliceStart, spliceEnd);

        offspring = takeFromTheOtherParent(offspring, modifiedP2, spliceEnd, spliceStart, startCityP2, startCityP1);
        offspring2 = takeFromTheOtherParent(offspring2, modifiedP1, spliceEnd, spliceStart, startCityP1, startCityP2);

        offsprings.add(offspring);
        offsprings.add(offspring2);

    }

    //Removes start and end city to avoid complications
    private Tour removeStartAndEndCity(Tour parent) {
        Tour modifiedParent = new Tour(parent.tour.length - 1);
        for (int i = 0; i < parent.tour.length - 1; i++) {
            if (!(i == 0 || i == parent.tour.length - 1)) {
                modifiedParent.tour[i] = parent.tour[i];
            }
        }
        return modifiedParent;


    }

    //Adds the previously removed start and end cities
    private void addStartAndEndCity(Tour offspring, int startAndEndCity) {
        ArrayList<Integer> completedTourAsList = new ArrayList<Integer>(Arrays.asList(offspring.tour));
        completedTourAsList.add(0, startAndEndCity);
        completedTourAsList.add(completedTourAsList.size(),  startAndEndCity);
        offspring.tour = completedTourAsList.toArray(offspring.tour);
    }

    //Fills the offspring between a random range
    private Tour fillUsingRandomSplice(Tour parent, int spliceStart, int sliceEnd) {
        Tour halfFilledRoute = new Tour(parent.tour.length - 1);
        halfFilledRoute.tour = new Integer[parent.tour.length-1];
        System.arraycopy(parent.tour, spliceStart, halfFilledRoute.tour, spliceStart, sliceEnd - spliceStart);
        return halfFilledRoute;
    }

    //Fills the offspring with the remaining elements from the other parent
    private Tour takeFromTheOtherParent(Tour offspring, Tour otherParent, int spliceEnd, int spliceStart, int missingCity, int startEndCity) {
        Tour completedList = new Tour(13);
        completedList.tour = offspring.tour.clone();

        //A loop will continuously run until this int value is decreased past 0
        int numElementsToCopy = otherParent.tour.length - (spliceEnd - spliceStart);
        int pointer = spliceEnd;
        int positionInOffspring = spliceEnd;

        while (numElementsToCopy > 1) {

            pointer = pointer % otherParent.tour.length;


            //Checks whether an element is contained
            if (!Arrays.asList(completedList.tour).contains(otherParent.tour[pointer]) && otherParent.tour[pointer] != startEndCity) {
                completedList.tour[positionInOffspring] = otherParent.tour[pointer];
                numElementsToCopy--;
                positionInOffspring++;
            }
            else if (!Arrays.asList(completedList.tour).contains(missingCity) && missingCity != startEndCity) {
                completedList.tour[positionInOffspring] = missingCity;
                positionInOffspring++;
                numElementsToCopy--;
            }


                positionInOffspring = positionInOffspring % offspring.tour.length;


                pointer++;
        }
        addStartAndEndCity(completedList, startEndCity);
        return completedList;

    }



}
