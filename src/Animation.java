public class Animation implements Action{
    private final Entity entity;
    private final int repeatCount;
    public Animation(Entity entity, int repeatCount){
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler) {
        this.executeAnimationAction(scheduler);
    }

    public void executeAnimationAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity, ((Animate)this.entity).createAnimationAction(Math.max(this.repeatCount - 1, 0)), ((Animate)this.entity).getAnimationPeriod());
        }
    }
}
