package org.example.models;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class Dice {
    private int rigged;
    private final int MAX = 100;
    private final SecureRandom random = new SecureRandom();
    private int faceArray[] = {1, 2, 3, 4, 5, 6};
    public int roll()
    {
        int odds = (int) (random.nextDouble() * 100);
        //System.out.println(odds);
        if (odds <= 20)
            return rigged;
        if (odds <= 36)
            return faceArray[0];
        if (odds <= 52)
            return faceArray[1];
        if (odds <= 68)
            return faceArray[2];
        if (odds <= 84)
            return faceArray[3];
        if (odds <= 100)
            return faceArray[4];
        throw new RuntimeException("unreachable");
    }
    public Dice(int rigged){
        this.rigged = rigged;
        faceArray[rigged - 1] = 1000;
        Arrays.sort(faceArray);
    }
}
