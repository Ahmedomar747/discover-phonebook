package Service;

import Repository.ContactDao;
import Repository.PhoneDao;

public class ComponentService {

    private ContactDao contactDao;
    private PhoneDao phoneDao;
    private ContactService contactService;
    private InputService inputService;


    public ComponentService() {

        this.contactDao = new ContactDao();
        this.phoneDao = new PhoneDao();
        this.contactService = new ContactService(contactDao, phoneDao);
        this.inputService = new InputService(contactService);
    }

    public ContactService getContactService() {
        return this.contactService;
    }

    public InputService getInputService() {
        return this.inputService;
    }

}
