package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Александр on 31.05.2017.
 */
public class Randomiser {

    private Long kolElement;

    public Randomiser(Long kolElement) {
        this.kolElement = kolElement;
    }

    public ArrayList<Long> getArray() {
        Random randomGenerator1 = new Random();
        ArrayList<Long> randomArray = new ArrayList<>();
        for (int size = 0; size < kolElement; size++) {
            randomArray.add((long) randomGenerator1.nextInt());
        }
        return randomArray;
    }


}

