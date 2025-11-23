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

class Individual {
    private int id;
    private Boolean isHumanoid;
    private String planet;
    private Integer age;
    private List<String> traits;
    private Universe universe;

    public Individual() {}

    public void setIsHumanoid(Boolean isHumanoid) {
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
   public Boolean isHumanoid() {
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
        File file = new  File("lab-papers-please/input.json");
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

    void writeJsonFile(String filename, UniverseOutput output) {
        File file = new File(filename);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filename), output);
            System.out.println("File written successfully: " + filename);
        }
        catch (IOException e) {
            System.out.println("Error writing file: " + filename);
            e.printStackTrace();
        }
    }

}

class UniverseOutput {
    private String name;
    private List<Individual> individuals;

    public UniverseOutput(String name, List<Individual> individuals) {
        this.name = name;
        this.individuals = individuals;
    }

    public String getName() {
        return name;
    }

    public List<Individual> getIndividuals() {
        return individuals;
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
        List<Universe> possibleUniverses = new ArrayList<>();

        // check Asgardian (Marvel)
        if (canBeAsgardian(individual)) {
            possibleUniverses.add(Universe.MARVEL);
        }

        // check Wookie (Star Wars)
        if (canBeWookie(individual)) {
            possibleUniverses.add(Universe.STAR_WARS);
        }

        // check Ewok (Star Wars)
        if (canBeEwok(individual)) {
            possibleUniverses.add(Universe.STAR_WARS);
        }

        // check Betelgeusian (Hitchhiker)
        if (canBeBetelgeusian(individual)) {
            possibleUniverses.add(Universe.HITCHHIKER);
        }

        // check Vogon (Hitchhiker)
        if (canBeVogon(individual)) {
            possibleUniverses.add(Universe.HITCHHIKER);
        }

        // check Elf (Lord of the Rings)
        if (canBeElf(individual)) {
            possibleUniverses.add(Universe.LORD_OF_THE_RINGS);
        }

        // check Dwarf (Lord of the Rings)
        if (canBeDwarf(individual)) {
            possibleUniverses.add(Universe.LORD_OF_THE_RINGS);
        }

        // assign universe (if only one possible, use it, if multiple, pick first, if none, undefined)
        if (!possibleUniverses.isEmpty()) {
            individual.setUniverse(possibleUniverses.get(0));
        } else {
            individual.setUniverse(Universe.UNDEFINED);
        }
    }

