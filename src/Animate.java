public interface Animate extends Entity{
    double getAnimationPeriod();
    Action createAnimationAction(int repeatCount);
    default void nextImage(){
        this.setImageIndex();
    }
}
