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
}

class Cappuccino extends Coffee {
    private int mlOfMilk;
    private final String coffee = "Cappuccino";
}

class Americano extends Coffee {
    private int mlOfWater;
    private final String coffeeName = "Americano";
}

class PumpkinSpiceLatte extends Cappuccino {
    private int mgOfPumpkinSpice;
    private final String name = "PumpkinSpiceLatte";
}

class SyrupCappuccino extends Cappuccino {
    private SyrupType syrup;
    private final String coffee = "SyrupCappuccino";
}

public class Main {
    public static void main (String[] args) {

    }
}
