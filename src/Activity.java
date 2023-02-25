public class Activity implements Action{
    private final Entity entity;
    private final WorldModel world;
    private final ImageStore imageStore;
    private final int repeatCount;

    public Activity(Entity entity, WorldModel world, ImageStore imageStore){
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = 0;
    }

    public void executeAction(EventScheduler scheduler) {
        this.executeActivityAction(scheduler);
    }

    private void executeActivityAction(EventScheduler scheduler) {
        if(this.entity instanceof House || this.entity instanceof Stump || this.entity instanceof Obstacle){
            throw new UnsupportedOperationException(String.format("executeActivityAction not supported for %s", this.entity.getClass()));
        }
        else{
            ((ExecuteActivity)this.entity).executeActivity(this.world, this.imageStore, scheduler);
        }
    }
}
