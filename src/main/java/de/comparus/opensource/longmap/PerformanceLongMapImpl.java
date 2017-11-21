package de.comparus.opensource.longmap;

/*
The output of console is:
Start putting 100000 elements...
Stop putting elements
It took  : 27seconds.


Start get an element in array of length 100 000
Stop get an element in array of length 100 000
It took  : 0

Conclusion : implementation of LongMapImpl has a bad performance :(
Always use inner solutions like HashMap :)
*/

public class PerformanceLongMapImpl {

    public static void main(String[] args) {

        int size = 100000;
        System.out.println("Start putting " + size +  " elements...");
        long startPut = System.currentTimeMillis();
        LongMap<String> map = new LongMapImpl<> (size);
        for (int i = 0; i < size; i++){
            long k = (long)(Math.random() * Long.MAX_VALUE);
            map.put(k, "test");
        }
        long stopPut = System.currentTimeMillis();
        System.out.println("Stop putting elements");
        System.out.println("It took  : " + (stopPut - startPut)/1000 + "seconds.\n\n");


        System.out.println("Start get an element in array of length " + size);
        long startGet = System.currentTimeMillis();

        map.get(128569580024455L);
        long stopGet = System.currentTimeMillis();

        System.out.println("Stop get an element in array of length" + size);

        System.out.println("It took  : " + (stopGet - startGet)/1000 + " seconds");
    }
}
