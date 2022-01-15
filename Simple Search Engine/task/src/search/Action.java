package search;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Action {
    private Scanner scanner = new Scanner(System.in);
    private List<String> listFound = new LinkedList<>();
    private Map<Integer, String> map = new HashMap<>();



    public void start() {
        System.out.println("Enter the number of people: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter all people:");
        for (int i = 0; i < n; i++) {
            map.put(i, scanner.nextLine());
        }
        menu();
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public void start(String fileName) {
        File file = new File(fileName);
        int n = 0;
        try {
            String lines = readFileAsString(fileName);
            Scanner stringScanner = new Scanner(lines);
            while (stringScanner.hasNextLine()) {
                String line = stringScanner.nextLine().trim();
                map.put(n, line);
                n++;
            }
            stringScanner.close();
            menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkListMap(String name, String strategy) {
        listFound.clear();
        String[] names = name.toLowerCase().split("\\s+");

        switch (strategy) {
            case "ALL":
                for (int i = 0; i < map.size(); i++) {
                    boolean contains = true;
                    String words[] = map.get(i).toLowerCase().split("\\s+");
                    for (String s: names) {
                        if (!Arrays.asList(words).contains(s)) {
                            contains = false;
                            break;
                        }
                    }
                    if (contains) {
                        listFound.add(map.get(i));
                    }
                }
                break;
            case "ANY":
                for (int i = 0; i < map.size(); i++) {
                    String words[] = map.get(i).toLowerCase().split("\\s+");
                    for (String s: words) {
                        if (Arrays.asList(names).contains(s)) {
                            listFound.add(map.get(i));
                            break;
                        }
                    }
                }
                break;
            case "NONE":
                for (int i = 0; i < map.size(); i++) {
                    boolean contains = false;
                    String words[] = map.get(i).toLowerCase().split("\\s+");
                    for (String s: names) {
                        if (Arrays.asList(words).contains(s)) {
                            contains = true;
                            break;
                        }
                    }
                    if (!contains) {
                        listFound.add(map.get(i));
                    }
                }
        }

        if (listFound.size() == 0) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(listFound.size() + " persons found:");
            for (String s: listFound) {
                System.out.println(s);
            }
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    findAPerson();
                    break;
                case "2":
                    printAllPeopleMap();
                    break;
                case "0":
                    exit();
                    return;
                default:
                    System.out.println("\nIncorrect option! Try again.");
            }
        }
    }

    private void findAPerson() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();
        System.out.println("\nEnter a name or email to search all suitable people.");
        String name = scanner.nextLine();
        checkListMap(name, strategy);
    }

    private void printAllPeopleMap() {
        System.out.println("=== List of people ===");
        for (var s: map.entrySet()) {
            System.out.println(s.getValue());
        }
    }

    private void exit() {
        System.out.println("\nBye!");
    }

}
