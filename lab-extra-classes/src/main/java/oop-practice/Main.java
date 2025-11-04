class Display {
    private int width;
    private int height;
    private float ppi;
    private String model;

    public Display(int width, int height, float ppi, String model) {
        this.width = width;
        this.height = height;
        this.ppi = ppi;
        this.model = model;
    }

    /*
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setPpi(float ppi) {
        this.ppi = ppi;
    }

    public void setModel(String model) {
        this.model = model;
    }
    */

    private double getDiagonalSize() {
        return Math.sqrt(width * width + height * height);
    }

    public void compareSize(Display m) {
        double thisSize = this.getDiagonalSize();
        double otherSize = m.getDiagonalSize();
        System.out.println("Comparing the sizes: ");
        System.out.println(this.model + " is " + this.width + "x" + this.height +
                ", its diagonal is " + thisSize);
        System.out.println(m.model + " is " + m.width + "x" + m.height +
                ", its diagonal is " + otherSize);

        if (thisSize > otherSize) {
            System.out.println("Result: " + this.model + " is larger than " + m.model);
        }
        else if (thisSize < otherSize) {
            System.out.println("Result: " + m.model + " is larger than " + this.model);
        }
        else {
            System.out.println("Models are the same size.");
        }
        System.out.println();

    }

    public void compareSharpness(Display m) {
        System.out.println("Comparing sharpness: ");
        System.out.println(this.model + " has " + this.ppi + " ppi");
        System.out.println(m.model + " has " + m.ppi + " ppi");

        if (this.ppi > m.ppi) {
            System.out.println("Result: " + this.model + " has more PPI than " + m.model);
        }

        if (this.ppi < m.ppi) {
            System.out.println("Result: " + m.model + " has more PPI than " + this.model);
        }

        else {
            System.out.println("Result: Both have equal sharpness.");
        }
        System.out.println();

    }

    public void compareWithMonitor(Display m) {
        System.out.println("Comparing " + this.model + " with " + m.model);
        double thisSize = this.getDiagonalSize();
        double otherSize = m.getDiagonalSize();

        if (thisSize > otherSize) {
            System.out.println(this.model + " is larger.");
        }
        else if (thisSize < otherSize) {
            System.out.println(m.model + " is larger.");
        }
        else  {
            System.out.println("Same size.");
        }

        if (this.ppi > m.ppi) {
            System.out.println(this.model + " is sharper.");
        }
        else if  (this.ppi < m.ppi) {
            System.out.println(m.model + " is sharper.");
        }
        else {
            System.out.println("Equal sharpness.");
        }

        boolean thisBigger = thisSize > otherSize;
        boolean thisSharper = this.ppi > m.ppi;

        if (thisBigger && thisSharper) {
            System.out.println("Overall, " + this.model + " excels in sharpness and size!");
        }

        else if (!thisBigger && thisSharper) {
            System.out.println("Overall, " + this.model + " is sharper, but smaller than " + m.model);
        }

        else if (thisBigger && !thisSharper) {
            System.out.println("Overall, " + m.model + " is sharper, but smaller than " + this.model);
        }

        else if (!thisBigger && !thisSharper) {
            System.out.println("Overall, " + m.model + " excels in sharpness and size!");
        }
        else {
            System.out.println("Both monitors are identical in size and sharpness!");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        var monitor1 = new Display(1920, 1080, 140.5f, "Cool Monitor 9000");
        var monitor2 = new Display(2560, 1440, 130f, "Ultra Epic Monitor 10000");
        var monitor3 = new Display(3840, 2160, 120f, "The COOLEST MONITOR 12000");

        monitor1.compareSize(monitor2);
        monitor2.compareSharpness(monitor3);
        monitor1.compareWithMonitor(monitor3);
    }
}