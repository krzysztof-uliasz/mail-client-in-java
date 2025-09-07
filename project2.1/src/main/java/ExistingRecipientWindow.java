import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExistingRecipientWindow extends JFrame {
    private static final DefaultListModel<String> contactsListModel = new DefaultListModel<>();
    private static String designatedRecipient = "";

    public ExistingRecipientWindow() throws HeadlessException {

        JFrame frame = new JFrame("Wybierz adresata");

        JPanel centrePanel = new JPanel(new BorderLayout());

        JList<String> recipientList = new JList<>(contactsListModel);
        recipientList.setPreferredSize(new Dimension(240, Math.max(contactsListModel.capacity()*13, 80)));

        centrePanel.add(recipientList, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton buttonChoose = new JButton("WYBIERZ");
        JButton buttonCancel = new JButton("ANULUJ");
        bottomPanel.add(buttonChoose);
        bottomPanel.add(buttonCancel);

        buttonCancel.addActionListener(event -> {
            frame.setVisible(false);
        });

        buttonChoose.addActionListener(event -> {
            NewMailWindow.getRecipient().setText(Main.findContactEmail(designatedRecipient));
            if (!designatedRecipient.isEmpty()) { NewMailWindow.getRecipient().setEnabled(false); }
            frame.setVisible(false);
        });

        recipientList.addListSelectionListener(event -> {
            designatedRecipient = recipientList.getSelectedValue();
        });

        Color niceMint = new Color(0x73EEB4);
        Color nicePink = new Color(0xFDC3E0);
        centrePanel.setBackground(niceMint);
        bottomPanel.setBackground(niceMint);
        buttonCancel.setBackground(nicePink);
        buttonChoose.setBackground(nicePink);

        frame.add(centrePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
    public static void addContactToListModel(String user) {
        contactsListModel.addElement(user);
    }
}
