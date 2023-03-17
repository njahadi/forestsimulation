import processing.core.PImage;
import java.util.List;

public class Gravestone implements Entity{
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;

    public Gravestone(String id, Point position, List<PImage> images){
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
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
}
