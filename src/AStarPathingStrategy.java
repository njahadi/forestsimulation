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
                new PriorityQueue<>(Comparator.comparingInt(WorldNode::getTotal)); //PriorityQueue to represent open list
        HashSet<Point> closedList = new HashSet<>(); //HashSet to represent closed list

        openList.add(new WorldNode(start, end, null,0)); //first node is the starting point

        while(!openList.isEmpty()){
            WorldNode current = openList.poll(); //current is first point in open list
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
                            if(openList.removeIf(node -> node.equals(neighbor) && node.getTotal() > neighbor.getTotal())){
                                openList.add(neighbor);
                            }

//                            WorldNode existing = openList.stream()
//                                    .filter(node -> node.equals(neighbor))
//                                    .findAny()
//                                    .get();
//                            if(existing.getTotal() > neighbor.getTotal()){
//                                openList.remove(existing);
//                                openList.add(neighbor);
//                            }
                        }
                            }

                    ); //adds points to open list
            closedList.add(currentPos);
        }
        return Collections.emptyList();
    }
}
