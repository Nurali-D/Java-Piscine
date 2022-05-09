package ex03;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloaderThread extends Thread {

    private static int filesRead = 0;

    @Override
    public void run() {
        String url = "";
        while ((url = Program.getUrl()) != null) {
            downloadFile(url);
        }
    }

    private static void downloadFile(String url) {
        try {
            Path file = Paths.get(url);
            if (Files.exists(file.getFileName())) {
                System.out.println("File " + file.getFileName() + "File already exists!");
                return;
            }
            int fileCount;
            synchronized (FileDownloaderThread.class) {
                fileCount = filesRead++;
            }
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start download file number " + fileCount);
            InputStream is = new URL(url).openStream();
            Files.copy(is, file.getFileName());
            System.out.println(threadName + " finish download file number " + fileCount);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
