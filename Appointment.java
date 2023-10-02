package Model;

import java.util.TimeZone;

/** This class supplies the appointment object. */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;


    /** Constructor for the appointment object.
     *
     * @param appointmentId the appointment ID
     * @param createDate the date the appointment was created
     * @param createdBy the user who created the appointment
     * @param customerId the customer assigned to the appointment
     * @param description the description of the appointment
     * @param end the end time and date of the appointment
     * @param location the location of the appointment
     * @param start  the start date time of the appointment
     * @param title the title of the appointment
     * @param type the type of appointment
     * @param userId the user who will be entering or updating the appointment
     * @param lastUpdate  the last date and time the appointment was updated
     * @param contactId the contact assigned to the appointment
     * @param lastUpdatedBy the user who last updated the appointment
     *
     *
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, String start, String end, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    public Appointment() {
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

    /** The appointment ID getter.
     *
     * @return the appointment ID
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
     * @return the appointment title
     *
     */
    public String getTitle() {
        return title;
    }

    /** The title setter. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** The description getter.
     *
     * @return the appointment description
     *
     */
    public String getDescription() {
        return description;
    }

    /** The description setter. */
    public void setDescription(String description) {
        this.description = description;
    }

    /** The location getter.
     *
     * @return the appointment location
     *
     */
    public String getLocation() {
        return location;
    }

    /** The location setter. */
    public void setLocation(String location) {
        this.location = location;
    }

    /** The time getter.
     *
     * @return appointment type
     *
     */
    public String getType() {
        return type;
    }

    /** The type setter. */
    public void setType(String type) {
        this.type = type;
    }

    /** The start date and time getter.
     *
     * @return the start date and time
     *
     */
    public String getStart() {
        return start;
    }

    /** The start setter. */
    public void setStart(String start) {
        this.start = start;
    }

    /** The end date and time getter.
     *
     * @return the end date and time
     *
     */
    public String getEnd() {
        return end;
    }

    /** The end setter. */
    public void setEnd(String end) {
        this.end = end;
    }

    /** The created date getter.
     *
     * @return the created date and time
     *
     */
    public String getCreateDate() {
        return createDate;
    }

    /** The create date setter. */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /** The created by getter.
     *
     * @return the user who created
     *
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /** The created by setter. */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /** The last update getter.
     *
     * @return the last update date and time
     *
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /** The last update setter. */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /** The last updated by getter.
     *
     * @return the last updated by user
     *
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /** The last updated by setter. */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** The customer ID getter.
     *
     * @return the customer ID
     *
     */
    public int getCustomerId() {
        return customerId;
    }

    /** The customer ID setter. */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** The user ID getter.
     *
     * @return the user ID
     *
     */
    public int getUserId() {
        return userId;
    }

    /** The user ID setter. */
    public void setUserId(int userId) {
        this.userId = userId;
    }


    public void createLocalTime() {
        TimeZone.getDefault();
    }
}
