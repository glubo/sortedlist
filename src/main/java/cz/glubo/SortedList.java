package cz.glubo;


import java.util.function.Predicate;

public interface SortedList<Type extends Comparable<Type>> extends Iterable<Type> {
    /**
     * add a new element to a sorted list at appropriate position
     * if the element is null, no action is taken
     * if the element is already contained in our sorted list, we will store a duplicate
     */
    void add(Type element);

    /**
     * filter our stored elements, keeping just those that matches our Predicate
     * @param keepElement true means that we want to keep an element
     */
    void filter(Predicate<Type> keepElement);

    /**
     * drop all stored elements
     */
    void clear();

    /**
     * check if our stored elements contains a specific element
     * @return true means stored elements do contain this element
     */
    boolean contains(Type element);
}
