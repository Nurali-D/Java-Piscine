package ex03;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Program {

    private static final String urlsFile = "/home/dn/Desktop/java_piscine/day03/ex03/files_urls.txt";
    private static Queue<String> urls;

    public static void main(String[] args) {

        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.out.println("Input argument \"--threadsCount=n\"");
            System.exit(-1);
        }
        int threadsCount = Integer.parseInt(args[0].substring(15));
        if (threadsCount <= 0) {
            System.out.println("Invalid argument. input n > 0");
            System.exit(-1);
        }
        parseUrlsFile();
        for (int i = 0 ; i < threadsCount; i++)
            new FileDownloaderThread().start();
    }

    private static void parseUrlsFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(urlsFile))) {
            urls = new LinkedList<String>();
            String line = "";
            while ((line = br.readLine()) != null) {
                urls.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized String getUrl() {
        return urls.poll();
    }
}
