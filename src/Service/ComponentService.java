package Service;

import Repository.ContactDao;
import Repository.FavoriteDao;
import Repository.PhoneDao;

public class ComponentService {

    private ContactDao contactDao;
    private FavoriteDao favoriteDao;
    private PhoneDao phoneDao;
    private ContactService contactService;


    public ComponentService() {

        this.contactDao = new ContactDao();
        this.favoriteDao = new FavoriteDao();
        this.phoneDao = new PhoneDao();
        this.contactService = new ContactService(contactDao, favoriteDao, phoneDao);
    }

    public ContactService getContactService() {
        return this.contactService;
    }
}
