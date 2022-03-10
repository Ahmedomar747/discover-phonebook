package Service;

import Repository.ContactDao;
import Repository.PhoneDao;

public class ComponentService {

    private ContactDao contactDao;
    private PhoneDao phoneDao;
    private ContactService contactService;


    public ComponentService() {

        this.contactDao = new ContactDao();
        this.phoneDao = new PhoneDao();
        this.contactService = new ContactService(contactDao, phoneDao);
    }

    public ContactService getContactService() {
        return this.contactService;
    }
}
