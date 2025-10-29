package oop.practice;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

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

class Individual {
    private int id;
    private boolean isHumanoid;
    private String planet;
    private int age;
    private List<String> traits;

    public Individual() {}

    public void setIsHumanoid(boolean isHumanoid) {
        this.isHumanoid = isHumanoid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }

   public int getId() {
       return id;
   }
   public boolean isHumanoid() {
       return isHumanoid;
   }
   public String getPlanet() {
       return planet;
   }
   public int getAge() {
       return age;
   }
   public List<String> getTraits() {
       return traits;
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

    List<Individual> readJsonFile(File obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(obj);
            JsonNode dataNode = root.get("data");

            if (dataNode != null && dataNode.isArray()) {
                List<Individual> individuals = mapper.readValue(
                        dataNode.toString(),
                        new TypeReference<List<Individual>>() {}
                );
                System.out.println("All individuals loaded successfully");
                return individuals;
            }
            else {
                System.out.println("No Data found");
                return new ArrayList<>();
            }
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + obj.toPath().getFileName());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        /*
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
        */



        Files files = new Files();
        files.createFile();
        File fileObj = new File("lab-papers-please\\java-classifcation\\src\\main\\resources\\input.json");
        // files.readFile(fileObj);
        List<Individual> individuals = files.readJsonFile(fileObj);
        System.out.println("Total individuals: " + individuals.size());

        for (Individual individual : individuals) {
            System.out.println(individual.getId());
            System.out.println(individual.isHumanoid());
            System.out.println(individual.getPlanet());
            System.out.println(individual.getAge());
            System.out.println(individual.getTraits());
            System.out.println("---");
        }

        System.out.println("\n=== Only IDs ===");
        for (Individual individual : individuals) {
            System.out.println("ID: " + individual.getId());
        }

        System.out.println("\n Only humanoids");
        for (Individual individual : individuals) {
            if (individual.isHumanoid()) {
                System.out.println("ID: " + individual.getId() + " - Humanoid from " + individual.getPlanet());
            }
        }
    }
}