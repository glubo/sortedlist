package cz.glubo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

public class NaiveSortedArrayList<Type extends Comparable<Type>> implements SortedList<Type> {
    ArrayList<Type> array = new ArrayList<>();

    @Override
    public void add(Type element) {
        if (element == null) return;

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).compareTo(element) > 0) {
                array.add(i, element);
                return;
            }
        }
        array.addLast(element);
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
        return array.contains(element);
    }

    @Override
    public Iterator<Type> iterator() {
        return array.iterator();
    }
}
