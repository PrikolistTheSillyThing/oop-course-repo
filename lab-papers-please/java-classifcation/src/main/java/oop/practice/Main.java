package oop.practice;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

enum Universe {
    STAR_WARS,
    MARVEL,
    HITCHHIKER,
    LORD_OF_THE_RINGS,
    UNDEFINED  // for individuals with insufficient info
}

 /* class Universe {
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
*/

class Individual {
    private int id;
    private boolean isHumanoid;
    private String planet;
    private Integer age;
    private List<String> traits;
    private Universe universe;

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

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
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
   public Integer getAge() {
       return age;
   }
   public List<String> getTraits() {
       return traits;
   }
    public Universe getUniverse() {
        return universe;
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
    private static boolean hasAllTraits(Individual individual, String... requiredTraits) {
        if (individual.getTraits() == null) return false;
        for (String trait : requiredTraits) {
            if (!individual.getTraits().contains(trait)) {
                return false;
            }
        }
        return true;
    }
    private static void classifyIndividual(Individual individual) {
        // Asgardian (Marvel)
        if (individual.isHumanoid()
                && individual.getAge() != null  // Check if age exists
                && individual.getAge() >= 0 && individual.getAge() <= 5000
                && "Asgard".equals(individual.getPlanet())
                && hasAllTraits(individual, "BLONDE", "TALL")) {
            individual.setUniverse(Universe.MARVEL);
        }
        // Wookie (Star Wars)
        else if (!individual.isHumanoid()
                && individual.getAge() != null  // Check if age exists
                && individual.getAge() >= 0 && individual.getAge() <= 400
                && "Kashyyyk".equals(individual.getPlanet())
                && hasAllTraits(individual, "HAIRY", "TALL")) {
            individual.setUniverse(Universe.STAR_WARS);
        }
        // Ewok (Star Wars)
        else if (!individual.isHumanoid()
                && individual.getAge() != null  // Check if age exists
                && individual.getAge() >= 0 && individual.getAge() <= 60
                && "Endor".equals(individual.getPlanet())
                && hasAllTraits(individual, "SHORT", "HAIRY")) {
            individual.setUniverse(Universe.STAR_WARS);
        }
        // Betelgeusian (Hitchhiker)
        else if (individual.isHumanoid()
                && individual.getAge() != null  // Check if age exists
                && individual.getAge() >= 0 && individual.getAge() <= 100
                && "Betelgeuse".equals(individual.getPlanet())
                && hasAllTraits(individual, "EXTRA_ARMS", "EXTRA_HEAD")) {
            individual.setUniverse(Universe.HITCHHIKER);
        }
        // Vogon (Hitchhiker)
        else if (!individual.isHumanoid()
                && individual.getAge() != null  // Check if age exists
                && individual.getAge() >= 0 && individual.getAge() <= 200
                && "Vogsphere".equals(individual.getPlanet())
                && hasAllTraits(individual, "GREEN", "BULKY")) {
            individual.setUniverse(Universe.HITCHHIKER);
        }
        // Elf (Lord of the Rings)
        else if (individual.isHumanoid()
                && individual.getAge() != null  // Check if age exists
                && individual.getAge() >= 0  // No upper limit for elves
                && "Earth".equals(individual.getPlanet())
                && hasAllTraits(individual, "BLONDE", "POINTY_EARS")) {
            individual.setUniverse(Universe.LORD_OF_THE_RINGS);
        }
        // Dwarf (Lord of the Rings)
        else if (individual.isHumanoid()
                && individual.getAge() != null  // Check if age exists
                && individual.getAge() >= 0 && individual.getAge() <= 200
                && "Earth".equals(individual.getPlanet())
                && hasAllTraits(individual, "SHORT", "BULKY")) {
            individual.setUniverse(Universe.LORD_OF_THE_RINGS);
        }
        // If none match, it's undefined
        else {
            individual.setUniverse(Universe.UNDEFINED);
        }
    }

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

        System.out.println("Only IDs: ");
        for (Individual individual : individuals) {
            System.out.println("ID: " +individual.getId());
        }

        System.out.println("Humanoids only: ");
        for (Individual individual : individuals) {
            if (individual.isHumanoid()) {
                System.out.println("ID: " +individual.getId()+", From " +  individual.getPlanet());
            }
        }

        System.out.println("----Classifying Individuals----");
        for (Individual individual : individuals) {
            classifyIndividual(individual);
        }

        // Print results by universe
        System.out.println("\n----STAR WARS Universe----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.STAR_WARS) {
                System.out.println("ID: " + individual.getId() + ", from " + individual.getPlanet());
            }
        }

        System.out.println("\n----MARVEL Universe----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.MARVEL) {
                System.out.println("ID: " + individual.getId() + ", from " + individual.getPlanet());
            }
        }

        System.out.println("\n----HITCHHIKER Universe----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.HITCHHIKER) {
                System.out.println("ID: " + individual.getId() + ", from " + individual.getPlanet());
            }
        }

        System.out.println("\n----LORD OF THE RINGS Universe----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.LORD_OF_THE_RINGS) {
                System.out.println("ID: " + individual.getId() + ", from " + individual.getPlanet());
            }
        }

        System.out.println("\n----UNDEFINED Universe----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.UNDEFINED) {
                System.out.println("ID: " + individual.getId() + " - Insufficient information");
            }
        }

    }
}