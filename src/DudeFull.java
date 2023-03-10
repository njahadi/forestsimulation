import processing.core.PImage;

import java.util.*;

public class DudeFull extends Dude{

    public DudeFull(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int resourceLimit) {
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore){
        Entity dude = new DudeNotFull(this.getId(), this.getPosition(), this.getImages(), this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());

        world.removeEntity(scheduler, this);

        world.addEntity(dude);
        ((ExecuteActivity)dude).scheduleActions(scheduler, world, imageStore);
        return false;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(House.class)));

        if (fullTarget.isPresent() && this.moveTo(world, fullTarget.get(), scheduler)) {
            this.transform(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.getActionPeriod());
        }
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler){
        if (this.getPosition().adjacent(target.getPosition())) {
            return true;
        } else {
            PathingStrategy strat = new SingleStepPathingStrategy();
            List<Point> path = strat.computePath(this.getPosition(), target.getPosition(),
                    (p) -> world.withinBounds(p) && world.getOccupancyCell(p) == null || world.getOccupancyCell(p).getClass() == Stump.class,
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
}
