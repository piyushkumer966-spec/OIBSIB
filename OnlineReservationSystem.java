import java.util.*;

public class OnlineReservationSystem {
    private static final Map<String, String> credentials = new HashMap<>();
    private static final Map<Integer, Ticket> reservationDatabase = new HashMap<>();
    private static int pnrCounter = 1001;
    private static final Scanner scanner = new Scanner(System.in);

    static class Ticket {
        int pnr;
        String passengerName;
        int trainNumber;
        String trainName;
        String classType;
        String dateOfJourney;
        String fromPlace;
        String destination;

        public Ticket(int pnr, String passengerName, int trainNumber, String trainName, 
                      String classType, String dateOfJourney, String fromPlace, String destination) {
            this.pnr = pnr;
            this.passengerName = passengerName;
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.fromPlace = fromPlace;
            this.destination = destination;
        }

        public void displayTicket() {
            System.out.println("\n--- Ticket Details ---");
            System.out.println("PNR Number      : " + pnr);
            System.out.println("Passenger Name  : " + passengerName);
            System.out.println("Train Number    : " + trainNumber);
            System.out.println("Train Name      : " + trainName);
            System.out.println("Class Type      : " + classType);
            System.out.println("Date of Journey : " + dateOfJourney);
            System.out.println("From            : " + fromPlace);
            System.out.println("Destination     : " + destination);
            System.out.println("----------------------");
        }
    }

    public static void main(String[] args) {
        credentials.put("admin", "admin123");
        credentials.put("user1", "pass123");

        System.out.println("=== Welcome to Online Reservation System ===");
        if (!loginModule()) {
            System.out.println("Invalid credentials. Exiting system.");
            return;
        }

        while (true) {
            System.out.println("\n1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    reservationModule();
                    break;
                case 2:
                    cancellationModule();
                    break;
                case 3:
                    System.out.println("Thank you for using the system.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static boolean loginModule() {
        System.out.print("Enter Login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        return credentials.containsKey(loginId) && credentials.get(loginId).equals(password);
    }

    private static void reservationModule() {
        System.out.println("\n--- Reservation Form ---");
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Train Number: ");
        int trainNum = scanner.nextInt();
        scanner.nextLine(); 

        String trainName;
        switch (trainNum) {
            case 12001:
                trainName = "Shatabdi Express";
                break;
            case 12424:
                trainName = "Rajdhani Express";
                break;
            default:
                trainName = "Express Train";
                break;
        }
        System.out.println("Train Name (Auto-filled): " + trainName);

        System.out.print("Enter Class Type (e.g., AC, Sleeper): ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String date = scanner.nextLine();
        System.out.print("From (Source Place): ");
        String from = scanner.nextLine();
        System.out.print("To (Destination): ");
        String to = scanner.nextLine();

        System.out.print("Press 'I' to insert and confirm booking: ");
        String insertConfirmation = scanner.nextLine();

        if (insertConfirmation.equalsIgnoreCase("I")) {
            int currentPnr = pnrCounter++;
            Ticket newTicket = new Ticket(currentPnr, name, trainNum, trainName, classType, date, from, to);
            reservationDatabase.put(currentPnr, newTicket);
            System.out.println("\nBooking Confirmed Successfully!");
            newTicket.displayTicket();
        } else {
            System.out.println("Booking discarded.");
        }
    }

    private static void cancellationModule() {
        System.out.println("\n--- Cancellation Form ---");
        System.out.print("Enter your PNR Number: ");
        int pnr = scanner.nextInt();
        scanner.nextLine(); 

        if (reservationDatabase.containsKey(pnr)) {
            Ticket ticket = reservationDatabase.get(pnr);
            ticket.displayTicket();
            System.out.print("Confirm cancellation? (Press 'OK' to proceed): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("OK")) {
                reservationDatabase.remove(pnr);
                System.out.println("Your ticket with PNR " + pnr + " has been cancelled successfully.");
            } else {
                System.out.println("Cancellation cancelled.");
            }
        } else {
            System.out.println("No booking record found for the provided PNR.");
        }
    }
}