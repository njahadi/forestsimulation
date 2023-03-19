import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RedFairy extends ExecuteActivity implements Movable{
    public RedFairy(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod){
        super(id, position, images, actionPeriod, animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        Optional<Entity> redFairyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(DudeFull.class, DudeNotFull.class)));

        if (redFairyTarget.isPresent()) {
            Point tgtPos = redFairyTarget.get().getPosition();

            if (this.moveTo(world, redFairyTarget.get(), scheduler)) {

                Entity gravestone = new Gravestone(WorldModel.GRAVESTONE_KEY + "_" + redFairyTarget.get().getId(), tgtPos, imageStore.getImageList(WorldModel.GRAVESTONE_KEY));

                world.addEntity(gravestone);
            }
        }

        scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.getActionPeriod());
    }
}
