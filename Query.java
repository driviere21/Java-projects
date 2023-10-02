package DAO;

import Controller.LogInController;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.LocalDateStringConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/** This class contains all the methods that interact with the database.*/
public class Query {
    private ObservableList<Customer> allCustomers;
    private ObservableList<Contact> allContacts;
    private ObservableList<String> allCountries;
    private ObservableList<Appointment> allAppointments;
    private ObservableList<AppointmentReport> allAppointmentReport;
    private ObservableList<AppointmentSchedule> allAppointmentSchedule;
    private ObservableList<CustomerReport> allCustomerReport;
    private ObservableList<Appointment> monthAppointments;
    private ObservableList<Appointment> weekAppointments;
    private ObservableList<Appointment> fifteenMinutesDueAppointments;
    private ObservableList<Integer> nCountryId;
    private ObservableList<String> firstLevelDivision;
    private ObservableList<String> firstLevelDivisionName;
    private ObservableList<String> customerIdList;
    private ObservableList<Integer> appointmentIdList;
    private ObservableList<String> allContactsNames;
    private ObservableList<String> allContactIds;
    private ObservableList<String> divisionName;
    private ObservableList<String> countryName;
    private ObservableList<String> allContactIdsNames;
    private ObservableList<Integer> allCustomerId;
    private ObservableList<Customer> searchCustomerName;
    private ObservableList<Customer> searchCustomerId;




    public String logUser;
    public String newUser;

    public static int newCountryId;
    public static int newDivisionId;
    public static int newContactId;
    public static int newCustomerIdDelete = 0;
    public static int newRowsAffected = 0;
    public static int dueAppointmentId = 0;
    public static String dueAppointmentDateTime;









   /** This method validates the username and password.
    *
    * @param ePassword password entered by the user
    * @param eUserName username entered by the user
    * @return status verify the username and password is valid.
    *
    * */
    public static boolean userLogIn(String eUserName, String ePassword) throws SQLException {
       boolean status = false;
       String sql = "SELECT * FROM users WHERE User_Name = ? and Password = ?";
       PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       ps.setString(1,eUserName);
       ps.setString(2, ePassword);
       ResultSet rs = ps.executeQuery();
       status = rs.next();

     return status;
   }




