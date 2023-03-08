public class WorldNode {
    private final Point start;
    private final Point target;
    private final Point position;
    private final int h;
    private final int g;
    private final int f;

    public WorldNode(Point start, Point target, Point position){
        this.start = start;
        this.position = position;
        this.target = target;
        this.g = manhattanDistance(this.start, this.position);
        this.h = manhattanDistance(this.position, this.target);
        this.f = this.g + this.h;
    }

    public Point getPos() {
        return this.position;
    }
    public Point getTarget() {
        return this.target;
    }
    public int getH() {
        return this.h;
    }
    public int getG() {
        return this.g;
    }
    public int getF(){
        return this.f;
    }
    public int manhattanDistance(Point position, Point target){
        return Math.abs(position.getX() - target.getX()) + Math.abs(position.getY() - target.getY());
    }
}
