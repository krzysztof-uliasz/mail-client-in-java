import javax.swing.*;
import java.awt.*;

public class AddContactWindow extends JFrame {
    private String name;
    private String surname;
    private String email;
    private final JTextField nameField;
    private final JTextField surnameField;
    private final JTextField emailField;

    public AddContactWindow() throws HeadlessException {

        JFrame frame = new JFrame("Utwórz nowy kontakt");

        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));

        JLabel name = new JLabel("Imię:");
        nameField = new JTextField(20);

        JLabel surname = new JLabel("Nazwisko:");
        surnameField = new JTextField(20);

        JLabel email = new JLabel("Email:");
        emailField = new JTextField(20);

        JButton addButton = new JButton("Dodaj");
        JButton cancelButton = new JButton("Anuluj");

        addButton.addActionListener(event -> {

            verifyName(nameField.getText());
            verifySurname(surnameField.getText());
            verifyEmail(emailField.getText());

            if (verifyName(nameField.getText()) && verifySurname(surnameField.getText()) && verifyEmail(emailField.getText())) {
                this.name = nameField.getText();
                this.surname = surnameField.getText();
                this.email = emailField.getText();

                if (Main.checkForExistingContact(this.email)) {
                    JOptionPane.showConfirmDialog(frame, "A contact with this email already exists.", "Contact already exists", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }
                else if (Main.checkForExistingContactName(this.name + " " + this.surname)) {
                    JOptionPane.showConfirmDialog(frame, "A contact with this name already exists.", "Contact already exists", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                } else {
                    Main.addContact(this.name, this.surname, this.email);
                    ExistingRecipientWindow.addContactToListModel(this.name + " " + this.surname);
                    frame.setVisible(false);
                }
            }
        });

        cancelButton.addActionListener(event -> {
            frame.setVisible(false);
        });

        Color niceMint = new Color(0x73EEB4);
        Color nicePink = new Color(0xFDC3E0);
        panel.setBackground(niceMint);
        addButton.setBackground(nicePink);
        cancelButton.setBackground(nicePink);

        panel.add(name);
        panel.add(nameField);
        panel.add(surname);
        panel.add(surnameField);
        panel.add(email);
        panel.add(emailField);
        panel.add(addButton);
        panel.add(cancelButton);
        panel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
    public boolean verifyName(String name) {
        if (name.trim().isEmpty()) {
            nameField.setBorder(BorderFactory.createLineBorder((Color.RED), 2));
            return false;
        }
        else {
            nameField.setBorder(null);
            return true;
        }
    }
    public boolean verifySurname(String surname) {
        if (surname.trim().isEmpty()) {
            surnameField.setBorder(BorderFactory.createLineBorder((Color.RED), 2));
            return false;
        }
        else {
            surnameField.setBorder(null);
            return true;
        }
    }
    public boolean verifyEmail(String email) {
        if (!email.matches("[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z]+")) {
            emailField.setBorder(BorderFactory.createLineBorder((Color.RED), 2));
            return false;
        }
        else {
            emailField.setBorder(null);
            return true;
        }
    }
}
