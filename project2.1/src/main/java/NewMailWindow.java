import javax.swing.*;
import java.awt.*;

public class NewMailWindow extends JFrame {
    private static JTextField recipient;

    public NewMailWindow() throws HeadlessException {

        JFrame frame = new JFrame("Utwórz nowy mail");

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(4,4,4,4);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.05;
        panel.add(new JLabel("DO:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.9;
        recipient = new JTextField();
        panel.add(recipient, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0.05;
        JButton buttonChooseExistingRecipient = new JButton("WYBIERZ");
        panel.add(buttonChooseExistingRecipient, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.05;
        panel.add(new JLabel("TEMAT:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.9;
        JTextField subject = new JTextField();
        panel.add(subject, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        JTextArea messageArea = new JTextArea();
        panel.add(new JScrollPane(messageArea), constraints);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton buttonSend = new JButton("WYŚLIJ");
        JButton buttonCancel = new JButton("ODRZUĆ");
        bottomPanel.add(buttonSend);
        bottomPanel.add(buttonCancel);

        buttonChooseExistingRecipient.addActionListener(event -> {
            new ExistingRecipientWindow();
        });

        buttonCancel.addActionListener(event -> {
            frame.setVisible(false);
        });

        buttonSend.addActionListener(event -> {
            if (recipient.getText().isEmpty()) {
                JOptionPane.showConfirmDialog(frame, "Recipient field cannot be empty.", "No email address", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
            else {
                Mail mail = new Mail(recipient.getText(), subject.getText(), messageArea.getText());
                Main.getListModel().addElement(subject.getText());
                Main.getSentEmails().addLast(mail);
                frame.setVisible(false);
            }
        });

        Color niceMint = new Color(0x73EEB4);
        Color nicePink = new Color(0xFDC3E0);
        panel.setBackground(niceMint);
        bottomPanel.setBackground(niceMint);
        buttonSend.setBackground(nicePink);
        buttonCancel.setBackground(nicePink);
        buttonChooseExistingRecipient.setBackground(nicePink);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(480, 480);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
    public static JTextField getRecipient() {
        return recipient;
    }
}
