import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class wordcounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 'text' to input text manually or 'file' to provide a file path:");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("text")) {
            System.out.println("Enter your text:");
            String inputText = scanner.nextLine();
            countWordsAndFrequency(inputText);
        } else if (choice.equals("file")) {
            System.out.println("Enter the path to the file:");
            String filePath = scanner.nextLine();
            try {
                String fileContent = readFile(filePath);
                countWordsAndFrequency(fileContent);
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    public static void countWordsAndFrequency(String text) {
        String[] words = text.split("[\\s.,!?]+");
        int wordCount = words.length;

        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase(); // Convert to lowercase to count words case-insensitively
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        System.out.println("Total word count: " + wordCount);
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}


