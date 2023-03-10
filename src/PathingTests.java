import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;
public class PathingTests {
    @Test
    public void testAStarCornerToCorner3x3(){
        boolean[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(3, path.size());
        boolean contiguous = true;
        for(int i = 0; i+1 < path.size(); i++){
            Point p1 = path.get(i);
            Point p2 = path.get(i+1);
            if(!p1.adjacent(p2)){
                contiguous = false;
            }
        }
        assertTrue(contiguous);
        assertTrue(path.get(0).adjacent(start));
        assertTrue(path.get(path.size()-1).adjacent(end));
    }

    @Test
    public void testAStarNoPath3x3(){
        boolean[][] grid = {
                { true, false, true },
                { false, true, true },
                { true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(0, path.size());
    }

    @Test
    public void testAStarCornerToCorner4x4(){
        boolean[][] grid = {
                { true, true, true, true },
                { true, true, true, true },
                { true, true, true, true },
                { true, true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(3, 3);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(5, path.size());
        boolean contiguous = true;
        for(int i = 0; i+1 < path.size(); i++){
            Point p1 = path.get(i);
            Point p2 = path.get(i+1);
            if(!p1.adjacent(p2)){
                contiguous = false;
            }
        }
        assertTrue(contiguous);
        assertTrue(path.get(0).adjacent(start));
        assertTrue(path.get(path.size()-1).adjacent(end));
    }

    @Test
    public void testAStarStartIsEnd(){
        boolean[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(0, path.size());
    }

    @Test
    public void testAStarStartWithinReachOfEnd(){
        boolean[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(0, 1);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(0, path.size());
    }
    @Test
    public void testAStarDownOneSide(){
        boolean[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(2, 0);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(1, path.size());
        boolean contiguous = true;
        for(int i = 0; i+1 < path.size(); i++){
            Point p1 = path.get(i);
            Point p2 = path.get(i+1);
            if(!p1.adjacent(p2)){
                contiguous = false;
            }
        }
        assertTrue(contiguous);
        assertTrue(path.get(0).adjacent(start));
        assertTrue(path.get(path.size()-1).adjacent(end));
    }

    @Test
    public void testAStarAcrossTop(){
        boolean[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(0, 2);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(1, path.size());
        boolean contiguous = true;
        for(int i = 0; i+1 < path.size(); i++){
            Point p1 = path.get(i);
            Point p2 = path.get(i+1);
            if(!p1.adjacent(p2)){
                contiguous = false;
            }
        }
        assertTrue(contiguous);
        assertTrue(path.get(0).adjacent(start));
        assertTrue(path.get(path.size()-1).adjacent(end));
    }

    @Test
    public void testAStarBottomLeftTopRight(){
        boolean[][] grid = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };
        Point start = new Point(2, 2);
        Point end = new Point(0, 0);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(3, path.size());
        boolean contiguous = true;
        for(int i = 0; i+1 < path.size(); i++){
            Point p1 = path.get(i);
            Point p2 = path.get(i+1);
            if(!p1.adjacent(p2)){
                contiguous = false;
            }
        }
        assertTrue(contiguous);
        assertTrue(path.get(0).adjacent(start));
        assertTrue(path.get(path.size()-1).adjacent(end));
    }

    @Test
    public void testAStarWithObstacles3x3(){
        boolean[][] grid = {
                { true, true, false },
                { true, false, true },
                { true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(3, path.size());
        boolean contiguous = true;
        for(int i = 0; i+1 < path.size(); i++){
            Point p1 = path.get(i);
            Point p2 = path.get(i+1);
            if(!p1.adjacent(p2)){
                contiguous = false;
            }
        }
        assertTrue(contiguous);
        assertTrue(path.get(0).adjacent(start));
        assertTrue(path.get(path.size()-1).adjacent(end));
    }

    @Test
    public void testAStarNoPath4x4(){
        boolean[][] grid = {
                { true, true, false, true },
                { true, true, false, true },
                { false, false, true, true },
                { true, true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(3, 3);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(0, path.size());
    }

    @Test
    public void testAStarObstacles4x4(){
        boolean[][] grid = {
                { true, true, false, true },
                { true, true, false, true },
                { true, false, false, true },
                { true, true, true, true }
        };
        Point start = new Point(0, 0);
        Point end = new Point(3, 3);

        PathingStrategy ps = new AStarPathingStrategy();
        List<Point> path = ps.computePath(
                start, end,
                (p) -> withinBounds(p, grid) && grid[p.getY()][p.getX()],
                (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                PathingStrategy.CARDINAL_NEIGHBORS
        );

        assertEquals(5, path.size());
        boolean contiguous = true;
        for(int i = 0; i+1 < path.size(); i++){
            Point p1 = path.get(i);
            Point p2 = path.get(i+1);
            if(!p1.adjacent(p2)){
                contiguous = false;
            }
        }
        assertTrue(contiguous);
        assertTrue(path.get(0).adjacent(start));
        assertTrue(path.get(path.size()-1).adjacent(end));
    }

    private static boolean withinBounds(Point p, boolean[][] grid)
    {
        return p.getY() >= 0 && p.getY() < grid.length &&
                p.getX() >= 0 && p.getX() < grid[0].length;
    }
}
