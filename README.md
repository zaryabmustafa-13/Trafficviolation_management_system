Traffic Violation Management System
A Java project demonstrating traffic challan generation, vehicle-record
lookup, fine calculation, and payment-status handling. The supplied
source contains a console implementation and Swing GUI prototypes.
Project Features
1. Console-Based Challan System (Main.java)
- Generate a challan by entering a vehicle number, violation type, and
  base fine.
- Search for a vehicle's challan record.
- Display the issue date, base fine, total fine, and payment status.
- Mark an unpaid fine as paid.
- Search vehicle numbers without case sensitivity.
- Apply a 20% late fee when the issue date plus the 30-day grace
  period is earlier than the current date.
The console implementation stores records in a shared in-memory list.
The sample program sets the issue date to the current date when
generating a challan.
2. Challan Generation GUI (TrafficUI.java)
A Java Swing desktop interface with: - Vehicle number and owner name
fields. - A violation selector: Signal Jump, Over Speed, and No
Helmet. - A Generate Challan button. - A receipt area showing the
vehicle, owner, violation, fine, and previous-violation information.

The GUI version starts with a fine of Rs. 2,000 and adds Rs. 500 for
each previous record found for the same vehicle number. Its record
search is case-sensitive in the supplied code.
3. Violator Panel GUI (ViolatorUI.java)
A second Swing interface prototype containing: - A vehicle number
field. - View Challan and Pay Now buttons. - An output area intended to
show challan details and payment feedback.

Integration note: The supplied ViolatorUI expects
TrafficSystem.search() and Violation members such as ownerName,
totalFine, paid, and payFine(). These do not all exist in the
TrafficUI.java version of those classes. The three code sections also
declare duplicate Violation and TrafficSystem class names. As
supplied, they are not one combined compile-ready application and should
be kept in separate files/projects until their shared classes are
unified.
Technologies Used
- Java
- Java Swing (javax.swing)
- AWT and event handling (java.awt, java.awt.event)
- Java Collections (ArrayList, List)
- Scanner for console input
- Date and Calendar for the console version's due-date calculation
Requirements
- Java Development Kit (JDK)
- A Java IDE (such as IntelliJ IDEA, Eclipse, or Visual Studio Code
  with Java extensions) or a terminal
Running the Supplied Implementations
Console version
Save the first code section as Main.java in its own folder, then run:
javac Main.java
java Main
Challan generation GUI
Save the second code section as TrafficUI.java in a separate
folder/project, then run:
javac TrafficUI.java
java TrafficUI
Violator panel
Save the third code section as ViolatorUI.java. It is currently a UI
prototype and requires its dependencies to be reconciled with a shared
Violation model and TrafficSystem class before it can compile and
run with the supplied GUI version.
Current Limitations
- Challan records are stored in memory and are lost when the
  application closes.
- No database or permanent file storage is included.
- The console and GUI implementations use different data models and
  fine-calculation rules.
- The Violator Panel has unresolved dependencies on the supplied GUI
  classes.
- Input validation and error handling are limited.
Possible Future Improvements
- Integrate all interfaces with one shared data model.
- Add database storage for permanent challan records.
- Add input validation and clearer error messages.
- Implement consistent due-date and payment tracking across the
  interfaces.
- Add automated tests for fine calculation, repeat violations,
  searching, and payment.
Author
Zaryab Mustafa
- GitHub: zaryabmustafa-13
- LinkedIn: Zaryab
  Mustafa
