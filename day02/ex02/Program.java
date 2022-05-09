package ex02;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.*;


public class Program {
    public static void main(String[] args) {
        
		if (args.length == 0) {
			System.out.println("Input argument --current-folder=path");
			return;
		}
		Path startPath = null;
        if (args[0].startsWith("--current-folder=")) {
            startPath = Paths.get(args[0].substring(17));
        }
        CommandHandler handler = new CommandHandler(startPath);
        readCommandFromConsole(handler);
    }

    private static void readCommandFromConsole(CommandHandler handler) {
        Scanner scanner = new Scanner(System.in);
        String line;
        while ((line = scanner.nextLine()) != null) {
            try {
                String[] command = line.split(" ");
                handler.handleCommand(command);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}