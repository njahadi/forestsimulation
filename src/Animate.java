public interface Animate extends Entity{
    double getAnimationPeriod();
    Action createAnimationAction(int repeatCount);
}
