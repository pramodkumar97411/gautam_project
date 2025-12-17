package services;

import models.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private static MemberService instance;
    private List<Member> members = new ArrayList<>();
    private int nextId = 1;

    private MemberService() {}

    public static MemberService getInstance() {
        if (instance == null) {
            instance = new MemberService();
        }
        return instance;
    }

    public void addMember(String name, String email, String phone, String membershipType) {
        members.add(new Member(nextId++, name, email, phone, membershipType));
    }

    public void addMember(Member member) {
        members.add(new Member(nextId++, member.getName(), member.getEmail(), member.getPhone(), member.getMembershipType()));
    }

    public void updateMember(int id, String name, String email, String phone, String membershipType) {
        for (Member m : members) {
            if (m.getId() == id) {
                m.setName(name);
                m.setEmail(email);
                m.setPhone(phone);
                m.setMembershipType(membershipType);
                break;
            }
        }
    }

    public void deleteMember(int id) {
        members.removeIf(m -> m.getId() == id);
    }

    public Member getMember(int id) {
        return members.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    public List<Member> getAllMembers() { return new ArrayList<>(members); }

    public List<Member> searchMembers(String query) {
        return members.stream()
            .filter(m -> m.getName().toLowerCase().contains(query.toLowerCase()) ||
                        m.getEmail().toLowerCase().contains(query.toLowerCase()))
            .toList();
    }

    public int getTotalMembers() { return members.size(); }
    public long getActiveMembers() { return members.stream().filter(Member::isActive).count(); }
}
