package cz.glubo;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class IteratorSortedLinkedList<Type extends Comparable<Type>> implements SortedList<Type> {
    private LinkedList<Type> list = new LinkedList<>();

    @Override
    public void add(Type newElement) {
        if (newElement == null) return;

        var i = list.listIterator();

        while (i.hasNext()) {
            var current = i.next();
            if (current.compareTo(newElement) > 0) {
                if (i.hasPrevious()) {
                    i.previous();
                    i.add(newElement);
                } else {
                    list.addFirst(newElement);
                }
                return;
            }
        }
        list.addLast(newElement);
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
