import java.util.List;

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
    }
}
