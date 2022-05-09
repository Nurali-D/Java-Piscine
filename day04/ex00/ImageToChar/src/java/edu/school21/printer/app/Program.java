package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import java.io.IOException;

public class Program {

    public static void main(String[] args) {
        if ((args.length != 3) || (args[0].length() != 1) || (args[1].length() != 1)) {
            System.out.println("Invalid arguments");
            System.out.println("Input: [WHITE CHAR] [BLACK CHAR] [PATH TO IMAGE.BMP]");
            System.exit(-1);
        }
        char whiteChar = args[0].charAt(0);
        char blackChar = args[1].charAt(0);
        String file = args[2];
        char[][] image2d = null;
        try {
            image2d = Logic.readBmpToArray(file, whiteChar, blackChar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printImage2d(image2d);
    }

    private static void printImage2d(char[][] array2D) {
        for (int x = 0; x < array2D.length; x++) {
            for (int y = 0; y < array2D[x].length; y++) {
                System.out.print(array2D[x][y]);
            }
            System.out.println();
        }
    }
}