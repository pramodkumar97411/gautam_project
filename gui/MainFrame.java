package gui;

import utils.UIStyles;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    public MainFrame() {
        setTitle("💪 Gym User Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIStyles.BACKGROUND);
        
        // Sidebar
        mainPanel.add(createSidebar(), BorderLayout.WEST);
        
        // Content area
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(UIStyles.BACKGROUND);
        
        contentPanel.add(new DashboardPanel(), "dashboard");
        contentPanel.add(new MemberListPanel(), "members");
        contentPanel.add(new AddMemberPanel(), "add");
        contentPanel.add(new CheckInPanel(), "checkin");
        contentPanel.add(new StatisticsPanel(), "statistics");
        contentPanel.add(new PaymentPanel(), "payments");
        contentPanel.add(new MemberProfilePanel(), "profile");
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBackground(UIStyles.PRIMARY);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        
        // Logo/Title
        JLabel logo = new JLabel("🏋️ GYM PRO");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(40));
        
        // Navigation buttons
        String[][] navItems = {
            {"📊 Dashboard", "dashboard"},
            {"👥 Members", "members"},
            {"➕ Add Member", "add"},
            {"✅ Check-In", "checkin"},
            {"📈 Statistics", "statistics"},
            {"💳 Payments", "payments"},
            {"👤 Profile", "profile"}
        };
        
        for (String[] item : navItems) {
            JButton btn = createNavButton(item[0], item[1]);
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(10));
        }
        
        sidebar.add(Box.createVerticalGlue());
        
        // Footer
        JLabel footer = new JLabel("v1.0.0");
        footer.setFont(UIStyles.SMALL_FONT);
        footer.setForeground(new Color(255, 255, 255, 150));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(footer);
        
        return sidebar;
    }
    
    private JButton createNavButton(String text, String card) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setBackground(UIStyles.PRIMARY_DARK);
        btn.setForeground(Color.WHITE);
        btn.setFont(UIStyles.BODY_FONT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(UIStyles.PRIMARY);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(UIStyles.PRIMARY_DARK);
            }
        });
        
        btn.addActionListener(e -> cardLayout.show(contentPanel, card));
        return btn;
    }
}
