import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dinosaur extends ExecuteActivity implements Movable{
    public Dinosaur(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod){
        super(id, position, images, actionPeriod, animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        Optional<Entity> dinoTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Fairy.class)));

        if (dinoTarget.isPresent()) {
            Point tgtPos = dinoTarget.get().getPosition();

            if (this.moveTo(world, dinoTarget.get(), scheduler)) {

                Entity redFairy = new RedFairy(WorldModel.REDFAIRY_KEY + "_" + dinoTarget.get().getId(), tgtPos, imageStore.getImageList(WorldModel.REDFAIRY_KEY), 0.5,0.2);

                world.addEntity(redFairy);
                ((ExecuteActivity)redFairy).scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.getActionPeriod());
    }
}
