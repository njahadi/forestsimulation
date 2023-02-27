import processing.core.PImage;
import java.util.List;

public interface Entity {
    String getId();
    Point getPosition();
    void setPosition(Point point);
    List<PImage> getImages();
    int getImageIndex();
    void setImageIndex();
    default String log(){
        return this.getId().isEmpty() ? null :
                String.format("%s %d %d %d", this.getId(), this.getPosition().getX(), this.getPosition().getY(), this.getImageIndex());
    }
    default void nextImage(){
        this.setImageIndex();
    }
    default PImage getCurrentImage(){
        return this.getImages().get(this.getImageIndex() % this.getImages().size());
    }
}
