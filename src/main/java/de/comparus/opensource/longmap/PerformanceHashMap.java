package de.comparus.opensource.longmap;

import java.util.HashMap;
import java.util.Map;


/**
 The output of console is:
 Start putting 10 000 000 elements...
 Stop putting elements
 It took  : 19seconds.


 Start get an element in array of length 10 000 000
 Stop get an element in array of length 10 000 000
 It took  : 0
 */

public class PerformanceHashMap {

    public static void main(String[] args) {

        int size = 10000000;
        System.out.println("Start putting " + size +  " elements...");
        long startPut = System.currentTimeMillis();
        Map<Long, String> map = new HashMap<>(size);
        for (int i = 0; i < size; i++){
            long k = (long)(Math.random() * Long.MAX_VALUE);
            map.put(k, "test");
        }
        long stopPut = System.currentTimeMillis();
        System.out.println("Stop putting elements");
        System.out.println("It took  : " + (stopPut - startPut)/1000 + " seconds.\n\n");


        System.out.println("Start get an element in array of length  " + size);
        long startGet = System.currentTimeMillis();
        map.get(128569580024455L);
        long stopGet = System.currentTimeMillis();

        System.out.println("Stop get an element in array of length  " + size);

        System.out.println("It took  : " + (stopGet - startGet)/1000 + " seconds.");
    }
}
