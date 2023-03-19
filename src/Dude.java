import processing.core.PImage;

import java.util.List;

public abstract class Dude extends Transform implements Movable{
    private final int resourceLimit;

    public Dude(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int resourceLimit){
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit(){
        return this.resourceLimit;
    }
}
