import processing.core.PImage;

import java.util.*;

public class DudeNotFull extends Dude{
    private int resourceCount;
    public DudeNotFull(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int resourceLimit){
        super(id, position, images, actionPeriod, animationPeriod, resourceLimit);
        this.resourceCount = 0;
    }

    public int getResourceCount(){
        return this.resourceCount;
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore){
        if (this.resourceCount >= this.getResourceLimit()) {
            Entity dude = new DudeFull(this.getId(), this.getPosition(), this.getImages(), this.getActionPeriod(), this.getAnimationPeriod(), this.getResourceLimit());

            world.removeEntity(scheduler, this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dude);
            ((ExecuteActivity)dude).scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (target.isEmpty() || !this.moveTo(world, target.get(), scheduler) || !this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.getActionPeriod());
        }
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler){
        if (this.getPosition().adjacent(target.getPosition())) {
            this.resourceCount += 1;
            ((Plant)target).decrementHealth();
            return true;
        } else {
            PathingStrategy strat = new AStarPathingStrategy();
            List<Point> path = strat.computePath(this.getPosition(), target.getPosition(),
                    (p) -> world.withinBounds(p) && world.getOccupancyCell(p) == null || world.getOccupancyCell(p).getClass() == Stump.class,
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
