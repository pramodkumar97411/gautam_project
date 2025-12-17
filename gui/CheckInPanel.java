package gui;

import utils.UIStyles;
import models.Member;
import services.MemberService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckInPanel extends JPanel {
    private JTextArea logArea;
    private MemberService service;

    public CheckInPanel() {
        this.service = MemberService.getInstance();
        setLayout(new BorderLayout(20, 20));
        setBackground(UIStyles.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Member Check-In");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_PRIMARY);
        add(title, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(UIStyles.CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(UIStyles.BORDER, 1, true),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        inputPanel.setBackground(UIStyles.CARD_BG);

        JLabel idLabel = new JLabel("Member ID:");
        idLabel.setFont(UIStyles.BODY_FONT);
        idLabel.setForeground(UIStyles.TEXT_SECONDARY);

        JTextField idField = UIStyles.createStyledTextField();
        idField.setPreferredSize(new Dimension(200, 40));

        JButton checkInBtn = UIStyles.createStyledButton("Check In", UIStyles.SECONDARY);
        checkInBtn.setPreferredSize(new Dimension(120, 40));

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(checkInBtn);
        card.add(inputPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        logArea.setBackground(UIStyles.BACKGROUND);
        logArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        card.add(scrollPane, BorderLayout.CENTER);

        checkInBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Member m = service.getMember(id);
                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if (m != null) {
                    logArea.append("[" + time + "] CHECK-IN: " + m.getName() + " - " + m.getMembershipType() + " (ID: " + id + ")\n");
                    logArea.setCaretPosition(logArea.getDocument().getLength());
                } else {
                    logArea.append("[" + time + "] ERROR: Member ID " + id + " not found\n");
                    logArea.setCaretPosition(logArea.getDocument().getLength());
                }
                idField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(card, BorderLayout.CENTER);
    }
}
