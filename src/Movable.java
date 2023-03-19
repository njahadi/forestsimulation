import java.util.List;

public interface Movable extends Entity{
    default boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler){
        if (this.getPosition().adjacent(target.getPosition())) {
            world.removeEntity(scheduler, target);
            return true;
        } else {
            PathingStrategy strat = new AStarPathingStrategy();
            List<Point> path = strat.computePath(this.getPosition(), target.getPosition(),
                    (p) -> world.withinBounds(p) && world.getOccupancyCell(p) == null,
                    (p1, p2) -> p1.adjacent(p2) || p1.equals(p2),
                    PathingStrategy.CARDINAL_NEIGHBORS
            );
            Point nextPos;
            if(!path.isEmpty()){
                nextPos = path.get(0);
            }
            else{
                nextPos = this.getPosition();
            }

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }
}
