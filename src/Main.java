import java.util.List;

import Model.Contact;
import Model.Phone;
import Service.ComponentService;
import Service.ContactService;
import Service.InputService;

public class Main {
    public static void main(String[] args) throws Exception {

        ComponentService componentService = new ComponentService();
        // ContactService contactService = componentService.getContactService();
        InputService inputService = componentService.getInputService();
        String result = inputService.parseInput(args);
        System.out.println(result);
        try {
            // Contact contact = contactService.insertContact(new Contact("test13", List.of(new Phone("22444", "Mobile"))));
            // System.out.println(contact.toString());
            // System.out.println(contactService.getContacts());
            // Contact contact = contactService.updateContact(new Contact("test13", List.of(new Phone("22445", "Mobile"))));
            // System.out.println(contact.toString());
            // contactService.setContactAsFavorite("test13");
            // Contact contact = contactService.getContactByPhone("22444");
            // System.out.println(contact.toString());
        } catch (Exception e) {
            System.out.println(e);
        }

        
    }
}
