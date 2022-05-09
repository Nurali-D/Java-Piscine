package ex00;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class FileSignatureParser {

    private Map<String[], String> map;
    private int maxLength;
    private final String PROCESSED = "PROCESSED";
    private final String UNDEFINED = "UNDEFINED";
    private final String STOPLINE = "42";
    private ArrayList<String> resultList;

    public FileSignatureParser(Map<String[], String> map, int maxLength) {
        this.map = map;
        this.maxLength = maxLength;
        resultList = new ArrayList<String>();
        parseFilesSignatures();

    }

    private void parseFilesSignatures() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while (!(line = scanner.nextLine()).equals(STOPLINE)) {
            readParseFile(line);
        }
        scanner.close();
    }

    private void readParseFile(String path) {
        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] array = new byte[maxLength];
            for (int i = 0; i < maxLength; ++i) {
                array[i] = (byte) fis.read();
            }
            compareWithSignatures(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void compareWithSignatures(byte[] array) {
        String[] strArray = new String[maxLength];
        for (int i = 0; i < array.length; ++i ) {
            strArray[i] = Integer.toHexString(array[i] & 0xff);
            if (strArray[i].length() == 1) {
                strArray[i] = "0" + strArray[i].toUpperCase();
            } else {
                strArray[i] = strArray[i].toUpperCase();
            }
        }

        for (String[] key : map.keySet()) {
            boolean defined = true;
            for (int i = 0; i < key.length; ++i) {
                if (!key[i].equals(strArray[i])) {
                    defined = false;
                    break;
                }
            }
            if (defined) {
                System.out.println(PROCESSED);
                String str = map.get(key);
                resultList.add(str);
                return;
            }
        }
        System.out.println(UNDEFINED);
    }

    public ArrayList<String> getResultList() {
        return resultList;
    }
}
