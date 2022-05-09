package ex00;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Program {

    private static final String SIGNATURES_FILE = "/home/dn/Desktop/day02/ex00/signatures.txt";
    private static final String RESULT_FILE ="/home/dn/Desktop/day02/ex00/result.txt";

    public static void main(String[] args) {
        Signatures signatures = new Signatures(SIGNATURES_FILE);
        int maxLength = 0;
        Map<String[], String> signaturesMap = signatures.getMap();
        for (String[] array : signaturesMap.keySet()) {
            if (maxLength < array.length) {
                maxLength = array.length;
            }
        }
        FileSignatureParser fsp = new FileSignatureParser(signaturesMap, maxLength);
        ArrayList<String> filesSignatures = fsp.getResultList();
        try (FileOutputStream fos = new FileOutputStream(RESULT_FILE)) {
           for (String s : filesSignatures) {
                fos.write(s.getBytes());
                fos.write('\n');
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}