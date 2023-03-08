import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class AStarPathingStrategy implements PathingStrategy{
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {
        PriorityQueue<WorldNode> openList =
                new PriorityQueue<>(Comparator.comparingInt(AStarPathingStrategy::getTotal)); //PriorityQueue to represent open list
        HashSet<Point> closedList = new HashSet<>(); //HashSet to represent closed list

        openList.add(new WorldNode(start, end, null,0)); //first node is the starting point

        while(!openList.isEmpty()){
            WorldNode current = openList.remove(); //current is first point in open list
            Point currentPos = current.getPos();
            
            if(withinReach.test(currentPos, end)){
                List<Point> path = new ArrayList<>();
                while (current != null) {
                    path.add(0, current.getPos());
                    current = current.getPrev();
                }
                path.remove(0);
                return path;
            }
            closedList.add(currentPos);

            WorldNode fCurrent = current;
            potentialNeighbors.apply(currentPos)
                    .filter(canPassThrough) //checks what it can pass through
                    .filter(p -> !closedList.contains(p)) //checks if not in closed list
                    .forEach(n -> {
                        WorldNode neighbor = new WorldNode(n, end, fCurrent, fCurrent.getGScore()+1);
                        if (!openList.contains(neighbor)){
                            openList.add(neighbor);
                        }
                        else{
                            WorldNode existing = openList.stream()
                                    .filter(node -> node.equals(neighbor))
                                    .findFirst().get();

                            if(existing.getGScore() > neighbor.getGScore()){
                                existing.setPrev(fCurrent);
                                existing.setGScore(neighbor.getGScore());
                            }
                        }
                            }

                    ); //adds points to open list
        }
        return Collections.emptyList();
    }

    private static int getTotal(WorldNode node) {
        return node.getGScore() + node.manhattanDistance(node.getPos(), node.getTarget());
    }

}
