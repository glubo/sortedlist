package cz.glubo;

import cz.glubo.helper.Implementations;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class StringTest implements WithAssertions {
    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanAddAndRetrieveOneElement(SortedList<String> list) {
        list.add("Adam");

        assertThat(list).containsExactly("Adam");
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanAddAndRetrieveOneElementMultipleTimes(SortedList<String> list) {
        list.add("Adam");
        list.add("Adam");

        assertThat(list).containsExactly("Adam", "Adam");
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void nullIsIgnored(SortedList<String> list) {
        list.add(null);

        assertThat(list).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanAddAndClear(SortedList<String> list) {
        list.add("Adam");
        list.clear();

        assertThat(list).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanAddAndRetrieveMoreElements(SortedList<String> list) {
        list.add("Cyril");
        list.add("Bozena");
        list.add("Adam");

        assertThat(list).containsExactly("Adam", "Bozena", "Cyril");
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanFilterElementsStartingWithB(SortedList<String> list) {
        list.add("Cyril");
        list.add("Bozena");
        list.add("Adam");
        list.filter((String element) -> element.startsWith("B"));

        assertThat(list).containsExactly("Bozena");
    }

    @ParameterizedTest
    @MethodSource("sortedListImplementationArguments")
    void weCanFindElements(SortedList<String> list) {
        list.add("Cyril");
        list.add("Adam");
        assertThat(list.contains("Bozena")).isFalse();

        list.add("Bozena");

        assertThat(list.contains("Bozena")).isTrue();
    }

    public static Stream<Arguments> sortedListImplementationArguments() {
        return Implementations.allString().map(Arguments::of);
    }


}
