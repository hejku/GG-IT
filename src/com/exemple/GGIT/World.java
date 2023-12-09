package com.exemple.GGIT;

import java.util.Random;

public class World {
    private static final int defaultnoOfDays = 10;
    public static void main(String[] args) {
        int noOfDays = args.length > 0 ? Integer.parseInt(args[0]) : defaultnoOfDays;
        for(int i = 0; i < noOfDays; i++ ) {
            Simulation.simulateDay();
        }
    }
}