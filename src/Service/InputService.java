package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Model.Phone;

public class InputService {

    private final ContactService contactService;

    public InputService(ContactService contactService) {
        this.contactService = contactService;
    }

    public String parseInput(String[] input) {
        if (input.length < 1) {
            return "Invalid number of inputs";
        }
        String operation = input[0];
        switch (operation) {
            case "getAllContacts":
                try {
                    List<Contact> contacts = contactService.getContacts();
                    return contacts.toString();
                } catch (ClassNotFoundException | SQLException e2) {
                    return "Error"; 
                }
            case "getFavoriteContacts":
                try {
                    List<Contact> contacts = contactService.getFavoriteContacts();
                    return contacts.toString();
                } catch (ClassNotFoundException | SQLException e2) {
                    return "Error";
                }
            case "getContactsByName":
                try {
                    if (input.length < 2) {
                        return "Invalid number of inputs";
                    }
                    List<Contact> contacts = contactService.getContactsByName(input[1]);
                    return contacts.toString();
                } catch (ClassNotFoundException | SQLException e2) {
                    return "Error";
                }
            case "getContactByPhone":
                try {
                    if (input.length < 2) {
                        return "Invalid number of inputs";
                    }
                    Contact contact = contactService.getContactByPhone(input[1]);
                    return contact != null ? contact.toString() : "Contact not found";
                } catch (ClassNotFoundException | SQLException e2) {
                    return "Error";
                }
            case "insertContact":
                try {
                    if (input.length < 4) {
                        return "Invalid number of inputs";
                    }
                    Contact contact = new Contact();
                    contact.setName(input[1]);
                    List<Phone> plist = new ArrayList<>();
                    for (int i = 2; i < input.length - 1; i += 2) {
                        Phone p = new Phone();
                        p.setPhoneNumber(input[i]);
                        p.setPhoneType(input[i + 1]);
                        plist.add(p);
                    }
                    contact.setPhoneList(plist);
                    contact = contactService.insertContact(contact);
                    return contact != null ? "Contact " + contact.getName() + " registered" : "Contact not found";
                } catch (ClassNotFoundException | SQLException e1) {
                    return "Contact or Phone number already exists";
                }
            case "updateContact":
                try {
                    if (input.length < 4) {
                        return "Invalid number of inputs";
                    }
                    Contact contact = new Contact();
                    contact.setName(input[1]);
                    List<Phone> plist = new ArrayList<>();
                    for (int i = 2; i < input.length - 1; i += 2) {
                        Phone p = new Phone();
                        p.setPhoneNumber(input[i]);
                        p.setPhoneType(input[i + 1]);
                        plist.add(p);
                    }
                    contact.setPhoneList(plist);
                    contact = contactService.updateContact(contact);
                    return contact != null ? "Contact " + contact.getName() + " updated" : "Contact not found";
                } catch (ClassNotFoundException | SQLException e1) {
                    return "Contact or Phone number already exists";
                }
            case "deleteContact":
                try {
                    if (input.length < 2) {
                        return "Invalid number of inputs";
                    }
                    Boolean result = contactService.deleteContact(input[1]);
                    return result ? "Contact " + input[1] + " deleted" : " Contact not found";
                } catch (ClassNotFoundException | SQLException e1) {
                    return "Error";
                }
            case "markAsFavorite":
                try {
                    if (input.length < 2) {
                        return "Invalid number of inputs";
                    }
                    Boolean result = contactService.setContactAsFavorite(input[1]);
                    return result ? "Contact " + input[1] + " marked as favorite" : " Contact not found";
                } catch (ClassNotFoundException | SQLException e) {
                    return "Error";
                }
            default:
                return "Invalid function.";
        }
    }
}
