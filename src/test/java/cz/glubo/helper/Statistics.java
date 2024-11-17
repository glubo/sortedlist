package cz.glubo.helper;


import java.time.Duration;
import java.util.List;

public class Statistics {
    public record Result(
            Duration mean,
            Duration stdDev,
            String name
    ) {
    }

    public static Result calculateStatistics(
            String name,
            List<Duration> durations
    ) {
        double sumNs = 0;
        double squaredSumNsNs = 0;

        for (var duration : durations) {
            double value = duration.getNano();
            sumNs += value;
            squaredSumNsNs += value * value;
        }
        double meanNs = sumNs / durations.size();
        double varianceNsNs = squaredSumNsNs / durations.size() - meanNs * meanNs;

        Duration mean = Duration.ofNanos(Math.round(meanNs));
        Duration stdDev = Duration.ofNanos(
                Math.round(
                        Math.sqrt(varianceNsNs)
                )
        );

        return new Result(
                mean,
                stdDev,
                name
        );
    }
}
