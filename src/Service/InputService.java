package Service;

import java.sql.SQLException;
import java.util.List;

import Model.Contact;

public class InputService {
    
    private final ContactService contactService; 

    
    public InputService(ContactService contactService) {
        this.contactService = contactService;
    }

    public void parseInput(String operation, String input) {
        switch (operation) {
            case "getAllContacts" : 
                try {
                    List<Contact> contacts = contactService.getContacts();
                    System.out.println(contacts);
                } catch (ClassNotFoundException | SQLException e2) {
                    e2.printStackTrace();
                }
                break;
            case "getFavoriteContacts" : 
                try {
                    List<Contact> contacts = contactService.getFavoriteContacts();
                    System.out.println(contacts);
                } catch (ClassNotFoundException | SQLException e2) {
                    e2.printStackTrace();
                }
                break;
            case "getContactByName" : 
                try {
                    List<Contact> contacts = contactService.getContactsByName(input);
                    System.out.println(contacts);
                } catch (ClassNotFoundException | SQLException e2) {
                    e2.printStackTrace();
                }
                break;
            case "getContactByPhone" :
                try {
                    Contact contact = contactService.getContactByPhone(input);
                    System.out.println(contact.toString());
                } catch (ClassNotFoundException | SQLException e2) {
                    e2.printStackTrace();
                }
                break;
            case "insertContact" :
                break;
            case "updateContact" :
                break;
            case "deleteContact" :
                try {
                    contactService.deleteContact(input);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case "markAsFavorite" :
                try {
                    contactService.setContactAsFavorite(input);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid function.");  
        }
    }
}
