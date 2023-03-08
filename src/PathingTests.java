import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;
public class PathingTests {
    @Test
    public void testSingleStepNoObstacles() {
        // Grid for testing --> 2D array
        GridValues[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };

        PathingStrategy ps = new SingleStepPathingStrategy();
        List<Point> path = ps.computePath(
                new Point(0, 0), new Point(2, 2), // start, end
                (p) -> withinBounds(p, grid) && grid[p.y][p.x], // canPassThrough
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        // expected path => [(0, 1)] <=
        assertEquals(path, Arrays.asList(new Point(0, 1)));
    }
}
