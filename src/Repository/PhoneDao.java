package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Model.Phone;

public class PhoneDao {

    public int[] insertPhoneNumber(Connection conn, String name, List<Phone> phoneList) throws SQLException  {
        try( PreparedStatement ps = conn.prepareStatement("INSERT INTO PHONE (name, phone_number, phone_type) values (?,?,?)")) {
            for(Phone phone: phoneList) {
                ps.setString(1, name);
                ps.setString(2, phone.getPhoneNumber());
                ps.setString(3, phone.getPhoneType());
                ps.addBatch();
            }
            return ps.executeBatch();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }

    public int[] updatePhoneNumber(Connection conn, String name, List<Phone> phoneList) throws SQLException {
        try(  PreparedStatement ps = conn.prepareStatement("UPDATE PHONE SET phone_number = ?, phone_type = ? WHERE name = ?")) {
            for(Phone phone: phoneList) {
                ps.setString(1, name);
                ps.setString(2, phone.getPhoneNumber());
                ps.setString(3, phone.getPhoneType());
                ps.addBatch();
            }
            return ps.executeBatch();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }

    public int deletePhoneNumber(Connection conn, String name) throws SQLException {
        try( PreparedStatement ps = conn.prepareStatement("DELETE FROM PHONE WHERE name = ?") ) {
            return ps.executeUpdate();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }
    
}
