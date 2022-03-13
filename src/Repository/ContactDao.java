package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDao {

    public Integer insertContact(Connection conn, String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO CONTACT (name) values (?)");
        ps.setString(1, name);
        return ps.executeUpdate();
    }

    public Integer updateContact(Connection conn, String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE CONTACT SET name = ? WHERE name = ?");
        ps.setString(1, name);
        return ps.executeUpdate();
    }

    public int deleteContact(Connection conn, String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM CONTACT WHERE name = ?");
        ps.setString(1, name);
        return ps.executeUpdate();
    }

    public ResultSet getContacts(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT CONTACT.name, GROUP_CONCAT(PHONE_NUMBER) phonelist,  GROUP_CONCAT(PHONE_TYPE)  typelist FROM CONTACT INNER JOIN PHONE ON CONTACT.name = PHONE.Name GROUP BY CONTACT.name");
        return ps.executeQuery();
    }

    public ResultSet getContactByPhone(Connection conn, String phoneNumber) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT CONTACT.name, GROUP_CONCAT(PHONE_NUMBER) phonelist,  GROUP_CONCAT(PHONE_TYPE)  typelist FROM CONTACT INNER JOIN PHONE ON CONTACT.name = PHONE.Name GROUP BY CONTACT.name HAVING Phone.PHONE_NUMBER = ?");
        ps.setString(1, phoneNumber);
        return ps.executeQuery();
    }

    public ResultSet getContactsByName(Connection conn, String nameInput) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT CONTACT.name, GROUP_CONCAT(PHONE_NUMBER) phonelist,  GROUP_CONCAT(PHONE_TYPE)  typelist FROM CONTACT INNER JOIN PHONE ON CONTACT.name = PHONE.Name GROUP BY CONTACT.name HAVING CONTACT.name like ?");
        ps.setString(1, nameInput + "%");
        return ps.executeQuery();
    }

    public ResultSet getFavoriteContacts(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT CONTACT.name, GROUP_CONCAT(PHONE_NUMBER) phonelist,  GROUP_CONCAT(PHONE_TYPE) typelist " 
        + "FROM CONTACT INNER JOIN PHONE ON CONTACT.name = PHONE.Name "
        + "GROUP BY CONTACT.name"
        +" HAVING is_favorite = 1");
        return ps.executeQuery();
    }

    public int setContactAsFavorite(Connection conn, String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE CONTACT SET is_favorite = 1 WHERE name = ?");
        ps.setString(1, name);
        return ps.executeUpdate();
    }
    
}
