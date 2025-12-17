package gui;

import utils.UIStyles;
import services.MemberService;
import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    public StatisticsPanel() {
        setBackground(UIStyles.BACKGROUND);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("Statistics & Analytics");
        title.setFont(UIStyles.TITLE_FONT);
        title.setForeground(UIStyles.TEXT_PRIMARY);
        add(title, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        contentPanel.setOpaque(false);

        contentPanel.add(createMembershipChart());
        contentPanel.add(createRevenueCard());

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createMembershipChart() {
        JPanel card = UIStyles.createCard("Membership Distribution");
        card.setPreferredSize(new Dimension(0, 350));

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int centerX = width / 2;
                int centerY = height / 2;
                int radius = Math.min(width, height) / 3;

                int basic = 45;
                int premium = 30;
                int vip = 25;
                int total = basic + premium + vip;

                int startAngle = 0;
                g2d.setColor(UIStyles.PRIMARY);
                int arc1 = (basic * 360) / total;
                g2d.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arc1);
                startAngle += arc1;

                g2d.setColor(UIStyles.SECONDARY);
                int arc2 = (premium * 360) / total;
                g2d.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arc2);
                startAngle += arc2;

                g2d.setColor(new Color(245, 158, 11));
                int arc3 = (vip * 360) / total;
                g2d.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arc3);

                g2d.setFont(UIStyles.BODY_FONT);
                g2d.setColor(UIStyles.TEXT_PRIMARY);
                int legendX = width - 150;
                int legendY = 50;

                g2d.setColor(UIStyles.PRIMARY);
                g2d.fillRect(legendX, legendY, 20, 20);
                g2d.setColor(UIStyles.TEXT_PRIMARY);
                g2d.drawString("Basic: " + basic + "%", legendX + 30, legendY + 15);

                g2d.setColor(UIStyles.SECONDARY);
                g2d.fillRect(legendX, legendY + 30, 20, 20);
                g2d.setColor(UIStyles.TEXT_PRIMARY);
                g2d.drawString("Premium: " + premium + "%", legendX + 30, legendY + 45);

                g2d.setColor(new Color(245, 158, 11));
                g2d.fillRect(legendX, legendY + 60, 20, 20);
                g2d.setColor(UIStyles.TEXT_PRIMARY);
                g2d.drawString("VIP: " + vip + "%", legendX + 30, legendY + 75);
            }
        };
        chartPanel.setBackground(UIStyles.CARD_BG);
        card.add(chartPanel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createRevenueCard() {
        JPanel card = UIStyles.createCard("Monthly Revenue Trend");
        card.setPreferredSize(new Dimension(0, 350));

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int padding = 50;
                int[] revenue = {3200, 3800, 4100, 3900, 4500, 5200};
                String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun"};

                int maxRevenue = 6000;
                int barWidth = (width - 2 * padding) / revenue.length;

                g2d.setColor(UIStyles.BORDER);
                g2d.drawLine(padding, height - padding, width - padding, height - padding);
                g2d.drawLine(padding, padding, padding, height - padding);

                for (int i = 0; i < revenue.length; i++) {
                    int barHeight = (revenue[i] * (height - 2 * padding)) / maxRevenue;
                    int x = padding + i * barWidth + barWidth / 4;
                    int y = height - padding - barHeight;

                    GradientPaint gradient = new GradientPaint(
                        x, y, UIStyles.ACCENT,
                        x, height - padding, UIStyles.PRIMARY
                    );
                    g2d.setPaint(gradient);
                    g2d.fillRect(x, y, barWidth / 2, barHeight);

                    g2d.setColor(UIStyles.TEXT_SECONDARY);
                    g2d.setFont(UIStyles.SMALL_FONT);
                    g2d.drawString(months[i], x, height - padding + 20);
                    g2d.drawString("$" + revenue[i], x, y - 5);
                }
            }
        };
        chartPanel.setBackground(UIStyles.CARD_BG);
        card.add(chartPanel, BorderLayout.CENTER);

        return card;
    }
}
