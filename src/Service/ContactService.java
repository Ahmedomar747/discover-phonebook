package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Model.Phone;
import Repository.ContactDao;
import Repository.FavoriteDao;
import Repository.PhoneDao;

public class ContactService {

    private final ContactDao contactDao;
    private final FavoriteDao favoriteDao;
    private final PhoneDao phoneDao;


    public ContactService(ContactDao contactDao, FavoriteDao favoriteDao, PhoneDao phoneDao) {
        this.contactDao = contactDao;
        this.favoriteDao = favoriteDao;
        this.phoneDao = phoneDao;
    }

    public List<Contact> getContacts() throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseService.getConnection()) {
            List<Contact> contactList = new ArrayList<>();
            ResultSet rs = contactDao.getContacts(conn);
            while(rs.next()) {
                Contact c = mapContact(rs);
                contactList.add(c);
            }
            return contactList;
        }
    }

    public List<Contact> getFavoriteContacts() throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseService.getConnection()) {
            List<Contact> contactList = new ArrayList<>();
            ResultSet rs = favoriteDao.getFavoriteContacts(conn);
            while(rs.next()) {
                Contact c = mapContact(rs);
                contactList.add(c);
            }
            return contactList;
        } 
    }

    public List<Contact> getContactsByName(String nameInput) throws ClassNotFoundException, SQLException {
        try (Connection conn = DatabaseService.getConnection()) {
            List<Contact> contactList = new ArrayList<>();
            ResultSet rs = contactDao.getContactsByName(conn, nameInput);
            while(rs.next()) {
                Contact c = mapContact(rs);
                contactList.add(c);
            }
            return contactList;
        }
    }

    public Contact getContactByPhone(String phoneNumber) throws ClassNotFoundException, SQLException {
        try (Connection conn = DatabaseService.getConnection()) {
            ResultSet rs = contactDao.getContactByPhone(conn, phoneNumber);
            if(rs.next()) {
                Contact c = mapContact(rs);
                return c;
            }
            return null;
        }
    }

    public Boolean setContactAsFavorite(String name) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseService.getConnection()) {
            int result = favoriteDao.setContactAsFavorite(conn, name);
            conn.commit();
            return (result > 0);
        }
    }

    public Boolean deleteContact(String name) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseService.getConnection()) {
            int result = contactDao.deleteContact(conn, name);
            result += phoneDao.deletePhoneNumber(conn, name);
            conn.commit();
            return (result > 0);
        }
    }

    public Contact updateContact(Contact contact) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseService.getConnection()) {
            contactDao.updateContact(conn, contact.getName());
            phoneDao.updatePhoneNumber(conn, contact.getName(), contact.getPhoneList());
            conn.commit();
            return contact;
        }
    }

    public Contact insertContact(Contact contact) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseService.getConnection()) {
            contactDao.insertContact(conn, contact.getName());
            phoneDao.insertPhoneNumber(conn, contact.getName(), contact.getPhoneList());
            conn.commit();
            return contact;
        }
    }

    private Contact mapContact(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setName(rs.getString("name"));
        String[] phoneList = rs.getString("phonelist").split(",");
        String[] typeList = rs.getString("typelist").split(",");
        List<Phone> pList = new ArrayList<>();
        for (int i = 0; i < phoneList.length ; i++) {
            Phone p = new Phone();
            p.setPhoneNumber(phoneList[i]);
            p.setPhoneType(typeList[i]);
            pList.add(p);
        }
        contact.setPhoneList(pList);
        return contact;
    }

}
