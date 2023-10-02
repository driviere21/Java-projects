package Model;

/** This class supplies the appointment schedule by contact report object. */
public class AppointmentSchedule {
    String contact;
   // int contactId;
    int appointmentId;
    String title;
    String type;
    String description;
    String startDateTime;
    String endDateTime;
    int customerId;


    /** Constructor for an instance of the appointment schedule object.
     *
     * @param type the type of appointment
     * @param title the title of the appointment
     * @param description the appointment description
     * @param appointmentId the appointment ID
     * @param customerId the customer ID
     * @param contact the contact assigned to the appointment
     * @param endDateTime the appointment end date and time
     * @param startDateTime the appointment start date and time
     *
     */
    public AppointmentSchedule(String contact, int appointmentId, String title, String type, String description, String startDateTime, String endDateTime, int customerId) {
        //this.contactId = contactId;
        this.contact = contact;
        this.appointmentId = appointmentId;
        this.title = title;
        this.type = type;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
    }


    public AppointmentSchedule() {
    }


    /** The contact getter.
     *
     * @return the contact
     *
     */
    public String getContact() {
        return contact;
    }

    /** The contact setter. */
    public void setContact(String contact) {
        this.contact = contact;
    }


    /** The appointment ID getter.
     *
     * @return appointment ID
     *
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** The appointment ID setter. */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** The title getter.
     *
     * @return  the title
     *
     */
    public String getTitle() {
        return title;
    }

    /** The title setter. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** The type getter.
     *
     * @return the type
     *
     */
    public String getType() {
        return type;
    }

    /** The type setter. */
    public void setType(String type) {
        this.type = type;
    }

    /** The description getter.
     *
     * @return the description
     *
     */
    public String getDescription() {
        return description;
    }

    /** The description setter. */
    public void setDescription(String description) {
        this.description = description;
    }

    /** The start date and time getter.
     *
     * @return start date and time
     *
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /** The start date and time setter. */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /** The end date and time getter.
     *
     * @return the end date and time
     *
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /** The end date and time setter. */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    /** The customer ID getter.
     *
     * @return customer ID
     *
     */
    public int getCustomerId() {
        return customerId;
    }

    /** The customer ID setter. */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
