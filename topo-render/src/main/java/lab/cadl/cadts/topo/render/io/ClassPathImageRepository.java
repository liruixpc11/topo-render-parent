package lab.cadl.cadts.topo.render.io;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 */
public class ClassPathImageRepository implements ImageRepository {
    @Override
    public Image load(String name) {
        name = StringUtils.strip(name, "/");

        InputStream imageStream = getClass().getClassLoader().getResourceAsStream(name);
        if (imageStream == null) {
            throw new IllegalArgumentException(String.format("Image %s not found", name));
        }

        try {
            return ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
