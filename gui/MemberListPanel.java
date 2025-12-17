package gui;

import utils.UIStyles;
import models.Member;
import services.MemberService;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class MemberListPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    
    public MemberListPanel() {
        setBackground(UIStyles.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Header with search
        JPanel header = new JPanel(new BorderLayout(20, 0));
        header.setOpaque(false);
        
        JLabel title = new JLabel("Members");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_PRIMARY);
        header.add(title, BorderLayout.WEST);
        
        // Search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);
        searchField = UIStyles.createStyledTextField();
        searchField.setPreferredSize(new Dimension(250, 40));

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                searchMembers();
            }
        });

        JButton refreshBtn = UIStyles.createStyledButton("Refresh", UIStyles.SECONDARY);
        refreshBtn.addActionListener(e -> refreshTable());

        JButton deleteBtn = UIStyles.createStyledButton("Delete", UIStyles.DANGER);
        deleteBtn.addActionListener(e -> deleteMember());

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(refreshBtn);
        searchPanel.add(deleteBtn);
        header.add(searchPanel, BorderLayout.EAST);
        
        add(header, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"ID", "Name", "Email", "Phone", "Membership", "Status"};
        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        
        table = new JTable(model);
        table.setFont(UIStyles.BODY_FONT);
        table.setRowHeight(45);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setBackground(UIStyles.CARD_BG);
        table.setSelectionBackground(new Color(79, 70, 229, 30));
        
        // Header styling
        JTableHeader header2 = table.getTableHeader();
        header2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header2.setBackground(UIStyles.BACKGROUND);
        header2.setForeground(UIStyles.TEXT_SECONDARY);
        header2.setPreferredSize(new Dimension(0, 45));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new javax.swing.border.LineBorder(UIStyles.BORDER, 1, true));
        scrollPane.getViewport().setBackground(UIStyles.CARD_BG);
        
        add(scrollPane, BorderLayout.CENTER);
        refreshTable();
    }
    
    private void refreshTable() {
        model.setRowCount(0);
        List<Member> members = MemberService.getInstance().getAllMembers();
        for (Member m : members) {
            model.addRow(new Object[]{m.getId(), m.getName(), m.getEmail(), m.getPhone(), m.getMembershipType(), "Active"});
        }
    }

    private void searchMembers() {
        String query = searchField.getText().trim();
        model.setRowCount(0);

        if (query.isEmpty()) {
            refreshTable();
            return;
        }

        List<Member> results = MemberService.getInstance().searchMembers(query);
        for (Member m : results) {
            model.addRow(new Object[]{m.getId(), m.getName(), m.getEmail(), m.getPhone(), m.getMembershipType(), "Active"});
        }
    }

    private void deleteMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a member to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) table.getValueAt(selectedRow, 0);
        String name = (String) table.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete " + name + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            MemberService.getInstance().deleteMember(id);
            refreshTable();
            JOptionPane.showMessageDialog(this, "Member deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
