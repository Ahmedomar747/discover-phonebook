package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoriteDao {

    public int setContactAsFavorite(Connection conn, String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO FAVORITE (name) VALUES (?)");
        ps.setString(1, name);
        return ps.executeUpdate();
    }

    public ResultSet getFavoriteContacts(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT CONTACT.name, GROUP_CONCAT(PHONE_NUMBER) phonelist,  GROUP_CONCAT(PHONE_TYPE) typelist " 
        + "FROM CONTACT INNER JOIN PHONE ON CONTACT.name = PHONE.Name "
        + "INNER JOIN FAVORITE ON CONTACT.name = FAVORITE.name " 
        + "GROUP BY CONTACT.name");
        return ps.executeQuery();
    }
    
}
