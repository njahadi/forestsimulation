import processing.core.PImage;

import java.util.List;

public class Tree extends Plant{

    public Tree(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health){
        super(id, position, images, actionPeriod, animationPeriod, health);
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore){
        if (this.getHealth() <= 0) {
            Entity stump = new Stump(WorldModel.STUMP_KEY + "_" + this.getId(), this.getPosition(), imageStore.getImageList(WorldModel.STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        }

        return false;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        if (!this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.getActionPeriod());
        }
    }

}
