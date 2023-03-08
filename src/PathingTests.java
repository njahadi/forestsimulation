import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;
public class PathingTests {
    @Test
    public void testSingleStepNoObstacles() {
        // Grid for testing --> 2D array
        boolean[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };

        PathingStrategy ps = new SingleStepPathingStrategy();
        List<Point> path = ps.computePath(
                new Point(0, 0), new Point(2, 2), // start, end
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()], // canPassThrough
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        // expected path => [(0, 1)] <=
        assertEquals(path, Arrays.asList(new Point(0, 1)));
    }

    @Test
    public void testAStarNoObstacles(){
        boolean[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                new Point(0, 0), new Point(2, 2),
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()], // canPassThrough
                (p1, p2) -> p1.adjacent(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(path, Arrays.asList(new Point(0,1), new Point(1,1), new Point(1,2)));
    }
    private static boolean withinBounds(Point p, boolean[][] grid)
    {
        return p.getY() >= 0 && p.getY() < grid.length &&
                p.getX() >= 0 && p.getX() < grid[0].length;
    }
}
