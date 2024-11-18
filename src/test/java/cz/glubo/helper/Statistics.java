package cz.glubo.helper;


import java.time.Duration;
import java.util.List;

public class Statistics {
    protected static double roundToPower(long num, int pow) {
        return Math.round(num / Math.pow(10, pow))
                        * Math.pow(10, pow)
                ;
    }

    public record Result(
            Duration mean,
            Duration stdDev,
            String name
    ) {
        public String toMarkDownRow() {
            int powStdDev = String.valueOf(stdDev.toNanos()).length() - 1;
            var roundedMean = roundToPower(mean.toNanos(), powStdDev);
            var roundedStdDev = roundToPower(stdDev.toNanos(), powStdDev);

            int powMean = String.valueOf(mean.toNanos()).length() - 1;
            var unit = "ns";
            switch (powMean) {
                // ns
                case 1, 2:
                    break;
                // µs
                case 3, 4, 5:
                    unit = "µs";
                    roundedMean /= 1000;
                    roundedStdDev /= 1000;
                    break;
                // ms
                case 6, 7, 8:
                    unit = "ms";
                    roundedMean /= 1000 * 1000;
                    roundedStdDev /= 1000 * 1000;
                    break;
                // s
                default:
                    unit = "s";
                    roundedMean /= 1000 * 1000 * 1000;
                    roundedStdDev /= 1000 * 1000 * 1000;
                    break;
            }

            return "| " + name + " | " + roundedMean + "±" + roundedStdDev + " " + unit + " |";
        }
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
