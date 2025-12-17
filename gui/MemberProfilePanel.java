package gui;

import utils.UIStyles;
import models.Member;
import services.MemberService;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class MemberProfilePanel extends JPanel {
    private JTextField searchField;
    private JPanel profileCard;

    public MemberProfilePanel() {
        setBackground(UIStyles.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Member Profile");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_PRIMARY);
        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        searchPanel.setOpaque(false);

        JLabel searchLabel = new JLabel("Member ID:");
        searchLabel.setFont(UIStyles.BODY_FONT);
        searchLabel.setForeground(UIStyles.TEXT_SECONDARY);

        searchField = UIStyles.createStyledTextField();
        searchField.setPreferredSize(new Dimension(200, 40));

        JButton searchBtn = UIStyles.createStyledButton("Search", UIStyles.PRIMARY);
        searchBtn.setPreferredSize(new Dimension(120, 40));
        searchBtn.addActionListener(e -> searchMember());

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(searchPanel, BorderLayout.NORTH);

        profileCard = new JPanel();
        profileCard.setBackground(UIStyles.CARD_BG);
        profileCard.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(UIStyles.BORDER, 1, true),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        profileCard.setLayout(new BoxLayout(profileCard, BoxLayout.Y_AXIS));

        JLabel emptyLabel = new JLabel("Search for a member to view their profile");
        emptyLabel.setFont(UIStyles.BODY_FONT);
        emptyLabel.setForeground(UIStyles.TEXT_SECONDARY);
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileCard.add(Box.createVerticalStrut(100));
        profileCard.add(emptyLabel);

        topPanel.add(profileCard, BorderLayout.CENTER);
        add(topPanel, BorderLayout.CENTER);
    }

    private void searchMember() {
        try {
            int id = Integer.parseInt(searchField.getText().trim());
            Member member = MemberService.getInstance().getMember(id);

            profileCard.removeAll();

            if (member != null) {
                displayMemberProfile(member);
            } else {
                JLabel notFoundLabel = new JLabel("Member not found");
                notFoundLabel.setFont(UIStyles.BODY_FONT);
                notFoundLabel.setForeground(UIStyles.DANGER);
                notFoundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                profileCard.add(Box.createVerticalStrut(100));
                profileCard.add(notFoundLabel);
            }

            profileCard.revalidate();
            profileCard.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid member ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayMemberProfile(Member member) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        JLabel nameLabel = new JLabel(member.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        nameLabel.setForeground(UIStyles.TEXT_PRIMARY);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel idLabel = new JLabel("Member ID: " + member.getId());
        idLabel.setFont(UIStyles.BODY_FONT);
        idLabel.setForeground(UIStyles.TEXT_SECONDARY);
        idLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        profileCard.add(nameLabel);
        profileCard.add(Box.createVerticalStrut(5));
        profileCard.add(idLabel);
        profileCard.add(Box.createVerticalStrut(30));

        profileCard.add(createInfoRow("Email:", member.getEmail()));
        profileCard.add(Box.createVerticalStrut(15));
        profileCard.add(createInfoRow("Phone:", member.getPhone()));
        profileCard.add(Box.createVerticalStrut(15));
        profileCard.add(createInfoRow("Membership Type:", member.getMembershipType()));
        profileCard.add(Box.createVerticalStrut(15));
        profileCard.add(createInfoRow("Join Date:", member.getJoinDate().format(formatter)));
        profileCard.add(Box.createVerticalStrut(15));
        profileCard.add(createInfoRow("Expiry Date:", member.getExpiryDate().format(formatter)));
        profileCard.add(Box.createVerticalStrut(15));

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusPanel.setOpaque(false);
        statusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel statusLabel = new JLabel("Status: ");
        statusLabel.setFont(UIStyles.BODY_FONT);
        statusLabel.setForeground(UIStyles.TEXT_SECONDARY);

        JLabel statusValue = new JLabel(member.isActive() ? "Active" : "Inactive");
        statusValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusValue.setForeground(member.isActive() ? UIStyles.SECONDARY : UIStyles.DANGER);

        statusPanel.add(statusLabel);
        statusPanel.add(statusValue);
        profileCard.add(statusPanel);

        profileCard.add(Box.createVerticalStrut(30));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton editBtn = UIStyles.createStyledButton("Edit", UIStyles.PRIMARY);
        JButton renewBtn = UIStyles.createStyledButton("Renew", UIStyles.SECONDARY);
        JButton deleteBtn = UIStyles.createStyledButton("Delete", UIStyles.DANGER);

        buttonPanel.add(editBtn);
        buttonPanel.add(renewBtn);
        buttonPanel.add(deleteBtn);

        profileCard.add(buttonPanel);
    }

    private JPanel createInfoRow(String label, String value) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelComp = new JLabel(label);
        labelComp.setFont(UIStyles.BODY_FONT);
        labelComp.setForeground(UIStyles.TEXT_SECONDARY);
        labelComp.setPreferredSize(new Dimension(150, 20));

        JLabel valueComp = new JLabel(value);
        valueComp.setFont(UIStyles.BODY_FONT);
        valueComp.setForeground(UIStyles.TEXT_PRIMARY);

        row.add(labelComp);
        row.add(valueComp);

        return row;
    }
}
