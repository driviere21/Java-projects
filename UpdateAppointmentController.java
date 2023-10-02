package Controller;


import DAO.Query;
import Model.Appointment;
import Model.Customer;
import com.example.schedulepluspro2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;


/** This class update current appointments. */
public class UpdateAppointmentController implements Initializable {
    Stage stage;
    Parent scene;




    @FXML
    private Label updateAppointmentAppointmentIdLbl;

    @FXML
    private Button updateAppointmentCancelBtn;

    @FXML
    private ComboBox<String> updateAppointmentContactComboBx;

    @FXML
    private TextField updateAppointmentCreatedByTxt;

    @FXML
    private TextField updateAppointmentCustomerIdTxt;

    @FXML
    private TextField updateAppointmentDescriptionTxt;

    @FXML
    private TextField updateAppointmentEndTxt;

    @FXML
    private TextField updateAppointmentLastUpdatedTxt;

    @FXML
    private TextField updateAppointmentLocationTxt;

    @FXML
    private TextField updateAppointmentStartTxt;

    @FXML
    private TextField updateAppointmentTitleTxt;

    @FXML
    private TextField updateAppointmentTypeTxt;

    @FXML
    private Button updateAppointmentUpdateAppointmentBtn;

    @FXML
    private TextField updateAppointmentUpdatedByTxt;

    @FXML
    private TextField updateAppointmentUserIdTxt;

    @FXML
    private TextField updatedAppointmentCreatedDateTxt;

    @FXML
    private ComboBox<Integer> updateAppointmentCustomerIdComboBx;



    @FXML
    void displayCustomerId(ActionEvent event) {

    }


    /** This method cancels the update appointment process and navigate
     * back to the main screen. */
    @FXML
    void onActionCancelUpdateAppointment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }

    /** This method obtains the updated information and update the appointment table.
     *
     * This method also validates that the appointment time selected is not outside
     * business hours.
     *
     */
    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException, SQLException {

        int appointmentId = Integer.parseInt(updateAppointmentAppointmentIdLbl.getText());
        String title = updateAppointmentTitleTxt.getText();
        String location = updateAppointmentLocationTxt.getText();
        String start = updateAppointmentStartTxt.getText();
        String createBy = updateAppointmentCreatedByTxt.getText();
        String updatedBy = updateAppointmentUpdatedByTxt.getText();
        int customerId = updateAppointmentCustomerIdComboBx.getValue();
        String description = updateAppointmentDescriptionTxt.getText();
        String type = updateAppointmentTypeTxt.getText();
        String end = updateAppointmentEndTxt.getText();
        String createDate = updatedAppointmentCreatedDateTxt.getText();
        String lastUpdated = updateAppointmentLastUpdatedTxt.getText();
        int userId = Integer.parseInt(updateAppointmentUserIdTxt.getText());
        String contact = updateAppointmentContactComboBx.getSelectionModel().getSelectedItem().toString();

        Query qs = new Query();
        qs.findContactId(contact);
        String start2 = qs.UTCTimeConversion(start);
        String end2 = qs.UTCTimeConversion(end);


        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate startLD = LocalDate.of(2023, 05, 16);
        LocalTime startLT = LocalTime.of(07, 59);
        LocalDateTime startLDT = LocalDateTime.of(startLD, startLT);
        ZoneId zoneId = ZoneId.of("America/New_York");
        ZonedDateTime startZDT = ZonedDateTime.of(startLDT, zoneId);

        LocalDate endLD = LocalDate.of(2023, 05, 16);
        LocalTime endLT = LocalTime.of(22, 01);
        LocalDateTime endLDT = LocalDateTime.of(endLD, endLT);
        ZonedDateTime endZDT = ZonedDateTime.of(endLDT, zoneId);

        LocalTime startBusinessHours = LocalTime.of(startZDT.getHour(), startZDT.getMinute());
        LocalTime endBusinessHours = LocalTime.of(endZDT.getHour(), endZDT.getMinute());






            LocalTime appointmentStartTime = LocalTime.parse(updateAppointmentStartTxt.getText().substring(11));
            LocalTime appointmentEndTime = LocalTime.parse(updateAppointmentEndTxt.getText().substring(11));

        if (Query.appointmentConflict(start2, end2)) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment conflict Please select a new appointment time ");
            alert.showAndWait();
        } else {


            if (appointmentStartTime.isAfter(startBusinessHours) && appointmentEndTime.isBefore(endBusinessHours)) {
                // Query qs = new Query();
                if ((appointmentStartTime.equals(startBusinessHours) || appointmentStartTime.isAfter(startBusinessHours)) && (appointmentEndTime.equals(endBusinessHours) || appointmentEndTime.isBefore(endBusinessHours))) {
                    Query.updateAppointments(appointmentId, title, description, location, type, start, end, createDate, createBy, lastUpdated, updatedBy, customerId, userId, contact);
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
                    stage.setTitle("Add New Customer");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an appointment time between 08:00 and 22:00");
                    alert.showAndWait();

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an appointment time between 08:00 and 22:00");
                alert.showAndWait();
            }
        }

        }




        /** This method transfers the data from the appointment selected to
         * the update appointment screen to be pre-populated. */
        public void sendAppointment(Appointment appointment) throws SQLException {

        Query qs = new Query();
        int contactId = appointment.getContactId();



        updateAppointmentAppointmentIdLbl.setText(String.valueOf(appointment.getAppointmentId()));
        updateAppointmentTitleTxt.setText(appointment.getTitle());
        updateAppointmentLocationTxt.setText(appointment.getLocation());
        updateAppointmentStartTxt.setText(String.valueOf(qs.ZonedDateTime(appointment.getStart())));
        updateAppointmentCreatedByTxt.setText(appointment.getCreatedBy());
        updateAppointmentUpdatedByTxt.setText(appointment.getLastUpdatedBy());
        updateAppointmentCustomerIdComboBx.setValue(appointment.getCustomerId());
        updateAppointmentDescriptionTxt.setText(appointment.getDescription());
        updateAppointmentTypeTxt.setText(appointment.getType());
        updateAppointmentEndTxt.setText(String.valueOf(qs.ZonedDateTime(appointment.getEnd())));
        updatedAppointmentCreatedDateTxt.setText(String.valueOf(qs.ZonedDateTime(appointment.getCreateDate())));
        updateAppointmentLastUpdatedTxt.setText(String.valueOf(qs.ZonedDateTime(appointment.getLastUpdate())));
        updateAppointmentUserIdTxt.setText(String.valueOf(appointment.getUserId()));
        updateAppointmentContactComboBx.setValue(qs.findContactIdNames(contactId));




    }


    /** This method set the contacts in the combo box. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Query qs = new Query();
        try {
            qs.displayAllContacts();
            qs.displayAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        updateAppointmentContactComboBx.setItems(qs.getAllContactsNames());
        updateAppointmentCustomerIdComboBx.setItems(qs.getAllCustomerId());


    }
}
