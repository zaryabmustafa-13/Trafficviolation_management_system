import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Violation {
    String vehicleNumber;
    String ownerName;
    String violationType;
    int fine;

    Violation(String v, String o, String type, int fine) {
        this.vehicleNumber = v;
        this.ownerName = o;
        this.violationType = type;
        this.fine = fine;
    }
}

class TrafficSystem {
    static ArrayList<Violation> list = new ArrayList<>();

    static int checkPrevious(String vehicleNumber) {
        int count = 0;
        for (Violation v : list) {
            if (v.vehicleNumber.equals(vehicleNumber)) {
                count++;
            }
        }
        return count;
    }

    static Violation generateChallan(String vNum, String name, String type) {
        int baseFine = 2000;
        int previous = checkPrevious(vNum);

        if (previous > 0) {
            baseFine += previous * 500;
        }

        Violation v = new Violation(vNum, name, type, baseFine);
        list.add(v);
        return v;
    }
}

public class TrafficUI extends JFrame implements ActionListener {

    JTextField vehicleField, nameField;
    JComboBox<String> violationBox;
    JTextArea receiptArea;
    JButton generateBtn;

    TrafficUI() {
        setTitle("Traffic Challan System");
        setSize(500, 500);
        setLayout(new FlowLayout());

        add(new JLabel("Vehicle Number:"));
        vehicleField = new JTextField(15);
        add(vehicleField);

        add(new JLabel("owner Name:"));
        nameField = new JTextField(15);
        add(nameField);

        add(new JLabel("Violation Type:"));
        String[] violations = {"Signal Jump", "Over Speed", "No Helmet"};
        violationBox = new JComboBox<>(violations);
        add(violationBox);

        generateBtn = new JButton("Generate Challan");
        add(generateBtn);

        receiptArea = new JTextArea(15, 35);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(receiptArea);

        generateBtn.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String vNum = vehicleField.getText();
        String name = nameField.getText();
        String type = (String) violationBox.getSelectedItem();

        int previous = TrafficSystem.checkPrevious(vNum);
        Violation v = TrafficSystem.generateChallan(vNum, name, type);

        // 🧾 Receipt Format
        receiptArea.setText("");
        receiptArea.append("=================================\n");
        receiptArea.append("      TRAFFIC CHALLAN\n");
        receiptArea.append("=================================\n");
        receiptArea.append("Vehicle No : " + v.vehicleNumber + "\n");
        receiptArea.append("Owner Name : " + v.ownerName + "\n");
        receiptArea.append("Violation  : " + v.violationType + "\n");
        receiptArea.append("---------------------------------\n");
        receiptArea.append("Fine Amount: Rs. " + v.fine + "\n");
        receiptArea.append("---------------------------------\n");

        if (previous > 0) {
            receiptArea.append("⚠ Previous Violations: " + previous + "\n");
            receiptArea.append("Status: REPEAT OFFENDER\n");
        } else {
            receiptArea.append("No Previous Violations\n");
        }

        receiptArea.append("=================================\n");
        receiptArea.append("       PAY WITHIN DUE DATE\n");
        receiptArea.append("=================================\n");
    }

    public static void main(String[] args) {
        new TrafficUI();
    }
}


