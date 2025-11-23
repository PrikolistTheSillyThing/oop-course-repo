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
}

class PumpkinSpiceLatte extends Cappuccino {
    private int mgOfPumpkinSpice;
    private final String name = "PumpkinSpiceLatte";

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
}

class SyrupCappuccino extends Cappuccino {
    private SyrupType syrup;
    private final String coffeeName = "SyrupCappuccino";

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
}

public class Main {
    public static void main (String[] args) {

    }
}
