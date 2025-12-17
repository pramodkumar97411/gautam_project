package gui;

import utils.UIStyles;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentPanel extends JPanel {
    private JTable paymentTable;
    private DefaultTableModel model;

    public PaymentPanel() {
        setBackground(UIStyles.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Payment History");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_PRIMARY);
        add(title, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setOpaque(false);

        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        summaryPanel.setOpaque(false);

        summaryPanel.add(createSummaryCard("Total Revenue", "$12,450", UIStyles.SECONDARY));
        summaryPanel.add(createSummaryCard("This Month", "$4,250", UIStyles.PRIMARY));
        summaryPanel.add(createSummaryCard("Pending", "$850", UIStyles.WARNING));

        contentPanel.add(summaryPanel, BorderLayout.NORTH);

        String[] columns = {"Date", "Member ID", "Member Name", "Amount", "Type", "Status"};
        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };

        paymentTable = new JTable(model);
        paymentTable.setFont(UIStyles.BODY_FONT);
        paymentTable.setRowHeight(45);
        paymentTable.setShowGrid(false);
        paymentTable.setBackground(UIStyles.CARD_BG);
        paymentTable.setSelectionBackground(new Color(16, 185, 129, 30));

        JTableHeader header = paymentTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(UIStyles.BACKGROUND);
        header.setForeground(UIStyles.TEXT_SECONDARY);
        header.setPreferredSize(new Dimension(0, 45));

        paymentTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (column == 5) {
                    String status = (String) value;
                    if (status.equals("Paid")) {
                        setForeground(UIStyles.SECONDARY);
                    } else if (status.equals("Pending")) {
                        setForeground(UIStyles.WARNING);
                    } else {
                        setForeground(UIStyles.DANGER);
                    }
                    setFont(new Font("Segoe UI", Font.BOLD, 13));
                } else {
                    setForeground(UIStyles.TEXT_PRIMARY);
                }

                if (!isSelected) {
                    setBackground(UIStyles.CARD_BG);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(paymentTable);
        scrollPane.setBorder(new javax.swing.border.LineBorder(UIStyles.BORDER, 1, true));
        scrollPane.getViewport().setBackground(UIStyles.CARD_BG);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        loadSampleData();
    }

    private JPanel createSummaryCard(String label, String value, Color accentColor) {
        JPanel card = new JPanel();
        card.setBackground(UIStyles.CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 4, 0, accentColor),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(UIStyles.TEXT_PRIMARY);

        JLabel nameLabel = new JLabel(label);
        nameLabel.setFont(UIStyles.BODY_FONT);
        nameLabel.setForeground(UIStyles.TEXT_SECONDARY);

        card.add(valueLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(nameLabel);

        return card;
    }

    private void loadSampleData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String today = LocalDate.now().format(formatter);

        model.addRow(new Object[]{today, "1", "John Doe", "$49.00", "Premium", "Paid"});
        model.addRow(new Object[]{today, "3", "Alice Smith", "$79.00", "VIP", "Paid"});
        model.addRow(new Object[]{"Dec 15, 2024", "2", "Jane Wilson", "$29.00", "Basic", "Pending"});
        model.addRow(new Object[]{"Dec 14, 2024", "5", "Bob Johnson", "$49.00", "Premium", "Paid"});
        model.addRow(new Object[]{"Dec 12, 2024", "4", "Mike Brown", "$29.00", "Basic", "Paid"});
    }
}
