package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {

    public static char[][] readBmpToArray(String bmpFileName, char whiteChar, char blackChar)
            throws IOException {
        BufferedImage image = ImageIO.read(new File(bmpFileName));;
        int height = image.getHeight();
        int width = image.getWidth();
        char[][] array2D = new char[height][width];

        for (int xPixel = 0; xPixel < height; xPixel++) {
            for (int yPixel = 0; yPixel < width; yPixel++) {
                int color = image.getRGB(yPixel, xPixel);
                if (color== Color.BLACK.getRGB()) {
                    array2D[xPixel][yPixel] = blackChar;
                } else {
                    array2D[xPixel][yPixel] = whiteChar;
                }
            }
        }
        return array2D;
    }
}
