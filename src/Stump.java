import processing.core.PImage;
import java.util.*;

public class Stump implements Entity{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    public Stump(String id, Point position, List<PImage> images){
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
    public List<PImage> getImages(){
        return this.images;
    }
    public int getImageIndex(){
        return this.imageIndex;
    }
    public String log(){
        return this.id.isEmpty() ? null :
                String.format("%s %d %d %d", this.id, this.position.getX(), this.position.getY(), this.imageIndex);
    }
    public void nextImage(){
        this.imageIndex = this.imageIndex + 1;
    }
    public PImage getCurrentImage(){
        return this.images.get(this.imageIndex % this.images.size());
    }

}
