package cz.glubo.helper;


import cz.glubo.*;

import java.util.stream.Stream;

public class Implementations {

    public static <Type extends Comparable<Type>> Stream<SortedList<Type>> all() {
        return Stream.of(
                new SortedLinkedList<>(),
                new NaiveSortedLinkedList<>(),
                new NaiveSortedArrayList<>(),
                new SortedArrayList<>()
        );
    }

    public static Stream<SortedList<Integer>> allInteger() {
        return all();
    }

    public static Stream<SortedList<String>> allString() {
        return all();
    }
}
