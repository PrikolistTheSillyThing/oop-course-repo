import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

enum Universe {
    STAR_WARS,
    MARVEL,
    HITCHHIKER,
    LORD_OF_THE_RINGS,
    UNDEFINED
}

class Individual {
    private int id;
    private Boolean isHumanoid;
    private String originPlanet;
    private Integer age;
    private List<String> physicalTraits;
    private Universe universe;

    public Individual() {}

    public void setIsHumanoid(Boolean isHumanoid) {
        this.isHumanoid = isHumanoid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhysicalTraits(List<String> physicalTraits) {
        this.physicalTraits = physicalTraits;
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

    public String getOriginPlanet() {
        return originPlanet;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getPhysicalTraits() {
        return physicalTraits;
    }

    public Universe getUniverse() {
        return universe;
    }
}

class Files {
    List<Individual> readJsonFile(File obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Individual> individuals = mapper.readValue(
                    obj, new TypeReference<List<Individual>>() {}
            );
            return individuals;
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + obj.getName());
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
                    .writeValue(file, output);
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

    // checks for ALL traits. 1 missing - fail

    private static boolean hasAllTraits(Individual individual, String... requiredTraits) {
        if (individual.getPhysicalTraits() == null) return false;
        for (String trait : requiredTraits) {
            if (!individual.getPhysicalTraits().contains(trait)) {
                return false;
            }
        }
        return true;
    }
    // string... for 0 1 or multiple arguments
    private static boolean hasIdentifyingInfo(Individual individual, String expectedPlanet, Boolean expectedHumanoid, int minAge, int maxAge, String... expectedTraits) {
        int evidencePoints = 0;

        //planet - 2 points

        if (expectedPlanet.equals(individual.getOriginPlanet())) {
            evidencePoints += 2;
        }

        // traits - 2 points (EVERY EXPECTED TRAIT!!!!)

        if (individual.getPhysicalTraits() != null && !individual.getPhysicalTraits().isEmpty()) {
            if (hasAllTraits(individual, expectedTraits)) {
                evidencePoints += 2;
            }
        }

        //humanoid - only 1 point

        if (individual.isHumanoid() != null && individual.isHumanoid() == expectedHumanoid) {
            evidencePoints += 1;
        }

        // age - 1 point
        if (individual.getAge() != null && individual.getAge() >= minAge && individual.getAge() <= maxAge) {
            evidencePoints += 1;
        }

        // older than 5000 - ELF!! straight up
        if (maxAge == Integer.MAX_VALUE && individual.getAge() != null && individual.getAge() > 5000) {
            return true;
        }

        // at least 2 evidence points to classify
        return evidencePoints >= 2;
    }

    // create empty list to collect all universes person may belong to..
    private static void classifyIndividual(Individual individual) {
        List<Universe> possibleUniverses = new ArrayList<>();


        // run through each species check. if can be species - add universe to list

        if (canBeAsgardian(individual)) {
            possibleUniverses.add(Universe.MARVEL);
        }

        if (canBeWookie(individual)) {
            possibleUniverses.add(Universe.STAR_WARS);
        }

        if (canBeEwok(individual)) {
            possibleUniverses.add(Universe.STAR_WARS);
        }

        if (canBeBetelgeusian(individual)) {
            possibleUniverses.add(Universe.HITCHHIKER);
        }

        if (canBeVogon(individual)) {
            possibleUniverses.add(Universe.HITCHHIKER);
        }

        if (canBeElf(individual)) {
            possibleUniverses.add(Universe.LORD_OF_THE_RINGS);
        }

        if (canBeDwarf(individual)) {
            possibleUniverses.add(Universe.LORD_OF_THE_RINGS);
        }

        // if any universe works - pick the first. if none - undefined

        if (!possibleUniverses.isEmpty()) {
            individual.setUniverse(possibleUniverses.get(0));
        } else {
            individual.setUniverse(Universe.UNDEFINED);
            System.out.println("UNDEFINED ID " + individual.getId() + ": humanoid=" +
                    individual.isHumanoid() + ", planet=" + individual.getOriginPlanet() +
                    ", age=" + individual.getAge() + ", traits=" + individual.getPhysicalTraits());
        }
    }

    // if data contradicts - fail immediately
    // if data is missing (null) - not used in classification

    private static boolean canBeAsgardian(Individual individual) {
        if (individual.isHumanoid() != null && !individual.isHumanoid()) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 5000)) return false;
        if (individual.getOriginPlanet() != null && !"ASGARD".equals(individual.getOriginPlanet())) return false;

        // if hard constraints passed - delegate to scorung system
        return hasIdentifyingInfo(individual, "ASGARD", true, 0, 5000, "BLONDE", "TALL");
    }

    private static boolean canBeWookie(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid()) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 400)) return false;
        if (individual.getOriginPlanet() != null && !"KASHYYYK".equals(individual.getOriginPlanet())) return false;

        return hasIdentifyingInfo(individual, "KASHYYYK", false, 0, 400, "HAIRY", "TALL");
    }

    private static boolean canBeEwok(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid()) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 60)) return false;
        if (individual.getOriginPlanet() != null && !"ENDOR".equals(individual.getOriginPlanet())) return false;

        return hasIdentifyingInfo(individual, "ENDOR", false, 0, 60, "SHORT", "HAIRY");
    }

    private static boolean canBeBetelgeusian(Individual individual) {
        if (individual.isHumanoid() != null && !individual.isHumanoid()) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 100)) return false;
        if (individual.getOriginPlanet() != null && !"BETELGEUSE".equals(individual.getOriginPlanet())) return false;

        return hasIdentifyingInfo(individual, "BETELGEUSE", true, 0, 100, "EXTRA_ARMS", "EXTRA_HEAD");
    }

    private static boolean canBeVogon(Individual individual) {
        if (individual.isHumanoid() != null && individual.isHumanoid()) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 200)) return false;
        if (individual.getOriginPlanet() != null && !"VOGSPHERE".equals(individual.getOriginPlanet())) return false;

        return hasIdentifyingInfo(individual, "VOGSPHERE", false, 0, 200, "GREEN", "BULKY");
    }

    private static boolean canBeElf(Individual individual) {
        if (individual.isHumanoid() != null && !individual.isHumanoid()) return false;
        if (individual.getAge() != null && individual.getAge() < 0) return false;
        if (individual.getOriginPlanet() != null && !"EARTH".equals(individual.getOriginPlanet())) return false;

        return hasIdentifyingInfo(individual, "EARTH", true, 0, Integer.MAX_VALUE, "BLONDE", "POINTY_EARS");
    }

    private static boolean canBeDwarf(Individual individual) {
        if (individual.isHumanoid() != null && !individual.isHumanoid()) return false;
        if (individual.getAge() != null && (individual.getAge() < 0 || individual.getAge() > 200)) return false;
        if (individual.getOriginPlanet() != null && !"EARTH".equals(individual.getOriginPlanet())) return false;

        return hasIdentifyingInfo(individual, "EARTH", true, 0, 200, "SHORT", "BULKY");
    }

    public static void main(String[] args) {
        Files files = new Files();

        // read from the correct input.json at lab-papers-please root
        File fileObj = new File("lab-papers-please/input.json");
        List<Individual> individuals = files.readJsonFile(fileObj);
        System.out.println("Total individuals: " + individuals.size());

        System.out.println("\nClassifying Individuals");
        for (Individual individual : individuals) {
            classifyIndividual(individual);
        }

        // print results by universe
        // count individuals by universe
        int starWarsCount = 0;
        int marvelCount = 0;
        int hitchhikerCount = 0;
        int lordOfTheRingsCount = 0;
        int undefinedCount = 0;

        for (Individual individual : individuals) {
            switch (individual.getUniverse()) {
                case STAR_WARS:
                    starWarsCount++;
                    break;
                case MARVEL:
                    marvelCount++;
                    break;
                case HITCHHIKER:
                    hitchhikerCount++;
                    break;
                case LORD_OF_THE_RINGS:
                    lordOfTheRingsCount++;
                    break;
                case UNDEFINED:
                    undefinedCount++;
                    break;
            }
        }

        System.out.println("\n----STAR WARS Universe (" + starWarsCount + " individuals)----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.STAR_WARS) {
                System.out.println("ID: " + individual.getId() + ", from " + individual.getOriginPlanet());
            }
        }

        System.out.println("\nMARVEL Universe (" + marvelCount + " individuals)----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.MARVEL) {
                System.out.println("ID: " + individual.getId() + ", from " + individual.getOriginPlanet());
            }
        }

        System.out.println("\nHITCHHIKER Universe (" + hitchhikerCount + " individuals)----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.HITCHHIKER) {
                System.out.println("ID: " + individual.getId() + ", from " + individual.getOriginPlanet());
            }
        }

        System.out.println("\nLORD OF THE RINGS Universe (" + lordOfTheRingsCount + " individuals)----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.LORD_OF_THE_RINGS) {
                System.out.println("ID: " + individual.getId() + ", from " + individual.getOriginPlanet());
            }
        }

        System.out.println("\nUNDEFINED Universe (" + undefinedCount + " individuals)----");
        for (Individual individual : individuals) {
            if (individual.getUniverse() == Universe.UNDEFINED) {
                System.out.println("ID: " + individual.getId() + " - Insufficient information");
            }
        }

        // Print summary
        System.out.println("Total individuals processed: " + individuals.size());
        System.out.println("Star Wars:        " + starWarsCount);
        System.out.println("Marvel:           " + marvelCount);
        System.out.println("Hitchhiker:       " + hitchhikerCount);
        System.out.println("Lord of the Rings: " + lordOfTheRingsCount);
        System.out.println("Undefined:        " + undefinedCount);

        System.out.println("\nWriting output files...");

        // Group individuals by universe
        List<Individual> starWarsIndividuals = new ArrayList<>();
        List<Individual> marvelIndividuals = new ArrayList<>();
        List<Individual> hitchhikerIndividuals = new ArrayList<>();
        List<Individual> lordOfTheRingsIndividuals = new ArrayList<>();

        for (Individual individual : individuals) {
            switch (individual.getUniverse()) {
                case MARVEL:
                    marvelIndividuals.add(individual);
                    break;
                case HITCHHIKER:
                    hitchhikerIndividuals.add(individual);
                    break;
                case LORD_OF_THE_RINGS:
                    lordOfTheRingsIndividuals.add(individual);
                    break;
                case STAR_WARS:
                    starWarsIndividuals.add(individual);
                    break;
            }
        }

        // Write output files
        UniverseOutput starWarsOutput = new UniverseOutput("starWars", starWarsIndividuals);
        UniverseOutput marvelOutput = new UniverseOutput("marvel", marvelIndividuals);
        UniverseOutput hitchhikerOutput = new UniverseOutput("hitchhiker", hitchhikerIndividuals);
        UniverseOutput lordOfTheRingsOutput = new UniverseOutput("lordOfTheRings", lordOfTheRingsIndividuals);

        files.writeJsonFile("lab-papers-please/java-classification/src/output/starWars.json", starWarsOutput);
        files.writeJsonFile("lab-papers-please/java-classification/src/output/marvel.json", marvelOutput);
        files.writeJsonFile("lab-papers-please/java-classification/src/output/hitchhiker.json", hitchhikerOutput);
        files.writeJsonFile("lab-papers-please/java-classification/src/output/lordOfTheRings.json", lordOfTheRingsOutput);
    }
}