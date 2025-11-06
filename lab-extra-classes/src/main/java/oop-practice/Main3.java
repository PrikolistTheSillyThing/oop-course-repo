import java.util.ArrayList;
import java.util.List;

class Assistant {
    private String assistantName;
    private List<Display> assignedDisplays;

    public Assistant(String assistantName) {
        this.assistantName = assistantName;
        this.assignedDisplays = new ArrayList<>();
    }

    public void assignDisplay(Display d) {
        assignedDisplays.add(d);
        System.out.println("Display '" + d.getModel() + "' has been assigned to " + assistantName);
    }

    public void assist() {
        if (assignedDisplays.isEmpty()) {
            System.out.println(assistantName + ": No displays to compare");
            return;
        }

        if (assignedDisplays.size() == 1) {
            System.out.println(assistantName + ": Only one display: " + assignedDisplays.get(0).getModel());
            return;
        }

        System.out.println("===== " + assistantName + "'s display comparison assistant =====\n");

        for (int i = 0; i < assignedDisplays.size() - 1; i++) {
            var current = assignedDisplays.get(i);
            var next = assignedDisplays.get(i + 1);

            System.out.println("--- Comparison " + (i + 1) + " ---");
            current.compareWithMonitor(next);
        }

        recommendBestDisplay();
    }

    private void recommendBestDisplay() {
        System.out.println("===== " + assistantName + "'s Recommendation =====");

        var bestOverall = assignedDisplays.get(0);
        var largest = assignedDisplays.get(0);
        var sharpest = assignedDisplays.get(0);

        // Find the largest and sharpest displays
        for (Display d : assignedDisplays) {
            if (d.getWidth() * d.getHeight() > largest.getWidth() * largest.getHeight()) {
                largest = d;
            }
            if (d.getPpi() > sharpest.getPpi()) {
                sharpest = d;
            }
        }

        System.out.println("Largest display: " + largest.getModel() +
                " (" + largest.getWidth() + "x" + largest.getHeight() + ")");
        System.out.println("Sharpest display: " + sharpest.getModel() +
                " (" + sharpest.getPpi() + " PPI)");

        if (largest == sharpest) {
            bestOverall = largest;
            System.out.println("\n" + assistantName + " recommends: " + bestOverall.getModel() +
                    " - It's both the largest AND sharpest.");
        } else {
            System.out.println("\n" + assistantName + " says: It depends on the priority.");
            System.out.println("  - Choose " + largest.getModel() + " for maximum screen space");
            System.out.println("  - Choose " + sharpest.getModel() + " for best image quality");
        }
        System.out.println();
    }

    public Display buyDisplay(Display d) {
        if (assignedDisplays.remove(d)) {
            System.out.println(assistantName + ": You've purchased " + d.getModel() + "!");
            System.out.println("This display has been removed from the list.");
            return d;
        } else {
            System.out.println(assistantName + ": Sorry, no display with that name in the list.");
            return null;
        }
    }

    public void showAssignedDisplays() {
        System.out.println(assistantName + "'s assigned displays:");
        if (assignedDisplays.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (int i = 0; i < assignedDisplays.size(); i++) {
                Display d = assignedDisplays.get(i);
                System.out.println("  " + (i + 1) + ". " + d.getModel() +
                        " (" + d.getWidth() + "x" + d.getHeight() +
                        ", " + d.getPpi() + " PPI)");
            }
        }
        System.out.println();
    }
}

public class Main3 {
    public static void main(String[] args) {
        Assistant shopAssistant = new Assistant("Alex");

        var monitor1 = new Display(1920, 1080, 92.5f, "Pixel Prophet");
        var monitor2 = new Display(2560, 1440, 109.0f, "GigaView Turbo Supreme");
        var monitor3 = new Display(3840, 2160, 163.2f, "The One Cool Monitor");
        var monitor4 = new Display(2560, 1440, 109.0f, "Vision Crusher Omega Gaming");

        shopAssistant.assignDisplay(monitor1);
        shopAssistant.assignDisplay(monitor2);
        shopAssistant.assignDisplay(monitor3);
        shopAssistant.assignDisplay(monitor4);

        System.out.println();

        shopAssistant.showAssignedDisplays();

        shopAssistant.assist();

        var purchased = shopAssistant.buyDisplay(monitor3);

        System.out.println();

        shopAssistant.showAssignedDisplays();
    }
}