package Library_Management_System;

public class Member {
    private int member_id;
    private String member_name;
    private String member_phone;
    private String memberShip_type;
    public Member() {
        super();
    }
    public Member(int member_id, String member_name, String member_phone, String member_ship_type) {
        super();
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_phone = member_phone;
        this.memberShip_type = member_ship_type;
    }
    public Member(String member_name, String member_phone, String member_ship_type) {
        this.member_name = member_name;
        this.member_phone = member_phone;
        this.memberShip_type = member_ship_type;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public String getMemberShip_type() {
        return memberShip_type;
    }

    public void setMemberShip_type(String memberShip_type) {
        this.memberShip_type = memberShip_type;
    }

    @Override
    public String toString() {
        return "Member{" +
                "member_id=" + member_id +
                ", member_name='" + member_name + '\'' +
                ", member_phone='" + member_phone + '\'' +
                ", memberShip_type='" + memberShip_type + '\'' +
                '}';
    }
}
