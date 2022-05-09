package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;
import com.diogonunes.jcdp.color.api.Ansi;

public class Logic {

    public static void printImage(String bmpFileName, String col1, String col2)
            throws IOException {
        BufferedImage image = ImageIO.read(new File(bmpFileName));;
        int height = image.getHeight();
        int width = image.getWidth();

        Ansi.BColor color1 = Ansi.BColor.valueOf(col1);
        Ansi.BColor color2 = Ansi.BColor.valueOf(col2);

        ColoredPrinter cp1 = new ColoredPrinter.Builder(1, false).background(color1).build();

        ColoredPrinter cp2 = new ColoredPrinter.Builder(1, false).background(color2).build();


        for (int xPixel = 0; xPixel < height; xPixel++) {
            for (int yPixel = 0; yPixel < width; yPixel++) {
                int color = image.getRGB(yPixel, xPixel);
                if (color== Color.BLACK.getRGB()) {
                    cp1.print(" ");;
                } else {
                    cp2.print(" ");;
                }
            }
            System.out.println();
        }
    }
}
