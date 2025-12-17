package gui;

import utils.UIStyles;
import models.Member;
import services.MemberService;
import javax.swing.*;
import java.awt.*;

public class AddMemberPanel extends JPanel {
    private JTextField nameField, emailField, phoneField;
    private JComboBox<String> membershipType;
    
    public AddMemberPanel() {
        setBackground(UIStyles.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel title = new JLabel("Add New Member");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_PRIMARY);
        add(title, BorderLayout.NORTH);
        
        // Form card
        JPanel formCard = new JPanel();
        formCard.setBackground(UIStyles.CARD_BG);
        formCard.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(UIStyles.BORDER, 1, true),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        formCard.setLayout(new GridBagLayout());
        formCard.setMaximumSize(new Dimension(500, 400));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        formCard.add(createLabel("Full Name"), gbc);
        gbc.gridx = 1;
        nameField = UIStyles.createStyledTextField();
        nameField.setPreferredSize(new Dimension(300, 40));
        formCard.add(nameField, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 1;
        formCard.add(createLabel("Email"), gbc);
        gbc.gridx = 1;
        emailField = UIStyles.createStyledTextField();
        formCard.add(emailField, gbc);
        
        // Phone
        gbc.gridx = 0; gbc.gridy = 2;
        formCard.add(createLabel("Phone"), gbc);
        gbc.gridx = 1;
        phoneField = UIStyles.createStyledTextField();
        formCard.add(phoneField, gbc);
        
        // Membership Type
        gbc.gridx = 0; gbc.gridy = 3;
        formCard.add(createLabel("Membership"), gbc);
        gbc.gridx = 1;
        membershipType = new JComboBox<>(new String[]{"Basic - $29/mo", "Premium - $49/mo", "VIP - $79/mo"});
        membershipType.setFont(UIStyles.BODY_FONT);
        formCard.add(membershipType, gbc);
        
        // Submit button
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.insets = new Insets(20, 10, 10, 10);
        JButton submitBtn = UIStyles.createStyledButton("Add Member", UIStyles.PRIMARY);
        submitBtn.setPreferredSize(new Dimension(300, 45));
        submitBtn.addActionListener(e -> addMember());
        formCard.add(submitBtn, gbc);
        
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapper.setOpaque(false);
        wrapper.add(formCard);
        add(wrapper, BorderLayout.CENTER);
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIStyles.BODY_FONT);
        label.setForeground(UIStyles.TEXT_SECONDARY);
        return label;
    }
    
    private void addMember() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MemberService.getInstance().addMember(name, email, phone, membershipType.getSelectedItem().toString());

        JOptionPane.showMessageDialog(this, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }
}
