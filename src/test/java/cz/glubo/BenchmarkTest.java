package cz.glubo;

import cz.glubo.helper.Implementations;
import cz.glubo.helper.Statistics;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;


public class BenchmarkTest implements WithAssertions {
    @Test
    void benchmarkAddition() {
        runBenchmark(
                SortedList::clear,
                (list) -> {
                    for (int i : BIG_LIST) {
                        list.add(i);
                    }
                }
        );
    }

    @Test
    void benchmarkContains() {
        runBenchmark(
                (list) -> {
                    list.clear();
                    for (int i : BIG_LIST) {
                        list.add(i);
                    }

                },
                (list) -> {
                    for (int needle : SMALL_LIST) {
                        list.contains(needle);
                    }
                }
        );
    }

    @Test
    void benchmarkFilter() {
        runBenchmark(
                (list) -> {
                    list.clear();
                    for (int i : BIG_LIST) {
                        list.add(i);
                    }
                },
                (list) -> {
                    list.filter((it) -> it % 2 == 0);
                }
        );
    }

    private void runBenchmark(
            Consumer<SortedList<Integer>> prepare,
            Consumer<SortedList<Integer>> action
    ) {
        var results = new HashMap<String, List<Duration>>();
        for (int iteratrion = 0; iteratrion < REPEATS; iteratrion++) {
            var implementations = new ArrayList<>(Implementations.allInteger().toList());
            // we want different order each time to eliminate sequential advantage
            Collections.shuffle(implementations);

            for (var implementation : implementations) {
                var name = implementation.getClass().getSimpleName();
                prepare.accept(implementation);
                var duration = measureTime(() -> action.accept(implementation));

                var currentResults = results.getOrDefault(
                        name,
                        new ArrayList<>(REPEATS)
                );
                currentResults.add(duration);
                results.put(
                        name,
                        currentResults
                );
            }

        }

        var finalResults = results.entrySet()
                .stream()
                .map(
                        (entry) -> {
                            System.out.println(entry.getValue());
                            return Statistics.calculateStatistics(entry.getKey(), entry.getValue());
                        }
                )
                .sorted(Comparator.comparing(Statistics.Result::mean))
                .toList();

        finalResults
                .forEach(System.out::println);

        finalResults
                .forEach(it -> System.out.println(it.toMarkDownRow()));
    }

    static int[] BIG_LIST = new Random(666).ints(10000).toArray();
    static int[] SMALL_LIST = new Random(666).ints(1000).toArray();
    static int REPEATS = 10;


    private static Duration measureTime(Runnable action) {
        var start = Instant.now();
        action.run();
        var stop = Instant.now();
        return Duration.between(start, stop);
    }
}
