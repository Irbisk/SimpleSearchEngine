package search;

import javax.security.sasl.SaslClient;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Action action = new Action();

        String fileName = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--data")) {
                fileName = args[i + 1];
            }
        }
        action.start(fileName);
    }
}
