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

    public void compareSize(Display m) {

    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("HELLO WORLD");
    }
}