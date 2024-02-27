import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static final Map<String, BufferedImage> images = new HashMap<>();

    public ImageLoader(){
        loadImages();
    }

    private void loadImages() {
        try {
            images.put("Volvo240", ImageIO.read(ImageLoader.class.getResourceAsStream("/pics/Volvo240.jpg")));
            images.put("Saab95", ImageIO.read(ImageLoader.class.getResourceAsStream("/pics/Saab95.jpg")));
            images.put("Scania", ImageIO.read(ImageLoader.class.getResourceAsStream("/pics/Scania.jpg")));
            images.put("VolvoWorkshop", ImageIO.read(ImageLoader.class.getResourceAsStream("/pics/VolvoBrand.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getImage(String key) {
        return images.get(key);
    }
}
