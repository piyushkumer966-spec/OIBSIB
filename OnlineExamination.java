import java.util.Scanner;

public class OnlineExamination {
    private static final Scanner scanner = new Scanner(System.in);
    private static String password = "password123";
    private static String profileName = "Student";

    public static void main(String[] args) {
        System.out.println("=== Online Exam System Portal ===");
        System.out.print("Enter Username: ");
        String user = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        // 1. Login Module Validation
        if (pass.equals(password)) {
            System.out.println("Login Success! Welcome, " + profileName);
            runExamPortal();
        } else {
            System.out.println("Access Denied. Invalid credentials.");
        }
    }

    private static void runExamPortal() {
        while (true) {
            System.out.println("\n1. Update Profile & Password");
            System.out.println("2. Start Examination Session");
            System.out.println("3. Logout & Quit");
            System.out.print("Select choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    startExamination();
                    break;
                case 3:
                    System.out.println("Session closed successfully. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // 2. Update Profile and Password Module
    private static void updateProfile() {
        System.out.print("Enter new Profile Display Name: ");
        profileName = scanner.nextLine();
        System.out.print("Enter new Password: ");
        password = scanner.nextLine();
        System.out.println("Profile updated successfully!");
    }

    // 3 & 4. MCQ Selection, Timer, and Auto-Submit Module
    private static void startExamination() {
        System.out.println("\n--- Exam Started ---");
        System.out.println("Notice: You have a strict limit of 15 seconds per question!");
        
        String[] questions = {
            "What is the parent class of all classes in Java?\nA) String  B) Object  C) Main  D) File",
            "Which modifier guarantees thread safety inside a runtime environment?\nA) volatile  B) synchronized  C) transient  D) static"
        };
        char[] answers = {'B', 'B'};
        char[] userSelections = new char[2];

        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            System.out.println(questions[i]);

            long startTime = System.currentTimeMillis();
            System.out.print("Your selected option choice (A/B/C/D): ");
            String input = scanner.nextLine().toUpperCase();
            long endTime = System.currentTimeMillis();

            // Check if user exceeded 15 seconds (15000 milliseconds)
            if ((endTime - startTime) > 15000) {
                System.out.println("Time limit expired! Auto-submitting blank response.");
                userSelections[i] = 'X'; 
            } else {
                userSelections[i] = input.isEmpty() ? 'X' : input.charAt(0);
            }
        }

        // Processing final score evaluation
        int score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (userSelections[i] == answers[i]) {
                score++;
            }
        }
        System.out.println("\nExam completed! Your score: " + score + "/" + questions.length);
    }
}