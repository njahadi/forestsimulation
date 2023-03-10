import java.util.*;
import processing.core.PImage;

public class Fairy extends ExecuteActivity implements Movable {

    public Fairy(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod){
        super(id, position, images, actionPeriod, animationPeriod);
    }
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        Optional<Entity> fairyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Stump.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {

                Entity sapling = new Sapling(WorldModel.SAPLING_KEY + "_" + fairyTarget.get().getId(), tgtPos, imageStore.getImageList(WorldModel.SAPLING_KEY), 0);

                world.addEntity(sapling);
                ((ExecuteActivity)sapling).scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.getActionPeriod());
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler){
        if (this.getPosition().adjacent(target.getPosition())) {
            world.removeEntity(scheduler, target);
            return true;
        } else {
            PathingStrategy strat = new SingleStepPathingStrategy();
            List<Point> path = strat.computePath(this.getPosition(), target.getPosition(),
                    (p) -> world.withinBounds(p) && world.getOccupancyCell(p) == null,
                    (p1, p2) -> p1.adjacent(p2),
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

    public Point nextPosition(WorldModel world, Point destPos){
        int horiz = Integer.signum(destPos.getX() - this.getPosition().getX());
        Point newPos = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.getY() - this.getPosition().getY());
            newPos = new Point(this.getPosition().getX(), this.getPosition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos)) {
                newPos = this.getPosition();
            }
        }

        return newPos;
    }
}
