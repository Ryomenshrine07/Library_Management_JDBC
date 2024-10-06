package Library_Management_System;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    Connection connect;
    MemberDAO(Connection connect){
        this.connect = connect;
    }
    public void addMember(Member member) throws SQLException {
        String query = "INSERT INTO member (name,contact,memberShip_type) VALUES(?,?,?)";
        try(PreparedStatement prpt = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            prpt.setString(1,member.getMember_name());
            prpt.setString(2,member.getMember_phone());
            prpt.setString(3,member.getMemberShip_type());
            prpt.executeUpdate();
            try(ResultSet generatedKeys = prpt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    member.setMember_id(generatedKeys.getInt(1));
                }
            }
            System.out.println("Member Added Successfully");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Member> getAllMember() throws SQLException {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM member";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            ResultSet rs = prpt.executeQuery();
            while(rs.next()){
                int member_id = rs.getInt("id");
                String member_name = rs.getString("name");
                String member_phone = rs.getString("contact");
                String member_ship_type = rs.getString("memberShip_type");
                Member member = new Member(member_id,member_name,member_phone,member_ship_type);
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
    public void removeMember(int member_id) throws SQLException {
        String query = "DELETE FROM member WHERE id=?";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            prpt.setInt(1,member_id);
            prpt.executeUpdate();
            System.out.println("Member has been deleted");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void updateMember(Member member) throws SQLException {
        String query = "UPDATE member SET name =?, contact =?, memberShip_type =? WHERE id=?";
        try(PreparedStatement prpt = connect.prepareStatement(query)){
            prpt.setString(1,member.getMember_name());
            prpt.setString(2,member.getMember_phone());
            prpt.setString(3,member.getMemberShip_type());
            prpt.setInt(4,member.getMember_id());
            prpt.executeUpdate();
            System.out.println("Member has been updated");
        }

        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
