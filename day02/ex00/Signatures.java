package ex00;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Signatures {
    private Map<String[], String> signMap;
    private String path;
    private int longestSignatureSize;

    public Signatures(String path) {
        this.path = path;
        signMap = new HashMap<String[], String>();
        parseSignatures();
    }

    public Map<String[], String> getMap() {
        return this.signMap;
    }

    private void parseSignatures() {
        try (FileInputStream fis = new FileInputStream(this.path)) {
            int fileLength = (int) new File(this.path).length();
            byte[] array = new byte[fileLength];
            fis.read(array);
            arrayToMap(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void arrayToMap(byte[] array) {
        String str = new String(array);
        String[] splitByBsn = str.split("\n");
        for (String s : splitByBsn) {
            String[] splitByComma = s.split(",");
            String[] splitBySpace = splitByComma[1].trim().split(" ");
            signMap.put(splitBySpace, splitByComma[0]);
        }
    }
}
