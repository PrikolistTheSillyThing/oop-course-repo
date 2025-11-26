import oop_practice.coffee.*;
import oop_practice.barista.Barista;

public class Main {
    public static void main (String[] args) {
        var barista = new Barista();
        PumpkinSpiceLatte psl = barista.makePumpkinSpiceLatte(Intensity.LIGHT, 50, 30);
    }
}
