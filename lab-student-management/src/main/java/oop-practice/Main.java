import java.util.*;
import java.text.SimpleDateFormat;

// Enumeration for Study Fields
enum StudyField {
    MECHANICAL_ENGINEERING,
    SOFTWARE_ENGINEERING,
    FOOD_TECHNOLOGY,
    URBANISM_ARCHITECTURE,
    VETERINARY_MEDICINE
}

// Student class
class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date enrollmentDate;
    private Date dateOfBirth;
    private boolean graduated;

    public Student(String firstName, String lastName, String email, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = new Date();
        this.graduated = false;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public Date getEnrollmentDate() { return enrollmentDate; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public boolean isGraduated() { return graduated; }
    public void setGraduated(boolean graduated) { this.graduated = graduated; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return firstName + " " + lastName + " (" + email + ") - Enrolled: " +
                sdf.format(enrollmentDate) + (graduated ? " [GRADUATED]" : " [ENROLLED]");
    }
}

// Faculty class
class Faculty {
    private String name;
    private String abbreviation;
    private List<Student> students;
    private StudyField studyField;

    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getAbbreviation() { return abbreviation; }
    public StudyField getStudyField() { return studyField; }
    public List<Student> getStudents() { return students; }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean hasStudent(String email) {
        return students.stream().anyMatch(s -> s.getEmail().equalsIgnoreCase(email));
    }

    public Student findStudentByEmail(String email) {
        return students.stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getEnrolledStudents() {
        List<Student> enrolled = new ArrayList<>();
        for (Student s : students) {
            if (!s.isGraduated()) {
                enrolled.add(s);
            }
        }
        return enrolled;
    }

    public List<Student> getGraduates() {
        List<Student> graduates = new ArrayList<>();
        for (Student s : students) {
            if (s.isGraduated()) {
                graduates.add(s);
            }
        }
        return graduates;
    }

    @Override
    public String toString() {
        return name + " (" + abbreviation + ") - Field: " + studyField +
                " - Students: " + students.size();
    }
}

public class Main {
    private static List<Faculty> faculties = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("Welcome to the student management system.");
        mainLoop();
    }

    private static void mainLoop() {
        while (true) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("g - general operations");
            System.out.println("f - faculty operations");
            System.out.println("s - students operations");
            System.out.println("q - quit program");
            System.out.println("\nyour input>");

            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "g":
                    generalOperations();
                    break;
                case "f":
                    facultyOperations();
                    break;
                case "s":
                    studentOperations();
                    break;
                case "q":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void generalOperations() {
        while (true) {
            System.out.println("\nGeneral Operations");
            System.out.println("What do you want to do?");
            System.out.println("\nnf/<faculty name>/<faculty abbreviation>/<field> - create faculty");
            System.out.println("ss/<student email> - search student and show faculty");
            System.out.println("df - display faculties");
            System.out.println("df/<field> - display all faculties of a field");
            System.out.println("\nb - Back");
            System.out.println("q - quit program");
            System.out.println("\nyour input>");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Bye!");
                return;
            }
            else if (input.startsWith("nf/")) {
                createFaculty(input);
            }
            else if (input.startsWith("ss")) {
                searchStudent(input);
            }
            else if (input.equalsIgnoreCase("df")) {
                displayFaculties();
            }
            else if (input.startsWith("df/")) {
                displayFacultiesByField(input);
            }
            else if (input.equalsIgnoreCase("b")) {
                return;
            }
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void createFaculty(String input) {

    }

    private static void searchStudent(String input) {

    }

    private static void displayFaculties() {

    }

    private static void displayFacultiesByField(String input) {

    }


    private static void facultyOperations() {
        while (true) {
            System.out.println("\nFaculty Operations");
            System.out.println("What do you want to do?");
            System.out.println("\nns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - create student");
            System.out.println("gs/<email> - (g)raduate (s)tudent");
            System.out.println("ds/<faculty abbreviation> - (d)isplay enrolled (s)tudents");
            System.out.println("dg/<faculty abbreviation> - (d)isplay (g)raduated students");
            System.out.println("bf/<faculty abbreviation>/<email> - check if student (b)elongs to (f)aculty");
            System.out.println("\nb - Back");
            System.out.println("q - Quit Program");
            System.out.print("\nyour input> ");

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("b")) {
                return;
            } else if (input.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else if (input.startsWith("ns/")) {
                createStudent(input);
            } else if (input.startsWith("gs/")) {
                graduateStudent(input);
            } else if (input.startsWith("ds/")) {
                displayEnrolledStudents(input);
            } else if (input.startsWith("dg/")) {
                displayGraduates(input);
            } else if (input.startsWith("bf/")) {
                checkStudentBelongsToFaculty(input);
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    private static void createStudent(String input) {

    }

    private static void displayEnrolledStudents(String input) {

    }

    private static void displayGraduates(String input) {

    }

    private static void checkStudentBelongsToFaculty(String input) {

    }

    private static void graduateStudent(String input) {

    }

    private static void studentOperations() {

    }
}

