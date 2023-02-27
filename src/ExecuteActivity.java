import processing.core.PImage;
import java.util.List;

public abstract class ExecuteActivity implements Animate{
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;
    private final double actionPeriod;
    private final double animationPeriod;

    public ExecuteActivity(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod){
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.actionPeriod = actionPeriod;
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
    public void setImageIndex(){
        this.imageIndex = this.imageIndex + 1;
    }
    public double getActionPeriod(){
        return this.actionPeriod;
    }
    public double getAnimationPeriod(){
        return this.animationPeriod;
    }


    public Action createAnimationAction(int repeatCount) {
        return new Animation(this, repeatCount);
    }
    public Action createActivityAction(WorldModel world, ImageStore imageStore) {
        return new Activity(this, world, imageStore);
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore){
        scheduler.scheduleEvent(this, this.createActivityAction(world, imageStore), this.actionPeriod);
        scheduler.scheduleEvent(this, this.createAnimationAction(0), this.animationPeriod);
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

}
