package oop.practice;
import java.io.Reader;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class Universe {
    private final String name;
    private final List<String> individuals;

    public Universe(String name, List<String> individuals) {
        this.name = name;
        this.individuals = individuals;
    }

    public String getName() {
        return name;
    }

    public List<String> getIndividuals() {
        return individuals;
    }
}

class Files {
    void createFile() {
        File file = new  File("lab-papers-please\\java-classifcation\\src\\main\\resources\\input.json");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.toPath().getFileName());
            }
            else  {
                System.out.println("File already exists: " + file.toPath().getFileName());
            }
        }
        catch(IOException e) {
            System.out.println("File not found: " + file.toPath().getFileName());
            e.printStackTrace();
        }
    }
    void readFile(File obj) {
        try (Scanner  scanner = new Scanner(obj)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Data: ");
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + obj.toPath().getFileName());
            e.printStackTrace();
        }
    }

    void readJsonFile(File obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(obj);
            System.out.println("Full JSON: ");
            System.out.println(root.toPrettyString());

            if (root.isArray()) {
                System.out.println("Entries: ");
                for (JsonNode node : root) {
                    System.out.println(node);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + obj.toPath().getFileName());
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> uniIndividuals = List.of("Courier 6", "Caesar", "General Oliver", "Mr. House");
        Universe universe = new Universe("Fallout: New Vegas", uniIndividuals);

        System.out.println("Universe: " + universe.getName());
        System.out.print("Individuals: ");

        for (int i = 0; i < universe.getIndividuals().size(); i++) {
            System.out.print(universe.getIndividuals().get(i));
            if (i < universe.getIndividuals().size() - 1) {
                System.out.print(", ");
            }
        }
        Files files = new Files();
        files.createFile();
        File fileObj = new File("lab-papers-please\\java-classifcation\\src\\main\\resources\\input.json");
        // files.readFile(fileObj);
        files.readJsonFile(fileObj);
    }
}