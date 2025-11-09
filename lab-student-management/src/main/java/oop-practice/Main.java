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
    }

    private static void mainLoop() {
        while (true) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("g - general operations");
            System.out.println("f - faculty operations");
            System.out.println("s - students operations");
            System.out.println("q - quit program");
            System.out.println("\nyour input>");

            String choice = scanner.nextLine();
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

    }

    private static void studentOperations() {

    }

    private static void facultyOperations() {

    }
}

