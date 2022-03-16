import java.time.Duration;
import java.time.Instant;

public class Timer {
    Instant start;

    public Timer() {
        this.start = Instant.now();
    }

    public long getTime() {
        return Duration.between(start, Instant.now()).toMillis();
    }
}
