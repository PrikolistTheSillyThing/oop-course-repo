package oop_practice.barista;
import oop_practice.coffee.*;

public class Barista {

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
