package cz.glubo;

import cz.glubo.helper.Implementations;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class IntTest implements WithAssertions {
    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanAddAndRetrieveOneElement(SortedList<Integer> list) {
        list.add(1);

        var resultList = toList(list);
        assertThat(resultList).containsExactly(1);
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void nullIsIgnored(SortedList<Integer> list) {
        list.add(null);

        var resultList = toList(list);
        assertThat(resultList).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanAddAndClear(SortedList<Integer> list) {
        list.add(1);
        list.clear();

        var resultList = toList(list);
        assertThat(resultList).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanAddAndRetrieveMoreElements(SortedList<Integer> list) {
        list.add(3);
        list.add(2);
        list.add(1);

        var resultList = toList(list);
        assertThat(resultList).containsExactly(1, 2, 3);
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanRemoveOddElements(SortedList<Integer> list) {
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.filter((Integer element) -> element % 2 == 1);

        var resultList = toList(list);
        assertThat(resultList).containsExactly(1, 3, 5);
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanFindElements(SortedList<Integer> list) {
        list.add(5);
        list.add(4);
        list.add(2);
        list.add(1);

        assertThat(list.contains(3)).isFalse();

        list.add(3);

        assertThat(list.contains(3)).isTrue();
    }

    public static Stream<Arguments> sortedListImplementationArguments() {
        return Implementations.allInteger().map(Arguments::of);
    }


    private static List<Integer> toList(SortedList<Integer> a) {
        return StreamSupport.stream(a.spliterator(), false).toList();
    }
}
