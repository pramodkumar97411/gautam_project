import gui.MainFrame;
import services.MemberService;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initializeSampleData();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

    private static void initializeSampleData() {
        MemberService service = MemberService.getInstance();
        service.addMember("John Doe", "john.doe@email.com", "+1-555-0101", "Premium - $49/mo");
        service.addMember("Jane Smith", "jane.smith@email.com", "+1-555-0102", "Basic - $29/mo");
        service.addMember("Mike Johnson", "mike.j@email.com", "+1-555-0103", "VIP - $79/mo");
        service.addMember("Sarah Williams", "sarah.w@email.com", "+1-555-0104", "Premium - $49/mo");
        service.addMember("Robert Brown", "robert.b@email.com", "+1-555-0105", "Basic - $29/mo");
        service.addMember("Emily Davis", "emily.d@email.com", "+1-555-0106", "VIP - $79/mo");
        service.addMember("David Wilson", "david.w@email.com", "+1-555-0107", "Premium - $49/mo");
        service.addMember("Lisa Anderson", "lisa.a@email.com", "+1-555-0108", "Basic - $29/mo");
    }
}
