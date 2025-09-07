import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    private static final ArrayList<Mail> sentEmails = new ArrayList<>();
    private static final DefaultListModel<String> listModel = new DefaultListModel<>();
    private static final Map<String, String> addedContacts = new HashMap<>();

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PJATK MAIL");

            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel leftPanel = new JPanel(new BorderLayout());
            JPanel rightPanel = new JPanel(new GridBagLayout());



            //This is the start of the 'topPanel' modification.
            ImageIcon addContactIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("contacticon.png")));
            ImageIcon newMailIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("mailicon.png")));

            JButton buttonNewMail = new JButton(newMailIcon);
            buttonNewMail.setSize(64,64);
            JButton buttonAddContact = new JButton(addContactIcon);
            buttonAddContact.setSize(64,64);

            buttonAddContact.addActionListener(event -> {
                new AddContactWindow();
            });

            buttonNewMail.addActionListener(event -> {
                new NewMailWindow();
            });

            topPanel.add(buttonNewMail);
            topPanel.add(buttonAddContact);



            //This is the start of the 'rightPanel' modification.
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(0,2,4,4);
            constraints.fill = GridBagConstraints.HORIZONTAL;

            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 0.05;
            rightPanel.add(new JLabel("DO:"), constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            constraints.weightx = 0.95;
            JTextField recipient = new JTextField();
            recipient.setEnabled(false);
            rightPanel.add(recipient, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weightx = 0.05;
            rightPanel.add(new JLabel("TEMAT:"), constraints);

            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.weightx = 0.95;
            JTextField subject = new JTextField();
            subject.setEnabled(false);
            rightPanel.add(subject, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 2;
            constraints.weightx = 1.0;
            constraints.weighty = 1.0;
            constraints.fill = GridBagConstraints.BOTH;
            JTextArea messageArea = new JTextArea();
            messageArea.setEnabled(false);
            rightPanel.add(new JScrollPane(messageArea), constraints);

            rightPanel.setPreferredSize(new Dimension(440, 500));



            //This is the start of the 'leftPanel' modification.
            leftPanel.setBorder(BorderFactory.createEmptyBorder(0,4,4,2));
            JList<String> subjectJList = new JList<>(listModel);
            subjectJList.setPreferredSize(new Dimension(160, 0));
            leftPanel.add(new JScrollPane(subjectJList), BorderLayout.WEST);

            subjectJList.addListSelectionListener(event -> {
                int i = subjectJList.getSelectedIndex();
                recipient.setText(addedContacts.get(sentEmails.get(i).getRecipient()) + " <" + sentEmails.get(i).getRecipient() + ">");
                subject.setText(sentEmails.get(i).getSubject());
                messageArea.setText(sentEmails.get(i).getText());
            });



            Color navyBlue = new Color(0x73EEB4);
            Color color = new Color(0xFDC3E0);
            //topPanel.setBackground(new Color(0x4F, 0x8F, 0xC0));
            //leftPanel.setBackground(new Color(0xFF, 0xE3, 0xB3));
            //rightPanel.setBackground(new Color(0xFF, 0xE3, 0xB3));
            //buttonNewMail.setBackground(new Color(0x53, 0xD2, 0xDC));
            //buttonAddContact.setBackground(new Color(0x53, 0xD2, 0xDC));
            topPanel.setBackground(navyBlue);
            leftPanel.setBackground(navyBlue);
            rightPanel.setBackground(navyBlue);
            buttonNewMail.setBackground(color);
            buttonAddContact.setBackground(color);

            //Putting everything into the frame
            frame.add(topPanel, BorderLayout.PAGE_START);
            frame.add(rightPanel, BorderLayout.EAST);
            frame.add(leftPanel, BorderLayout.CENTER);

            frame.pack();
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        });

    }
    public static String findContactEmail(String value){
        String credentials = "";
        for (Map.Entry<String, String> contact : addedContacts.entrySet()) {
            if (contact.getValue().equals(value)) {
                credentials = contact.getKey();
                break;
            }
        }
        return credentials;
    }
    public static ArrayList<Mail> getSentEmails() {
        return sentEmails;
    }
    public static DefaultListModel<String> getListModel() {
        return listModel;
    }
    public static boolean checkForExistingContact(String email) {
        return addedContacts.containsKey(email);
    }
    public static boolean checkForExistingContactName(String name) {
        return addedContacts.containsKey(findContactEmail(name));
    }
    public static void addContact(String name, String surname, String email) {
        addedContacts.put(email, name + " " + surname);
    }
}
