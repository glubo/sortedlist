package cz.glubo;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class NaiveSortedLinkedList<Type extends Comparable<Type>> implements SortedList<Type> {
    private LinkedList<Type> list = new LinkedList<>();

    @Override
    public void add(Type element) {
        if (element == null) return;

        list.add(element);
        list.sort(Comparable::compareTo);
    }

    @Override
    public Iterator<Type> iterator() {
        return list.iterator();
    }

    @Override
    public void filter(Predicate<Type> keep) {
        list.removeIf(keep.negate());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(Type element) {
        return list.contains(element);
    }
}
