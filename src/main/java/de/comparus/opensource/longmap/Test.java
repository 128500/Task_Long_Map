package de.comparus.opensource.longmap;

import javafx.util.Pair;

public class Test {

    public static void main(String[] args) {

        Pair [] pairs = new Pair [15000000];
        for (int i = 0; i < 15000000; i++){
            long k = (long)(Math.random() * Long.MAX_VALUE);
            pairs[i] = new Pair<Long, String>(k, "test");
        }

        long start = System.currentTimeMillis();
        for (Pair<Long, String> pair : pairs){
            if (pair.getKey().equals(128569580024455L)) {
                System.out.println("----------------------");
                System.out.println("Match!!!!");
                System.out.println("----------------------");
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println(stop - start);
    }
}
