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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getPpi() {
        return ppi;
    }

    public String getModel() {
        return model;
    }

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
    }

    public void compareSharpness(Display m) {
        System.out.println("Comparing sharpness: ");
        System.out.println(this.model + " has " + this.ppi + "ppi");
        System.out.println(m.model + " has " + m.ppi + "ppi");

        if (this.ppi > m.ppi) {
            System.out.println("Result: " + this.model + " has more PPI than" + m.model);
        }

        if (this.ppi < m.ppi) {
            System.out.println("Result: " + m.model + " has more PPI than" + this.model);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("HELLO WORLD");
    }
}