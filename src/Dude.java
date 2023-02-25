import processing.core.PImage;

import java.util.List;

public abstract class Dude extends Transform implements Movable{
    private int resourceLimit;

    public Dude(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int resourceLimit){
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit(){
        return this.resourceLimit;
    }

    public Point nextPosition(WorldModel world, Point destPos){
        int horiz = Integer.signum(destPos.getX() - this.getPosition().getX());
        Point newPos = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

        if (horiz == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).getClass() != Stump.class) {
            int vert = Integer.signum(destPos.getY() - this.getPosition().getY());
            newPos = new Point(this.getPosition().getX(), this.getPosition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).getClass() != Stump.class) {
                newPos = this.getPosition();
            }
        }

        return newPos;
    }

}
