import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int userCounter = 0;
        int uidCounter = 0;
        int nologinCounter = 0;
        int commentCounter = 0;
        File text = new File("./" + args[0]);
        Scanner s = null;
        try {
            s = new Scanner(text);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] splitLine = line.split(":");
            if (splitLine[2].equals(("0"))) {
                uidCounter++;
            }
            if (splitLine[6].equals("/sbin/nologin")) {
                nologinCounter++;
            }
            if(splitLine[4].equals("")) {
                commentCounter++;
            }
            userCounter++;
        }
        System.out.println("Users: " + userCounter);
        System.out.println("Users with UID 0: " + uidCounter);
        System.out.println("Users with /sbin/nologin shell: " + nologinCounter);
        System.out.println("Users with empty comment field: " + commentCounter);
    }
}