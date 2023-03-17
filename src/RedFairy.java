import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RedFairy extends ExecuteActivity implements Movable{
    public RedFairy(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod){
        super(id, position, images, actionPeriod, animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        Optional<Entity> redFairyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Dude.class)));

        if (redFairyTarget.isPresent()) {
            Point tgtPos = redFairyTarget.get().getPosition();

            if (this.moveTo(world, redFairyTarget.get(), scheduler)) {

                Entity gravestone = new Gravestone(WorldModel.GRAVESTONE_KEY + "_" + redFairyTarget.get().getId(), tgtPos, imageStore.getImageList(WorldModel.GRAVESTONE_KEY));

                world.addEntity(gravestone);
                ((ExecuteActivity)gravestone).scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.getActionPeriod());
    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler){
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
