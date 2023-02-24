import processing.core.PImage;
import java.util.List;

public interface Entity {
    String getId();
    Point getPosition();
    List<PImage> getImages();
    int getImageIndex();
    String log();
    void nextImage();
    PImage getCurrentImage();
}
