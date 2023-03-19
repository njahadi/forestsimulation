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
}
