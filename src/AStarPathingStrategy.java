import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class AStarPathingStrategy implements PathingStrategy{
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {
        List<Point> path = new ArrayList<>();
        Comparator<WorldNode> comp = Comparator.comparing(WorldNode::getF); //Comparator comparing WorldNodes by their f value
        PriorityQueue<WorldNode> openList = new PriorityQueue<>(comp); //PriorityQueue to represent open list
        HashSet<Point> closedList = new HashSet<>(); //HashSet to represent closed list

        WorldNode first = new WorldNode(start, end, start); //first node is the starting point
        Predicate<Point> inClosed = closedList::contains; //checks if given point is in the closed list
        Consumer<Point> openAdd = p -> openList.add(new WorldNode(start, end, p)); //adds point to open list

        openList.add(first);

        while(!openList.isEmpty() && !withinReach.test(openList.peek().getPos(), end)){
            Point current = (openList.remove()).getPos(); //current is first point in open list
            potentialNeighbors.apply(current)
                    .filter(canPassThrough) //checks what it can pass through
                    .filter(inClosed) //checks if in closed list
                    .forEach(openAdd); //adds point to open list
            closedList.add(current); //adds current to closed list (visited)
            path.add(current); //adds current to path
        }
        return path; //this might include the starting point, might need ot fix that
    }
}
