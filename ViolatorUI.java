import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViolatorUI extends JFrame implements ActionListener {

    JTextField vehicleField;
    JTextArea output;
    JButton viewBtn, payBtn;

    ViolatorUI() {
        setTitle("Violator Panel");
        setSize(400, 400);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Vehicle Number:"));
        vehicleField = new JTextField(15);
        add(vehicleField);

        viewBtn = new JButton("View Challan");
        payBtn = new JButton("Pay Now");

        add(viewBtn);
        add(payBtn);

        output = new JTextArea(10, 30);
        add(output);

        viewBtn.addActionListener(this);
        payBtn.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        String vNum = vehicleField.getText();
        Violation v = TrafficSystem.search(vNum);

        if (e.getSource() == viewBtn) {
            if (v != null) {
                output.setText( "Vehicle: "+ v.vehicleNumber +
                        "\nOwner: " + v.ownerName +
                        "\nViolation: "+ v.violationType +
                        "\nFine: " + v.totalFine +
                        "\nStatus: " + (v.paid ? "Paid": "Unpaid"));
            } else {
                output.setText("No record found.");
            }
        }

        if (e.getSource() == payBtn) {
            if (v != null) {
                v.payFine();
                output.append("\nPaid Successfully!");
            }
        }
    }
    public static void main(String[] args) {
        new ViolatorUI(); 
    }
}

