package org.alcibiade.asciiart.image.rasterize;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Yannick Kirschhoffer <yannick.kirschhoffer@cgi.com>
 */
public class ShapesDisplay {

    public static void main(String... args) throws IOException {
        int sx = 3;
        int sy = 4;

        String resourceBundle = String.format(
                "shapes_%dx%d.properties", sx, sy);

        Properties shapeProperties = new Properties();
        InputStream in = ShapesDisplay.class.getResourceAsStream(resourceBundle);
        shapeProperties.load(in);
        in.close();

        for (int ix = 0; ix < (1 << (sx * sy)); ix++) {
            String shapeChar = shapeProperties.getProperty(Integer.toString(ix));

            System.out.println("--------- " + ix + " / " + shapeChar + " -------------");

            for (int y = 0; y < sy; y++) {
                String line = "";
                for (int x = 0; x < sx; x++) {
                    char c;

                    if ((ix & (1 << (x + sx * y))) != 0) {
                        c = '.';
                    } else {
                        c = '#';
                    }

                    line += c;
                }

                System.out.println(line);
            }
        }
    }

}
