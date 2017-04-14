package lab9;

import custom.collections.BSTHashSet;
import custom.collections.LLHashSet;

import java.util.Random;

public class Benchmark {
    public static void main(String[] args) {
        for (int trial = 1; trial <= 3; trial++) {

            System.out.println("Starting timing tests for trial "
                    + trial + "...");
// Change the next line for the two different implementations

            BSTHashSet<Integer> set = new BSTHashSet<>(10);
            long time1, time2, duration;
// Timing test for column 1 -- change for the different rows
            time1 = System.currentTimeMillis();

            for (int i = 0; i <= 25000; i++) {
//            for (int i = 0; i <= 250000; i+=10){
                set.add(i);
            }
//            Random rand = new Random();
//            while (set.size() < 25000) {
//                int i = rand.nextInt();
//                set.add(Math.abs(i));
//            }

            time2 = System.currentTimeMillis();
            duration = time2 - time1;

            System.out.println("Add time in ms: " + duration);
// Timing test for column 2 – DO NOT CHANGE THIS CODE, EVER!!!
            time1 = System.currentTimeMillis();

//            for (int j = 0; j <= 25000; j++) {
            for (int j = 0; j <= 250000; j+=10){
                set.contains(j);
            }

            time2 = System.currentTimeMillis();
            duration = time2 - time1;

            System.out.println("Contains time in ms: " + duration);
// Hash table stats for columns 3 and 4 – DO NOT CHANGE!!!
            System.out.println("Load factor: " + set.getLoadFactor());
            System.out.println("Bucket size standard deviation: " +
                    set.getBucketSizeStandardDev());

        }
    }
}
