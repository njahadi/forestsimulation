import processing.core.PImage;
import java.util.*;
public class Obstacle implements Animate{
    private final double animationPeriod;
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;

    public Obstacle(String id, Point position, List<PImage> images, double animationPeriod){
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.animationPeriod = animationPeriod;
    }

    public String getId(){
        return this.id;
    }
    public Point getPosition(){
        return this.position;
    }
    public void setPosition(Point point){
        this.position = point;
    }
    public List<PImage> getImages(){
        return this.images;
    }
    public int getImageIndex(){
        return this.imageIndex;
    }
    public String log(){
        return this.id.isEmpty() ? null :
                String.format("%s %d %d %d", this.id, this.position.getX(), this.position.getY(), this.imageIndex);
    }
    public void nextImage(){
        this.imageIndex = this.imageIndex + 1;
    }
    public PImage getCurrentImage(){
        return this.images.get(this.imageIndex % this.images.size());
    }

    public double getAnimationPeriod() {
        return this.animationPeriod;
    }

    public Action createAnimationAction(int repeatCount) {
        return new Animation(this, repeatCount);
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore){
        scheduler.scheduleEvent(this, this.createAnimationAction(0), this.animationPeriod);
    }
}
