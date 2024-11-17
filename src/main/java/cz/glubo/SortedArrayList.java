package cz.glubo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

public class SortedArrayList<Type extends Comparable<Type>> implements SortedList<Type> {
    ArrayList<Type> array = new ArrayList<>();

    @Override
    public void add(Type element) {
        if (element == null) return;

        int index = Collections.binarySearch(array, element, Comparable<Type>::compareTo);
        if (index >= 0) return;

        array.add(-1 - index, element);
    }

    @Override
    public void filter(Predicate<Type> keepElement) {
        array.removeIf(keepElement.negate());
    }

    @Override
    public void clear() {
        array.clear();
    }

    @Override
    public boolean contains(Type element) {
        int index = Collections.binarySearch(array, element, Comparable<Type>::compareTo);

        return index >= 0;
    }

    @Override
    public Iterator<Type> iterator() {
        return array.iterator();
    }
}
