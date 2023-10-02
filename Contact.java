package Model;

/** This class supplies the contact object. */
public class Contact {
    private int contactId;
    private String contactName;
    private String email;

/** Constructor for an instance of the appointment schedule object.
 *
 * @param contactId the contact ID
 * @param contactName the contact assigned to the appointment
 * @param email the contact email
 *
 *
 */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    public Contact() {
    }

    /** The contact ID getter.
     *
     * @return the contact ID
     *
     */
    public int getContactId() {
        return contactId;
    }

    /** The contact ID setter. */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** The contact name getter.
     *
     * @return contact name
     *
     */
    public String getContactName() {
        return contactName;
    }

    /** The contact name setter. */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** The email getter.
     *
     * @return  email
     *
     */
    public String getEmail() {
        return email;
    }

    /** The email setter. */
    public void setEmail(String email) {
        this.email = email;
    }
}