    // helper methods - return true if individual COULD be this species
    private static boolean canBeAsgardian(Individual individual) {
        // check if any field CONTRADICTS being Asgardian
        if (individual.isHumanoid() != null && individual.isHumanoid() == false) return false;  // Must be humanoid
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 5000)) return false;
        if (individual.getPlanet() != null && !"Asgard".equals(individual.getPlanet())) return false;
        if (individual.getTraits() != null && !containsMultipleTraits(individual.getTraits(), "BLONDE", "TALL")) return false;

        // if we have at least SOME matching info, classify it
        boolean hasMatchingInfo = false;
        if ("Asgard".equals(individual.getPlanet())) hasMatchingInfo = true;
        if (individual.getTraits() != null && containsMultipleTraits(individual.getTraits(), "BLONDE", "TALL")) hasMatchingInfo = true;
        if (individual.getAge() != null && individual.getAge() >= 0 && individual.getAge() <= 5000) hasMatchingInfo = true;

        return hasMatchingInfo;
    }

    private static boolean canBeWookie(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid() == true) return false;  // must NOT be humanoid
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 400)) return false;
        if (individual.getPlanet() != null && !"Kashyyyk".equals(individual.getPlanet())) return false;
        if (individual.getTraits() != null && !containsMultipleTraits(individual.getTraits(), "HAIRY", "TALL")) return false;

        boolean hasMatchingInfo = false;
        if ("Kashyyyk".equals(individual.getPlanet())) hasMatchingInfo = true;
        if (individual.getTraits() != null && containsMultipleTraits(individual.getTraits(), "HAIRY", "TALL")) hasMatchingInfo = true;
        if (individual.getAge() != null && individual.getAge() >= 0 && individual.getAge() <= 400) hasMatchingInfo = true;

        return hasMatchingInfo;
    }

    private static boolean canBeEwok(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid() == true) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 60)) return false;
        if (individual.getPlanet() != null && !"Endor".equals(individual.getPlanet())) return false;
        if (individual.getTraits() != null && !containsMultipleTraits(individual.getTraits(), "SHORT", "HAIRY")) return false;

        boolean hasMatchingInfo = false;
        if ("Endor".equals(individual.getPlanet())) hasMatchingInfo = true;
        if (individual.getTraits() != null && containsMultipleTraits(individual.getTraits(), "SHORT", "HAIRY")) hasMatchingInfo = true;
        if (individual.getAge() != null && individual.getAge() >= 0 && individual.getAge() <= 60) hasMatchingInfo = true;

        return hasMatchingInfo;
    }

    private static boolean canBeBetelgeusian(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid() == false) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 100)) return false;
        if (individual.getPlanet() != null && !"Betelgeuse".equals(individual.getPlanet())) return false;
        if (individual.getTraits() != null && !containsMultipleTraits(individual.getTraits(), "EXTRA_ARMS", "EXTRA_HEAD")) return false;

        boolean hasMatchingInfo = false;
        if ("Betelgeuse".equals(individual.getPlanet())) hasMatchingInfo = true;
        if (individual.getTraits() != null && containsMultipleTraits(individual.getTraits(), "EXTRA_ARMS", "EXTRA_HEAD")) hasMatchingInfo = true;
        if (individual.getAge() != null && individual.getAge() >= 0 && individual.getAge() <= 100) hasMatchingInfo = true;

        return hasMatchingInfo;
    }

    private static boolean canBeVogon(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid() == true) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 200)) return false;
        if (individual.getPlanet() != null && !"Vogsphere".equals(individual.getPlanet())) return false;
        if (individual.getTraits() != null && !containsMultipleTraits(individual.getTraits(), "GREEN", "BULKY")) return false;

        boolean hasMatchingInfo = false;
        if ("Vogsphere".equals(individual.getPlanet())) hasMatchingInfo = true;
        if (individual.getTraits() != null && containsMultipleTraits(individual.getTraits(), "GREEN", "BULKY")) hasMatchingInfo = true;
        if (individual.getAge() != null && individual.getAge() >= 0 && individual.getAge() <= 200) hasMatchingInfo = true;

        return hasMatchingInfo;
    }

    private static boolean canBeElf(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid() == false) return false;
        if (individual.getAge() != null && individual.getAge() < 0) return false;  // no upper limit
        if (individual.getPlanet() != null && !"Earth".equals(individual.getPlanet())) return false;
        if (individual.getTraits() != null && !containsMultipleTraits(individual.getTraits(), "BLONDE", "POINTY_EARS")) return false;

        boolean hasMatchingInfo = false;
        if ("Earth".equals(individual.getPlanet())) hasMatchingInfo = true;
        if (individual.getTraits() != null && containsMultipleTraits(individual.getTraits(), "BLONDE", "POINTY_EARS")) hasMatchingInfo = true;
        if (individual.getAge() != null && individual.getAge() >= 0) hasMatchingInfo = true;

        return hasMatchingInfo;
    }

    private static boolean canBeDwarf(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid() == false) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 200)) return false;
        if (individual.getPlanet() != null && !"Earth".equals(individual.getPlanet())) return false;
        if (individual.getTraits() != null && !containsMultipleTraits(individual.getTraits(), "SHORT", "BULKY")) return false;

        boolean hasMatchingInfo = false;
        if ("Earth".equals(individual.getPlanet())) hasMatchingInfo = true;
        if (individual.getTraits() != null && containsMultipleTraits(individual.getTraits(), "SHORT", "BULKY")) hasMatchingInfo = true;
        if (individual.getAge() != null && individual.getAge() >= 0 && individual.getAge() <= 200) hasMatchingInfo = true;

        return hasMatchingInfo;
    }

    private static boolean containsMultipleTraits(List<String> traits, String... requiredTraits) {
        if (traits == null) return false;
        int count = 0;
        for (String required : requiredTraits) {
            if (traits.contains(required)) {
                count++;
            }
        }
        return count >= 2;  // at least 2 traits required
    }

    public static void main(String[] args) {
        Files files = new Files();
        files.createFile();
        File fileObj = new File("lab-papers-please/input.json");
        List<Individual> individuals = files.readJsonFile(fileObj);
        System.out.println("Total individuals: " + individuals.size());

        System.out.println("\n----Classifying Individuals----");
        for (Individual individual : individuals) {
            classifyIndividual(individual);
        }

        // print results by universe
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

        System.out.println("\n Writing output files");

        List<Individual> starWarsIndividuals = new ArrayList<>();
        List<Individual> marvelIndividuals = new ArrayList<>();
        List<Individual> hitchhikerIndividuals = new ArrayList<>();
        List<Individual> lordOfTheRingsIndividuals = new ArrayList<>();

        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.MARVEL) {
                marvelIndividuals.add(individual);
            }
            else if (individual.getUniverse() == Universe.HITCHHIKER) {
                hitchhikerIndividuals.add(individual);
            }
            else if (individual.getUniverse() == Universe.LORD_OF_THE_RINGS) {
                lordOfTheRingsIndividuals.add(individual);
            }
            else if (individual.getUniverse() == Universe.STAR_WARS) {
                starWarsIndividuals.add(individual);
            }
        }

        UniverseOutput starWarsOutput = new UniverseOutput("starWars", starWarsIndividuals);
        UniverseOutput marvelOutput = new UniverseOutput("marvel", marvelIndividuals);
        UniverseOutput hitchhikerOutput = new UniverseOutput("hitchhiker", hitchhikerIndividuals);
        UniverseOutput lordOfTheRingsOutput = new UniverseOutput("lordOfTheRings", lordOfTheRingsIndividuals);

        files.writeJsonFile("lab-papers-please/java-classifcation/src/output/starWars.json", starWarsOutput);
        files.writeJsonFile("lab-papers-please/java-classifcation/src/output/marvel.json", marvelOutput);
        files.writeJsonFile("lab-papers-please/java-classifcation/src/output/hitchhiker.json", hitchhikerOutput);
        files.writeJsonFile("lab-papers-please/java-classifcation/src/output/lordOfTheRings.json", lordOfTheRingsOutput);
    }
}