import processing.core.PImage;

import java.util.List;

public abstract class Transform extends ExecuteActivity{

    public Transform(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod){
        super(id, position, images, actionPeriod, animationPeriod);
    }
    public abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);

}