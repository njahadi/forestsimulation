public class WorldNode {
    private final Point position;
    private final Point target;
    private WorldNode prev;
    private int gScore;

    public WorldNode(Point position, Point target, WorldNode prev, int gScore){
        this.position = position;
        this.target = target;
        this.prev = prev;
        this.gScore = gScore;
    }

    public Point getPos() {
        return this.position;
    }
    public Point getTarget(){
        return this.target;
    }
    public WorldNode getPrev(){
        return this.prev;
    }
    public void setPrev(WorldNode node){
        this.prev = node;
    }
    public int getGScore(){
        return this.gScore;
    }
    public void setGScore(int g){
        this.gScore = g;
    }
    public int manhattanDistance(Point position, Point target){
        return Math.abs(position.getX() - target.getX()) + Math.abs(position.getY() - target.getY());
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!this.getClass().equals(other.getClass())){
            return false;
        }
        return this.position == ((WorldNode) other).getPos();
    }
}
