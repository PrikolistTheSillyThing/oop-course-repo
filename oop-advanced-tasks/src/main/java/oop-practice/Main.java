enum Intensity {
    LIGHT,
    NORMAL,
    STRONG
}

enum SyrupType {
    MACADAMIA,
    VANILLA,
    COCONUT,
    CARAMEL,
    CHOCOLATE,
    POPCORN
}

class Coffee {
    private Intensity coffeeIntensity;
    private final String name = "Coffee";

    public Coffee() {
        this.coffeeIntensity = Intensity.NORMAL;
    }

    public Coffee(Intensity coffeeIntensity) {
        this.coffeeIntensity = coffeeIntensity;
    }

    public Intensity getCoffeeIntensity() {
        return coffeeIntensity;
    }

    public void setCoffeeIntensity(Intensity coffeeIntensity) {
        this.coffeeIntensity = coffeeIntensity;
    }

    public String getName() {
        return name;
    }

    public void printCoffeeDetails() {
        System.out.println("Coffee intensity: " + coffeeIntensity);
    }

}

class Cappuccino extends Coffee {
    private int mlOfMilk;
    private final String coffee = "Cappuccino";

    public Cappuccino() {
        super();
        this.mlOfMilk = 0;
    }

    public Cappuccino(Intensity coffeeIntensity, int mlOfMilk) {
        super(coffeeIntensity);
        this.mlOfMilk = mlOfMilk;
    }

    public int getMlOfMilk() {
        return mlOfMilk;
    }

    public void setMlOfMilk(int mlOfMilk) {
        this.mlOfMilk = mlOfMilk;
    }

    public String getCoffee() {
        return coffee;
    }

    public void printCoffeeDetails() {
        super.printCoffeeDetails();
        System.out.println("Cappuccino milk: " + getMlOfMilk() + " ml");
    }
}

class Americano extends Coffee {
    private int mlOfWater;
    private final String coffeeName = "Americano";

    public Americano() {
        super();
        this.mlOfWater = 0;
    }

    public Americano(Intensity coffeeIntensity, int mlOfWater) {
        super(coffeeIntensity);
        this.mlOfWater = mlOfWater;
    }

    public int getMlOfWater() {
        return mlOfWater;
    }

    public void setMlOfWater(int mlOfWater) {
        this.mlOfWater = mlOfWater;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void printCoffeeDetails() {
        super.printCoffeeDetails();
        System.out.println("Americano water: " + getMlOfWater() + " ml");
    }
}

class PumpkinSpiceLatte extends Cappuccino {
    private int mgOfPumpkinSpice;
    private final String name = "Pumpkin Spice Latte";

    public PumpkinSpiceLatte() {
        super();
        this.mgOfPumpkinSpice = 0;
    }

    public PumpkinSpiceLatte(Intensity intensityOfCoffee, int mlOfMilk, int mgOfPumpkinSpice) {
        super(intensityOfCoffee, mlOfMilk);
        this.mgOfPumpkinSpice = mgOfPumpkinSpice;
    }

    public int getMgOfPumpkinSpice() {
        return mgOfPumpkinSpice;
    }

    public void setMgOfPumpkinSpice(int mgOfPumpkinSpice) {
        this.mgOfPumpkinSpice = mgOfPumpkinSpice;
    }

    public String getName() {
        return name;
    }

    public void printCoffeeDetails() {
        super.printCoffeeDetails();
        System.out.println("Pumpkin spice: "  + getMgOfPumpkinSpice() + " mg");
    }
}

class SyrupCappuccino extends Cappuccino {
    private SyrupType syrup;
    private final String coffeeName = "Syrup Cappuccino";

    public SyrupCappuccino() {
        super();
        this.syrup = SyrupType.VANILLA;
    }

    public SyrupCappuccino(Intensity coffeeIntensity, int mlOfMilk, SyrupType syrup) {
        super(coffeeIntensity, mlOfMilk);
        this.syrup = syrup;
    }

    public void setSyrup(SyrupType syrup) {
        this.syrup = syrup;
    }

    public SyrupType getSyrup() {
        return syrup;
    }

    public String  getCoffeeName() {
        return coffeeName;
    }

    public void  printCoffeeDetails() {
        super.printCoffeeDetails();
        System.out.println("Syrup in the cappuccino: " + syrup);
    }
}

class Barista {

    private void brewCoffee(Intensity intensity) {
        System.out.println("Intensity set to " + intensity);
    }

    private void addMilk(int mlOfMilk) {
        System.out.println("Adding " + mlOfMilk + " ml of milk");
    }

    public Coffee makeCoffee(Intensity intensity) {
        System.out.println("Making Coffee");
        brewCoffee(intensity);
        return new Coffee(intensity);
    }

    public Cappuccino makeCappuccino(Intensity intensity, int mlOfMilk) {
        System.out.println("Making Cappuccino");
        brewCoffee(intensity);
        addMilk(mlOfMilk);
        return new Cappuccino(intensity, mlOfMilk);
    }

    public PumpkinSpiceLatte makePumpkinSpiceLatte(Intensity intensity, int mlOfMilk, int mgOfPumpkinSpice) {
        System.out.println("Making Pumpkin Spice Latte");
        brewCoffee(intensity);
        addMilk(mlOfMilk);
        System.out.println("Adding " + mgOfPumpkinSpice + " mg of pumpkin spice");
        return new PumpkinSpiceLatte(intensity, mlOfMilk, mgOfPumpkinSpice);
    }

    public SyrupCappuccino makeSyrupCappuccino(Intensity intensity, int mlOfMilk, SyrupType syrup) {
        System.out.println("Making Syrup Cappuccino");
        brewCoffee(intensity);
        addMilk(mlOfMilk);
        System.out.println("Syrup set to " + syrup);
        return new SyrupCappuccino(intensity, mlOfMilk, syrup);
    }

    public Americano makeAmericano(Intensity intensity, int mlOfWater) {
        System.out.println("Making Americano");
        brewCoffee(intensity);
        System.out.println("Adding " + mlOfWater + " ml of water");
        return new Americano(intensity, mlOfWater);
    }
}

public class Main {
    public static void main (String[] args) {
        var barista = new Barista();
        PumpkinSpiceLatte psl = barista.makePumpkinSpiceLatte(Intensity.STRONG, 120, 50);
    }
}
