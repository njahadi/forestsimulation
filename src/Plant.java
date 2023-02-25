import processing.core.PImage;

import java.util.List;

public abstract class Plant extends Transform {
    private int health;

    public Plant(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health){
        super(id, position, images, actionPeriod, animationPeriod);
        this.health = health;
    }

    public int getHealth(){
        return this.health;
    }

    public void incrementHealth(){
        this.health++;
    }

    public void decrementHealth(){
        this.health--;
    }

}
