import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Recombination {

    public ArrayList<Tour> offsprings;
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
        Tour modifiedParent = new Tour(parent.tour.length - 2);
        ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(parent.tour));
        temp.remove(0);
        temp.remove(temp.size() - 1);
        modifiedParent.tour = temp.toArray(modifiedParent.tour);
        return modifiedParent;


    }

    public double getCost(Tour a, Map<Integer, City> c) {
        return a.getCostOfRoute(c);
    }
    //Adds the previously removed start and end cities
    private ArrayList<Integer> addStartAndEndCity(ArrayList<Integer> offspring, int startAndEndCity) {
        ArrayList<Integer> completedOffspring = new ArrayList<>(offspring);
        completedOffspring.add(0, startAndEndCity);
        completedOffspring.add(completedOffspring.size(),  startAndEndCity);
        return completedOffspring;
    }

    //Fills the offspring between a random range
    private Tour fillUsingRandomSplice(Tour parent, int spliceStart, int sliceEnd) {
        Tour halfFilledRoute;
        halfFilledRoute = new Tour(parent.tour.length);
        halfFilledRoute.tour = new Integer[parent.tour.length];
        System.arraycopy(parent.tour, spliceStart, halfFilledRoute.tour, spliceStart, sliceEnd - spliceStart);
        return halfFilledRoute;
    }

    //Fills the offspring with the remaining elements from the other parent
    private Tour takeFromTheOtherParent(Tour offspring, Tour otherParent, int spliceEnd, int spliceStart, int missingCity, int startEndCity) {
        Tour completedList = new Tour(14);
        completedList.tour = offspring.tour.clone();
        //A loop will continuously run until this int value is decreased past 0
        int numElementsToCopy = 0;
        if (missingCity != startEndCity) {
            numElementsToCopy = (otherParent.tour.length - 1)  - (spliceEnd - spliceStart)  + 2;
        }
        else {
            numElementsToCopy = (otherParent.tour.length - 1)  - (spliceEnd - spliceStart) + 1;

        }
        int pointer = spliceEnd;
        int positionInOffspring = spliceEnd;
        while (numElementsToCopy > 0 && Arrays.asList(completedList.tour).contains(null)) {

            pointer = pointer % otherParent.tour.length;

//            if (!temp.contains(otherParent.tour[pointer])){
//                if (otherParent.tour[pointer] != missingCity && otherParent.tour[pointer] != startEndCity){
//                    temp.set(positionInOffspring, otherParent.tour[pointer]);
//                    positionInOffspring++;
//                    numElementsToCopy--;
//                }
//            }
            if ((!Arrays.asList(completedList.tour).contains(otherParent.tour[pointer])) && otherParent.tour[pointer] != startEndCity) {
                completedList.tour[positionInOffspring] = otherParent.tour[pointer];
                numElementsToCopy--;
                positionInOffspring++;
            }
            if (missingCity != startEndCity && !Arrays.asList(completedList.tour).contains(missingCity)) {
                completedList.tour[positionInOffspring] = missingCity;
                numElementsToCopy--;
                positionInOffspring++;
            }
                positionInOffspring = positionInOffspring % offspring.tour.length;

                pointer++;
        }
        ArrayList<Integer> temp = new ArrayList<Integer> (Arrays.asList(completedList.tour));

        temp  = addStartAndEndCity(temp, startEndCity);
        completedList.tour = temp.toArray(completedList.tour);

        return completedList;

    }



}
