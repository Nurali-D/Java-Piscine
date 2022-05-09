package ex02;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CommandHandler {

    private Path currentDir;

    private void ls() throws IOException {
        List<Path> list;
        Stream<Path> stream = Files.walk(currentDir, 1);
        list = stream.collect(Collectors.toList());
        for (Path path : list) {
            if (!path.equals(currentDir)) {
                long fileSize = 0;
                if (Files.isRegularFile(path)) {
                    fileSize = Files.size(path);
                }else if (Files.isDirectory(path)) {
                    fileSize = getFolderSize(path.toFile());
                }
                String str = "";
                double size = fileSize / (double)1024;
                if (fileSize < 1024) {
                    str = String.format("%.2f", size);
                } else {
                    str = String.format("%.0f", size);
                }
                System.out.println(path.getFileName() + " " + str + " KB");
            }
        }

    }

    private long getFolderSize(File folder)
    {
        long length = 0;
        File[] files = folder.listFiles();
        int count = files.length;
        
        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            }
            else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }

    private void cd(Path newPath) throws IOException {
        Path sumOfPaths = Paths.get(this.getCurrentDir().toString() + "/" + newPath.toString());
        if (Files.exists(sumOfPaths) && Files.isDirectory(sumOfPaths)) {
            this.setCurrentDir(sumOfPaths.normalize());
            System.out.println(this.getCurrentDir());
        }
        else
            System.out.println("Invalid path");
    }

    private void mv(Path srcPath, Path destPath) throws IOException {
        Path tmpSource = Paths.get(this.getCurrentDir() + "/" + srcPath).normalize();
        Path tmpDest = Paths.get(this.getCurrentDir() + "/" + destPath).normalize();

        if (Files.isRegularFile(tmpSource)) {
            if (Files.isDirectory(tmpDest))
                tmpDest = Paths.get(tmpDest + "/" + tmpSource.getFileName());
            Files.move(tmpSource, tmpDest, REPLACE_EXISTING);
        }
        else
            System.out.println("Invalid source file path");

    }

    public CommandHandler(Path currentDir) {
        System.out.println(currentDir);
        if (!Files.exists(currentDir)) {
            try {
                Files.createDirectory(currentDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.currentDir = currentDir;
    }

    public Path getCurrentDir() {
        return currentDir;
    }

    private void setCurrentDir(Path currentDir) {
        this.currentDir = currentDir;
    }

    public void handleCommand(String[] command) throws IOException {
        switch (command[0]) {
            case "ls":
                ls();
                break;
            case "cd":
                cd(Paths.get(command[1]));
                break;
            case "mv":
                mv(Paths.get(command[1]), Paths.get(command[2]));
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("Unknown command. Input \"exit\" to quit");
        }
    }
}
