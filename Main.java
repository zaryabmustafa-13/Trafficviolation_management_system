import java.util.*;

// Violation class to store challan data
class Violation {
    String vehicleNumber;
    String violationType;
    int baseFine;
    int totalFine;
    Date issueDate;
    boolean paid;

    public Violation(String vehicleNumber, String violationType, int baseFine, Date issueDate) {
        this.vehicleNumber = vehicleNumber;
        this.violationType = violationType;
        this.baseFine = baseFine;
        this.issueDate = issueDate;
        this.paid = false;

        // BUG FIX 5: Apply 20% late fee only if challan is OVERDUE by more than 30 days
        // Previous bug: issued.before(today) was ALWAYS true for any past date,
        // meaning every single challan got a 20% penalty immediately upon creation.
        Calendar dueDate = Calendar.getInstance();
        dueDate.setTime(issueDate);
        dueDate.add(Calendar.DATE, 30); // 30-day grace period

        Calendar today = Calendar.getInstance();

        if (dueDate.before(today)) {
            this.totalFine = (int)(baseFine * 1.2); // 20% late penalty after 30 days
        } else {
            this.totalFine = baseFine;
        }
    }

    public void show() {
        System.out.println("\n--- Challan Details ---");
        System.out.println("Vehicle Number : " + vehicleNumber);
        System.out.println("Violation Type : " + violationType);
        System.out.println("Base Fine      : Rs." + baseFine);
        System.out.println("Total Fine     : Rs." + totalFine);
        System.out.println("Issue Date     : " + issueDate);
        System.out.println("Status         : " + (paid ? "Paid" : "Unpaid"));
    }

    public void payFine() {
        if (paid) {
            System.out.println("Fine already paid.");
        } else {
            paid = true;
            System.out.println("Fine of Rs." + totalFine + " paid successfully.");
        }
    }
}

// TrafficSystem class to manage all violations
class TrafficSystem {
    // BUG FIX 3: Static list ensures generateChallan() (instance method)
    // and search() (static method) both access the SAME shared list.
    private static List<Violation> records = new ArrayList<>();

    public void generateChallan(String vNum, String type, int fine, Date date) {
        Violation v = new Violation(vNum, type, fine, date);
        records.add(v);
        System.out.println("Challan generated for vehicle: " + vNum);
    }

    public static Violation search(String vehicleNumber) {
        for (Violation v : records) {
            // equalsIgnoreCase handles "ABC123" == "abc123" correctly
            if (v.vehicleNumber.equalsIgnoreCase(vehicleNumber)) {
                return v;
            }
        }
        return null;
    }
}

// BUG FIX 1: The public class MUST be named "Main" — so the file MUST be "Main.java"
// Java rule: filename must exactly match the public class name.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        TrafficSystem trafficSystem = new TrafficSystem();

        do {
            System.out.println("\n--- Traffic System ---");
            System.out.println("1. Generate Challan");
            System.out.println("2. Search Vehicle Record");
            System.out.println("3. Pay Fine");
            System.out.println("4. Exit");

            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline after reading int

            switch (choice) {

                case 1:
                    System.out.print("Enter Vehicle Number: ");
                    String vNum = sc.nextLine();

                    System.out.print("Enter Violation Type: ");
                    String type = sc.nextLine();

                    System.out.print("Enter Base Fine: ");
                    int fine = sc.nextInt();
                    sc.nextLine(); // BUG FIX 4: Without this, the next sc.nextLine() call
                                  // would read an empty string (the leftover '\n'),
                                  // causing the vehicle number input to be skipped.

                    Calendar cal = Calendar.getInstance();
                    // Using current date as issue date (remove -1 offset for real usage)
                    trafficSystem.generateChallan(vNum, type, fine, cal.getTime());
                    break;

                case 2:
                    System.out.print("Enter Vehicle Number: ");
                    String searchNum = sc.nextLine();

                    // BUG FIX 2: Return type must be "Violation" (capital V) — the class name.
                    // Using lowercase "violation" would be a compile error (no such type).
                    Violation v = TrafficSystem.search(searchNum);

                    if (v != null) {
                        v.show();
                    } else {
                        System.out.println("No record found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Vehicle Number: ");
                    String payNum = sc.nextLine();

                    Violation v2 = TrafficSystem.search(payNum);

                    if (v2 != null) {
                        v2.payFine();
                    } else {
                        System.out.println("No record found.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}