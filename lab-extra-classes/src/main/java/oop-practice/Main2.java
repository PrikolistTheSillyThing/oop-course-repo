import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class FileReader {
    public String readFileIntoString(String path) {
        var content = new StringBuilder();
        var file = new File(path);
        try (var scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                content.append(scanner.nextLine());
                if (scanner.hasNextLine()) {
                    content.append("\n");
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found: " + path);
            e.printStackTrace();
        }
        return content.toString();
    }
}

class TextData {
    private String fileName;
    private String text;
    private int numberOfVowels;
    private int numberOfConsonants;
    private int numberOfLetters;
    private int numberOfSentences;
    private String longestWord;

    public TextData(String fileName, String text) {
        this.fileName = fileName;
        this.text = text;
        calculateVowelsAndConsonants();
        calculateLetters();
        calculateSentences();
        findLongestWord();
    }

    private void calculateVowelsAndConsonants() {
        numberOfVowels = 0;
        numberOfConsonants = 0;
        var lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            var c = lowerText.charAt(i);

            if (Character.isLetter(c)) {
                if (c == 'a' || c == 'e' || c == 'o' || c == 'u' || c == 'i') {
                    numberOfVowels++;
                }
                else {
                    numberOfConsonants++;
                }
            }
        }

    }

    private void calculateLetters() {
        numberOfLetters = 0;
        for (int i = 0; i < text.length(); i++) {
            var c = text.charAt(i);
            if (Character.isLetter(c)) {
                numberOfLetters++;
            }
        }
    }
    private void calculateSentences() {
        numberOfSentences = 0;
        for (int i = 0; i < text.length(); i++) {
            var c = text.charAt(i);
            if (c == '!' || c == '.' || c == '?') {
                numberOfSentences++;
            }
        }
    }

    private void findLongestWord() {
        longestWord = "";
        var words = text.split("\\s+");

        for (var word : words) {
            var cleanedWord = word.replaceAll("[^a-zA-Z]","");
            if (cleanedWord.length() > longestWord.length()) {
                longestWord = cleanedWord;
            }
        }
    }

    public int getNumberOfVowels() {
        return numberOfVowels;
    }

    public int getNumberOfConsonants() {
        return numberOfConsonants;
    }

    public int getNumberOfLetters() {
        return numberOfLetters;
    }

    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public String getFileName() {
        return fileName;
    }

    public String getText() {
        return text;
    }

}

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
