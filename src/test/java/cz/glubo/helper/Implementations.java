package cz.glubo.helper;


import cz.glubo.IteratorSortedLinkedList;
import cz.glubo.NaiveSortedLinkedList;
import cz.glubo.SortedArrayList;
import cz.glubo.SortedList;

import java.util.stream.Stream;

public class Implementations {

    public static <Type extends Comparable<Type>> Stream<SortedList<Type>> all() {
        return Stream.of(
                new IteratorSortedLinkedList<>(),
                new NaiveSortedLinkedList<>(),
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
