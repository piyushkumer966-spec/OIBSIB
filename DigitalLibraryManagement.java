import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitalLibraryManagement {
    
    // Book blueprint class representation mapping properties
    static class Book {
        int id;
        String title;
        String category;
        boolean isIssued;

        public Book(int id, String title, String category) {
            this.id = id;
            this.title = title;
            this.category = category;
            this.isIssued = false;
        }
    }

    private static final List<Book> libraryCatalog = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-populating seed record elements into the central array catalog database
        libraryCatalog.add(new Book(101, "Effective Java", "Programming"));
        libraryCatalog.add(new Book(102, "Introduction to Algorithms", "Computer Science"));
        libraryCatalog.add(new Book(103, "Head First Design Patterns", "Programming"));

        while (true) {
            System.out.println("\n=== Digital Library Dashboard ===");
            System.out.println("1. Access Admin Terminal Module");
            System.out.println("2. Access User Browsing Module");
            System.out.println("3. Turn Off System");
            System.out.print("Select choice: ");
            int mode = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (mode) {
                case 1:
                    runAdminModule();
                    break;
                case 2:
                    runUserModule();
                    break;
                case 3:
                    System.out.println("Library structural operational server offline. Goodbye!");
                    return;
                default:
                    System.out.println("Unknown command input. Try again.");
            }
        }
    }

    // --- ADMIN MODULE (Full Control permissions) ---
    private static void runAdminModule() {
        System.out.println("\n--- Admin Management Engine ---");
        System.out.println("1. Add New Book Record Entry");
        System.out.println("2. Modify/Update Existing Record Title");
        System.out.println("3. Delete Book Record");
        System.out.print("Select process action: ");
        int ops = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (ops) {
            case 1: // Add Entry
                System.out.print("Enter target Book ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Book Title String: ");
                String title = scanner.nextLine();
                System.out.print("Enter Classification Category: ");
                String cat = scanner.nextLine();
                
                libraryCatalog.add(new Book(id, title, cat));
                System.out.println("Record appended successfully.");
                break;

            case 2: // Modify Entry
                System.out.print("Enter Book ID to Modify: ");
                int modId = scanner.nextInt();
                scanner.nextLine();
                boolean foundMod = false;
                for (Book b : libraryCatalog) {
                    if (b.id == modId) {
                        System.out.print("Update Title String text: ");
                        b.title = scanner.nextLine();
                        System.out.println("Record modification structural updates saved.");
                        foundMod = true;
                        break;
                    }
                }
                if (!foundMod) {
                    System.out.println("Record item reference targeting search missed.");
                }
                break;

            case 3: // Delete Entry
                System.out.print("Enter targeting Book ID to purge: ");
                int delId = scanner.nextInt();
                boolean removed = libraryCatalog.removeIf(book -> book.id == delId);
                if (removed) {
                    System.out.println("Deletion sequence execution complete.");
                } else {
                    System.out.println("No matching Book ID found to delete.");
                }
                break;
                
            default:
                System.out.println("Invalid Admin command selection.");
        }
    }

    // --- USER MODULE (Limited View Access) ---
    private static void runUserModule() {
        while (true) {
            System.out.println("\n--- User Central Catalog Access ---");
            System.out.println("1. List All Books");
            System.out.println("2. Filter Search via Keyword");
            System.out.println("3. Issue/Return a Book Simulation");
            System.out.println("4. Query Help Support Desk Email");
            System.out.println("5. Back to Main Menu");
            System.out.print("Selection option: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userChoice) {
                case 1: // List All Books
                    System.out.println("\n-------------------------------------------------------------");
                    System.out.printf("%-10s %-30s %-20s %-10s\n", "ID", "Title", "Category", "Issued Status");
                    System.out.println("-------------------------------------------------------------");
                    for (Book b : libraryCatalog) {
                        System.out.printf("%-10d %-30s %-20s %-10s\n", b.id, b.title, b.category, b.isIssued ? "YES" : "NO");
                    }
                    System.out.println("-------------------------------------------------------------");
                    break;

                case 2: // Filter Search via Keyword
                    System.out.print("Enter context title query keyword match: ");
                    String term = scanner.nextLine().toLowerCase();
                    System.out.println("\nSearch Results:");
                    for (Book b : libraryCatalog) {
                        if (b.title.toLowerCase().contains(term) || b.category.toLowerCase().contains(term)) {
                            System.out.println("[ID: " + b.id + "] " + b.title + " (" + b.category + ") - Issued: " + (b.isIssued ? "Yes" : "No"));
                        }
                    }
                    break;

                case 3: // Issue/Return Book Simulation
                    System.out.print("Enter target Book ID: ");
                    int targetId = scanner.nextInt();
                    System.out.println("1. Issue Book\n2. Return Book");
                    System.out.print("Choose action: ");
                    int action = scanner.nextInt();
                    
                    boolean foundBook = false;
                    for (Book b : libraryCatalog) {
                        if (b.id == targetId) {
                            foundBook = true;
                            if (action == 1) {
                                if (b.isIssued) {
                                    System.out.println("Sorry, this book is already issued out.");
                                } else {
                                    b.isIssued = true;
                                    System.out.println("Book '" + b.title + "' has been successfully issued to you.");
                                }
                            } else if (action == 2) {
                                if (!b.isIssued) {
                                    System.out.println("This book is already sitting in the catalog shelves.");
                                } else {
                                    b.isIssued = false;
                                    System.out.println("Book '" + b.title + "' has been successfully returned.");
                                }
                            }
                            break;
                        }
                    }
                    if (!foundBook) {
                        System.out.println("Book ID matching your request not found.");
                    }
                    break;

                case 4: // Query/Help Support Mail desk
                    System.out.print("Type your support queries / email contents desk: ");
                    String userQuery = scanner.nextLine();
                    System.out.println("Your message structural tracking package dispatching logs successfully uploaded to support queues.");
                    break;

                case 5: // Exit User Menu
                    return;

                default:
                    System.out.println("Invalid user tracking selection parameter.");
            }
        }
    }
}