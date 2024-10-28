package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int ELEMS_TO_READ = 1000;
    private static final int ELEMS_TO_ADD = 100_000;
    private static final int INIT_VAL = 1000;
    private static final int FINAL_VAL = 1999;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> arrayList = new ArrayList<>();
        for (int i = INIT_VAL; i <= FINAL_VAL; i++) {
            arrayList.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linkedList = new LinkedList<>();
        linkedList.addAll(arrayList);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        var temp = arrayList.get(0);
        arrayList.set(0, arrayList.get(arrayList.size() - 1));
        arrayList.set(arrayList.size() - 1, temp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer elem : arrayList) {
            System.out.println(elem);
        }

        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time;
        long millis;

        time = System.nanoTime();
        for (int i = 1; i <= ELEMS_TO_ADD; i++) {
            arrayList.addFirst(i);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("\nAdding 100,000 elems to an ArrayList: " + millis + "ms");

        time = System.nanoTime();
        for (int i = 1; i <= ELEMS_TO_ADD; i++) {
            linkedList.addFirst(i);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Adding 100,000 elems to a LinkedList: " + millis + "ms");

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        time = System.nanoTime();
        for (int i = 1; i <= ELEMS_TO_READ; i++) {
            arrayList.get(arrayList.size() / 2);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("\nReading 1000 elems from the middle of an ArrayList: " + millis + "ms");

        time = System.nanoTime();
        for (int i = 1; i <= ELEMS_TO_READ; i++) {
            linkedList.get(linkedList.size() / 2);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Reading 1000 elems from the middle of a LinkedList: " + millis + "ms");
        
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> map = new HashMap<>();

        map.put("Africa", 1_110_635_000L);
        map.put("Americas", 972_005_000L);
        map.put("Antartica", 0L);
        map.put("Asia", 4_298_723_000L);
        map.put("Europe", 742_452_000L);
        map.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
        var globalPop = 0L;

        for (Long continentPop : map.values()) {
            globalPop += continentPop;
        }

        System.err.println("\nTotal population of the world: " + globalPop);

    }
}