   /** This method executes the SQL query and displays all the customers. */
   public void displayAllCustomers() throws SQLException {
       allCustomers = FXCollections.observableArrayList();
       allCustomerId = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");

            allCustomers.add(new Customer(customerId, customerName, customerAddress, postalCode,phone, createDate,createdBy,lastUpdate, lastUpdatedBy, divisionId));
            allCustomerId.add(customerId);

        }

    }

    /** This method gets all the customers from the customers observable list.
     *
     * @return returns the list of the customers.
     *
     */
    public ObservableList<Customer> getAllCustomers() {

       return allCustomers;
    }

    public ObservableList<Integer> getAllCustomerId() {

        return allCustomerId;
    }


    /** This method add customers.
     *
     * @param address  the address entered by the user
     * @param customerName name of the customer
     * @param phone The customer's phone number
     * @param postalCode the customer's zip code
     * @return returns the validation when the customer is added.
     * */
    public int addCustomer(String customerName, String address, String postalCode, String phone) throws SQLException {
       String newUser = LogInController.eUser;
       String sql = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
       PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       ps.setString(1, customerName);
       ps.setString(2, address);
       ps.setString(3, postalCode);
       ps.setString(4, phone);
       ps.setString(5, String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
       ps.setString(6, newUser);
       ps.setString(7, String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
       ps.setString(8,  newUser);
       ps.setInt(9, newDivisionId);

       int rowAffected = ps.executeUpdate();
       return rowAffected;

    }

    /** This method deletes the selected customer. */
    public static int deleteCustomer(int customerId) throws SQLException {

            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            int rowsAffected = ps.executeUpdate();
            newRowsAffected = rowsAffected;

            return rowsAffected;

    }

    /** This method updates the current customer table.
     *
     * @param postalCode customer zip code
     * @param phone customer phone number
     * @param customerName customer name
     * @param address customer address
     * @param createDate date the transaction was created
     * @param customerId customer ID generated by the database
     * @param createdBy the user who created the transaction
     * @param divisionId the division ID
     * @param lastUpdate the date the transaction was updated
     * @param lastUpdateBy the user who last updated the transaction
     *
     *
     */
    public static int updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, String createDate, String createdBy, String lastUpdate, String lastUpdateBy, int divisionId) throws SQLException {
       String newUser = LogInController.eUser;
       divisionId = newDivisionId;
       String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
       PreparedStatement ps = JDBC.connection.prepareStatement(sql);

       ps.setString(1, customerName);
       ps.setString(2, address);
       ps.setString(3, postalCode);
       ps.setString(4, phone);
       ps.setString(5, createDate);
       ps.setString(6, createdBy);
       ps.setString(7, lastUpdate);
       ps.setString(8, newUser);
       ps.setInt(9,divisionId);
       ps.setInt(10, customerId);
       int rowsAffected = ps.executeUpdate();
       return rowsAffected;

    }

    /** This method states the user that was logged in.
     *
     * @param userName username of the current user
     * @return returns the user who logged in
     *
     */
    public static String logUsers(String userName){
       String newUser = userName;
       return newUser;
    }


    /** This method displays all the countries. */
    public void displayAllCountries() throws SQLException {

        allCountries = FXCollections.observableArrayList();

        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            allCountries.add(country);
        }
    }


    /** This method returns all the countries from the observable list.
     *
     * @return allCountries list
     *
     */
    public ObservableList<String> getAllCountries() {

        return allCountries;
    }



    /** This method retrieves the country ID.
     *
     * @param countryName the country name submitted in the form.
     *
     */
    public void retrieveCountryId(String countryName) throws SQLException {
        nCountryId = FXCollections.observableArrayList();

        String sql = "SELECT * FROM countries WHERE Country = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, countryName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            nCountryId.add(countryId);
            newCountryId = countryId;

        }
    }

    /** This method returns the country ID.
     *
     * @return returns the country ID.
     *
     */
    public int getnCountryId() {

        return newCountryId;
    }


    /** This method retrieves the first level ID.
     *
     * @param nCountryId the country ID return from calling getCountryId.
     *
     */
    public void retrieveFirstLevelDId(int nCountryId) throws SQLException {
        firstLevelDivision = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, nCountryId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryId = rs.getInt("Country_ID");

            firstLevelDivision.add(division);
        }
    }


    /** This method returns the first level division.
     *
     * @return  returns the first level division.
     *
     */
    public ObservableList<String> getFirstLevelDivision() {
        return firstLevelDivision;
    }


    /** This method identify the first level ID.
     *
     * @param divisionName the division name.
     *
     */
    public void findFirstLevelId(String divisionName) throws SQLException {
        firstLevelDivisionName = FXCollections.observableArrayList();

        String sql = "SELECT * FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, divisionName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryId = rs.getInt("Country_ID");

            firstLevelDivisionName.add(division);
            newDivisionId = divisionId;

        }
    }


    /** This method validates that the customer being deleted does not have an appointment scheduled.
     *
     * @param customerId2 customer ID from the customer table
     *
     */
    public void validateDelete(int customerId2) throws SQLException {
       customerIdList = FXCollections.observableArrayList();
       appointmentIdList = FXCollections.observableArrayList();
       String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
       PreparedStatement ps = JDBC.connection.prepareStatement(sql);
       ps.setInt(1, customerId2);
       ResultSet rs = ps.executeQuery();
       while (rs.next()){
           int appointmentId = rs.getInt("Appointment_ID");
           String title = rs.getString("Title");
           String description = rs.getString("Description");
           String location = rs.getString("Location");
           String type = rs.getString("Type");
           String start = rs.getString("Start");
           String end = rs.getString("End");
           String createDate = rs.getString("Create_Date");
           String createdBy = rs.getString("Created_By");
           String lastUpdate = rs.getString("Last_Update");
           String lastUpdatedBy = rs.getString("Last_Updated_By");
           int customerId = rs.getInt("Customer_ID");
           int userId = rs.getInt("User_ID");

           customerIdList.add(String.valueOf(customerId));
           appointmentIdList.add(appointmentId);
           newCustomerIdDelete = customerId;
       }

    }

    public ObservableList<String> getCustomerIdList() {
        return customerIdList;
    }
    public ObservableList<Integer> getAppointmentIdList() {
        return appointmentIdList;
    }



    /** This method displays all the scheduled appointments. */
    public void displayAllAppointments() throws SQLException {
        allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            String start2 = zoneTimeConversion(start);
            String end2 = zoneTimeConversion(end);
            String createDate2 = zoneTimeConversion(createDate);
            String lastUpdate2 = zoneTimeConversion(lastUpdate);




            allAppointments.add(new Appointment(appointmentId, title, description, location, type, start2, end2, createDate2, createdBy, lastUpdate2, lastUpdatedBy, customerId, userId, contactId));


        }

    }


    /** Method used to convert UTC back to local time zone.
     * */
    public String zoneTimeConversion (String time) {
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate startLD = LocalDate.parse(time.substring(0,10));
        LocalTime startLT = LocalTime.parse(time.substring(11));
        LocalDateTime startLDT = LocalDateTime.of(startLD, startLT);
        ZoneId zoneId = ZoneId.of("UTC");
        ZonedDateTime startZDT = ZonedDateTime.of(startLDT, zoneId);

        ZoneId myZID = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.ofInstant(startZDT.toInstant(), myZID);

        String startZDT2 = dtf.format(myZDT);
        String date2 = startZDT2.toString().substring(0, 10);
        String time3 = startZDT2.toString().substring(11);
        String nTime2 = date2 + " " + time3;



        return nTime2;
    }


    /** Method used to convert local time to UTC time. */
    public String UTCTimeConversion (String time) {
        LocalDate timeLD = LocalDate.parse(time.substring(0,10));
        LocalTime timeLT = LocalTime.parse(time.substring(11));
        LocalDateTime timeLDT = LocalDateTime.of(timeLD, timeLT);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime timeZDT = ZonedDateTime.of(timeLDT, zoneId);


        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId utcZoneTime = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(timeZDT.toInstant(), utcZoneTime);

        String submitTime1 = dtf.format(utcZDT);

        String date2 = submitTime1.toString().substring(0, 10);
        String time3 = submitTime1.toString().substring(11);
        String submitTime = date2 + " " + time3;

        return submitTime;
    }

    /** This method return all appointments from the observable list.
     *
     * @return return all schedule appointments
     *
     */
    public ObservableList<Appointment> getAllAppointments() {

        return allAppointments;
    }


    /** This method display all contacts on the appointment form combo box. */
    public void displayAllContacts() throws SQLException {

        allContactsNames = FXCollections.observableArrayList();

        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");


            allContactsNames.add( contactName);
        }
    }


    /** This method return the contact names from the observable list.
     *
     * @return  allContactNames
     *
     */
    public ObservableList<String> getAllContactsNames() {

        return allContactsNames;
    }


    public void findContactId(String nContactName) throws SQLException {
        allContactIds = FXCollections.observableArrayList();

        String sql = "SELECT * FROM contacts WHERE Contact_Name  = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, nContactName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            allContactIds.add(String.valueOf(contactId));
            newContactId = contactId;



        }
    }



    /** This method add appointments.
     *
     * @param customerId the customer ID
     * @param description the description of the appointment
     * @param end the end time of the appointment
     * @param location the location of the appointment
     * @param start the start time of the appointment
     * @param title the title of the appointment
     * @param type the type of the appointment
     * @param userId the user ID of the user who added the appointment
     * @return rowsAffected confirms the appointment was added
     *
     *
     */
    public int addAppointment(String title, String description, String location, String type, String start, String end, int customerId, int userId) throws SQLException {
        String newUser = LogInController.eUser;
        int nContactId = newContactId;

        String sql = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setString(5, UTCTimeConversion(start));
        ps.setString(6, UTCTimeConversion(end));
        ps.setString(7, UTCTimeConversion(String.valueOf(LocalDateTime.now())));
        ps.setString(8, newUser);
        ps.setString(9,  UTCTimeConversion(String.valueOf(LocalDateTime.now())));
        ps.setString(10, newUser);
        ps.setInt(11, customerId);
        ps.setInt(12, userId);
        ps.setInt(13, nContactId);

        int rowAffected = ps.executeUpdate();
        return rowAffected;

    }



    /** This method updates the appointment.
     *
     * @param userId the ID of the user updating the appointment
     * @param type the type of appointment
     * @param title the title of the appointment
     * @param start the start time of the appointment
     * @param location the location of the apppointment
     * @param end the end time of the appointment
     * @param description the description of the appointment
     * @param customerId the customer ID generated by the database
     * @param createdBy the user that initially entered the appointment
     * @param createDate the date the appointment was created
     * @param appointmentId the appointment ID
     * @param contact the contact for the appointment
     * @param lastUpdated the date and time the appointment was last updated
     * @param updatedBy the user who updated the appointment
     * @return rowsAffected confirms the appointment was updated
     *
     *
     */
    public static int updateAppointments(int appointmentId, String title, String description, String location, String type, String start, String end, String createDate, String createdBy , String lastUpdated, String updatedBy, int customerId, int userId, String contact ) throws SQLException {
        String newUser = LogInController.eUser;
        int contactId = newContactId;

        String sql = "UPDATE Appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        Query qs = new Query();
        String start2 = qs.UTCTimeConversion(start);
        String end2 = qs.UTCTimeConversion(end);
        String createDate2 = qs.UTCTimeConversion(createDate);
        String lastUpdate2 = qs.UTCTimeConversion(lastUpdated);

        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setString(5, start2);
        ps.setString(6, end2);
        ps.setString(7, createDate2);
        ps.setString(8, createdBy);
        ps.setString(9, lastUpdate2);
        ps.setString(10, newUser);
        ps.setInt(11, customerId);
        ps.setInt(12,userId);
        ps.setInt(13, contactId);
        ps.setInt(14, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }



    /** This method deletes the selected appointment.
     *
     * @param appointmentId the appointment ID to be deleted
     * @return rowsAffected confirms the appointment was deleted
     *
     *
     */
    public static int deleteAppointment(int appointmentId) throws SQLException {


        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();
       // newRowsAffected = rowsAffected;


        return rowsAffected;

    }

    public static int deleteAppointment2(int customerId) throws SQLException {


        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();
        // newRowsAffected = rowsAffected;


        return rowsAffected;

    }


    /** This method find and format the local date time zone.
     *
     * @param datetime the machine date and time
     *
     * @return  return the local date time
     *
     */
    public String ZonedDateTime (String datetime) {

        int year = Integer.parseInt(datetime.substring(0, 4));
        int month     = Integer.parseInt(datetime.substring(5, 7));
        int day = Integer.parseInt(datetime.substring(8, 10));
        int hour =Integer.parseInt(datetime.substring(11, 13));
        int minute = Integer.parseInt(datetime.substring(14, 16));


        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDate myLD = LocalDate.of(year, month, day);
        LocalTime myLT = LocalTime.of(hour, minute);
        LocalDateTime myLDT = LocalDateTime.of(myLD, myLT);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        String myZDT2 = dtf.format(myZDT);
        // ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);

        return myZDT2;
    }



    /** This method displays the current month's appointment. */
    public void displayMonthAppointments() throws SQLException {
        monthAppointments = FXCollections.observableArrayList();

        LocalDateTime currentTime = LocalDateTime.now();
        String startOfMonth = String.valueOf(currentTime.withDayOfMonth(1).minusHours(23));
        String endOfMonth = String.valueOf(currentTime.withDayOfMonth(1).plusMonths(1).minusDays(1));


        String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? AND ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, startOfMonth);
        ps.setString(2, endOfMonth);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            String start2 = zoneTimeConversion(start);
            String end2 = zoneTimeConversion(end);
            String createDate2 = zoneTimeConversion(createDate);
            String lastUpdate2 = zoneTimeConversion(lastUpdate);

            monthAppointments.add(new Appointment(appointmentId, title, description, location, type, start2, end2, createDate2, createdBy, lastUpdate2, lastUpdatedBy, customerId, userId, contactId));

        }
    }


    /** This method return the current month's appointments.
     *
     * @return return the current month's appointments
     *
     *
     */
    public ObservableList<Appointment> getMonthAppointments() {

        return monthAppointments;
    }




    /** This method displays the current week's appointment. */
    public void displayWeekAppointments() throws SQLException {
        weekAppointments = FXCollections.observableArrayList();
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate currentDay = LocalDate.now();

        LocalDate sunday = currentDay;
        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            sunday = sunday.minusDays(1);
        }

        LocalDate saturday = currentDay;
        while (saturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
            saturday= saturday.plusDays(1);
        }


        String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? AND ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(sunday));
        ps.setString(2, String.valueOf(saturday));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            String start2 = zoneTimeConversion(start);
            String end2 = zoneTimeConversion(end);
            String createDate2 = zoneTimeConversion(createDate);
            String lastUpdate2 = zoneTimeConversion(lastUpdate);

            weekAppointments.add(new Appointment(appointmentId, title, description, location, type, start2, end2, createDate2, createdBy, lastUpdate2, lastUpdatedBy, customerId, userId, contactId));

        }

    }

    /** This method return the current week's appointments.
     *
     * @return return the current week's appointments
     *
     *
     */
    public ObservableList<Appointment> getWeekAppointments() {

        return weekAppointments;
    }



    /** This method displays appointments due within 15 minutes of a user log on. */
    public void displayDueAppointments() throws SQLException {
        fifteenMinutesDueAppointments = FXCollections.observableArrayList();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime timeWindow = currentTime.plusMinutes(15);

        String dueTime = String.valueOf(timeWindow);
        String timeNow = String.valueOf(currentTime);

        String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? AND ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, timeNow);
        ps.setString(2, dueTime);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            String end = rs.getString("End");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");

            String start2 = zoneTimeConversion(start);
            String end2 = zoneTimeConversion(end);
            String createDate2 = zoneTimeConversion(createDate);
            String lastUpdate2 = zoneTimeConversion(lastUpdate);

            fifteenMinutesDueAppointments.add(new Appointment(appointmentId, title, description, location, type, start2, end2, createDate2, createdBy, lastUpdate2, lastUpdatedBy, customerId, userId, contactId));

        }

    }



    /** This method return the due appointments.
     *
     * @return return the appointments that a due within 15 minutes of log-on
     *
     */
    public ObservableList<Appointment> getFifteenMinutesDueAppointments() {


        return fifteenMinutesDueAppointments;
    }




    /** This method validates that appointment conflicts does not exist.
     *
     * @param appEndDateTime the end date time of the added  appointment
     * @param appStartDateTime  the start date time of the added appointment
     *
     * @return return the validate status
     * */
    public static boolean appointmentConflict (String appStartDateTime, String appEndDateTime) throws SQLException {

       boolean validate = false;

        //DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


       String sql = "SELECT * FROM appointments WHERE Start BETWEEN ? AND ? OR  End BETWEEN ? AND ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, appStartDateTime);
        ps.setString(2, appEndDateTime);
        ps.setString(3, appStartDateTime);
        ps.setString(4, appEndDateTime);
        ResultSet rs = ps.executeQuery();
        validate = rs.next();

        return validate;
    }



    /** This method displays the appointment summary report. */
    public void displayAppointmentReport() throws SQLException {
        allAppointmentReport = FXCollections.observableArrayList();
        String sql = "SELECT   MonthName ( Start )  AS Month, Type, COUNT(*) as Amount FROM appointments GROUP BY   MonthName ( Start ), Type";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

           String start = rs.getString("Month");
           String type = rs.getString("Type");
           String amount = rs.getString("Amount");

           allAppointmentReport.add(new AppointmentReport( start, type,amount ));

        }

    }


    /** This method returns the appointment summary report form the observable list.
     *
     * @return the appointment summary report
     *
     *
     */
    public ObservableList<AppointmentReport> getAllAppointmentReport() {

        return allAppointmentReport;
    }



    /** This method displays the appointment schedule by contact report. */
    public void displayAppointmentSchedule(int contactId) throws SQLException {
        allAppointmentSchedule = FXCollections.observableArrayList();
       // String sql = "SELECT * FROM appointments ";
        String sql = "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE appointments.Contact_ID = ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            String contact = rs.getString("Contact_Name");
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String type = rs.getString("Type");
            String startDateTime = rs.getString("Start");
            String endDateTime = rs.getString("End");
            int customerId = rs.getInt("Customer_ID");
           // int contactId = rs.getInt("Contact_ID");


            String start2 = zoneTimeConversion(startDateTime);
            String end2 = zoneTimeConversion(endDateTime);


            allAppointmentSchedule.add(new AppointmentSchedule(contact, appointmentId, title, type, description, start2, end2, customerId ));

        }

    }


    /** This method returns the appointment schedule by contact report form the observable list.
     *
     * @return the appointment schedule by contact report
     *
     *
     */
    public ObservableList<AppointmentSchedule> getAllAppointmentSchedule() {

        return allAppointmentSchedule;
    }



    /** This method displays the customer report. */
    public void displayCustomerReport() throws SQLException {
        allCustomerReport= FXCollections.observableArrayList();
        // String sql = "SELECT * FROM appointments ";
        String sql = "SELECT * FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            String customerName = rs.getString("Customer_Name");
            String division = rs.getString("Division");

            allCustomerReport.add(new CustomerReport(customerName, division));

        }

    }


    /** The method returns the customer report  from the observable list.
     *
     * @return  the customer report data
     *
     *
     */
    public ObservableList<CustomerReport> getAllCustomerReport() {

        return allCustomerReport;
    }


    public void findDivisionName(int divId) throws SQLException {
        divisionName = FXCollections.observableArrayList();
        countryName = FXCollections.observableArrayList();

        String sql = "SELECT * FROM first_level_divisions JOIN countries ON first_level_divisions.COUNTRY_ID = countries.Country_ID WHERE Division_ID = ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String divisionName2 = rs.getString("Division");
            String countryName2 = rs.getString("Country");

            divisionName.add(divisionName2);
            countryName.add(countryName2);
        }

    }


    public ObservableList<String> getAllDivisionNames() {

        return divisionName;
    }

    public ObservableList<String> getAllCountryNames() {

        return countryName;
    }


    public String findContactIdNames(int contactId2) throws SQLException {
        allContactsNames = FXCollections.observableArrayList();


        String sql = "SELECT * FROM contacts WHERE Contact_ID = ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId2);
        ResultSet rs = ps.executeQuery();

        String contactName = null;
        while (rs.next()) {
            contactName = rs.getString("Contact_Name");


        }
        return contactName;

    }


    public ObservableList<String> getAllContactIdsNames() {

        return allContactIdsNames;
    }

    public void findCustomerName(String newCustomerName) throws SQLException {
        searchCustomerName = FXCollections.observableArrayList();


        String sql = "SELECT * FROM customers WHERE Customer_Name LIKE ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, "%" + newCustomerName + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionId = rs.getInt("Division_ID");


            searchCustomerName.add(new Customer(customerId, customerName, customerAddress, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId));

        }

    }

    public ObservableList<Customer> getSearchCustomerName() {

        return searchCustomerName;
    }




}

