/**
 * A simple class representing a location in 2D space.
 */
public final class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object other) {
        return other instanceof Point && ((Point) other).x == this.x && ((Point) other).y == this.y;
    }

    public int hashCode() {
        int result = 17;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int val){
        this.x = val;
    }
    public void setY(int val){
        this.y = val;
    }

    public boolean adjacent(Point p)
    {
        return (this.x == p.x && Math.abs(this.y - p.y) == 1) ||
                (this.y == p.y && Math.abs(this.x - p.x) == 1);
    }

}
