package cz.glubo;


import java.util.function.Predicate;

public interface SortedList<Type extends Comparable<Type>> extends Iterable<Type> {
    void add(Type element);

    void filter(Predicate<Type> keepElement);

    void clear();

    boolean contains(Type element);
}
