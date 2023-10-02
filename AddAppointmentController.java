package Controller;

import DAO.Query;
import com.example.schedulepluspro2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
/** This class add a new appointment to the appointments table. */
public class AddAppointmentController implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    private Button AddAppointmentCancelBtn;

    @FXML
    private Button addAppointmentAddAppointmentBtn;

    @FXML
    private Label addAppointmentAppointmentIdLbl;

    @FXML
    private ComboBox<String> addAppointmentContactComboBx;

    @FXML
    private TextField addAppointmentCustomerIdTxt;

    @FXML
    private ComboBox<Integer> addAppointmentCustomerIdComboBx;

    @FXML
    private TextField addAppointmentEndTxt;

    @FXML
    private TextField addAppointmentLocationTxt;

    @FXML
    private TextField addAppointmentStartTxt;

    @FXML
    private TextField addAppointmentTitleTxt;

    @FXML
    private TextField addAppointmentTypeTxt;

    @FXML
    private TextField addAppointmentUserIdTxt;

    @FXML
    private TextField addAppointmentDescriptionTxt;

    @FXML
    /** This method retrieves the information needed to create the appointment and add it to the appointments table.
     *
     * This method also validate that the appointment time selected is not outside of the working hours
     *
     */
    void addAppointmentAddAppointmentBtn(ActionEvent event) throws IOException, SQLException {

        String title = addAppointmentTitleTxt.getText();
        String description = addAppointmentDescriptionTxt.getText();
        String location = addAppointmentLocationTxt.getText();
        String type = addAppointmentTypeTxt.getText();
        String start = addAppointmentStartTxt.getText();
        String end = addAppointmentEndTxt.getText();
        int customerId = addAppointmentCustomerIdComboBx.getValue();
        int userId = Integer.parseInt(addAppointmentUserIdTxt.getText());

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



        LocalTime appointmentStartTime = LocalTime.parse(addAppointmentStartTxt.getText().substring(11));
        LocalTime appointmentEndTime = LocalTime.parse(addAppointmentEndTxt.getText().substring(11));

        Query query = new Query();
        String start2 = query.UTCTimeConversion(start);
        String end2 = query.UTCTimeConversion(end);



        if (Query.appointmentConflict(start2, end2)) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Appointment conflict Please select a new appointment time ");
            alert.showAndWait();

        } else {


            if (appointmentStartTime.isAfter(startBusinessHours) && appointmentEndTime.isBefore(endBusinessHours)) {
                if ((appointmentStartTime.equals(startBusinessHours) || appointmentStartTime.isAfter(startBusinessHours)) && (appointmentEndTime.equals(endBusinessHours) || appointmentEndTime.isBefore(endBusinessHours))) {
                    Query qs = new Query();
                    qs.addAppointment(title, description, location, type, start, end, customerId, userId);
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

    /** This method retrieves the contact name.
     *
     * The contact name is used as a parameter when the findContactId method is called to retrieve the contact ID.
     *
     */
    @FXML
    void displayContact(ActionEvent event) throws SQLException {
        addAppointmentContactComboBx.getSelectionModel().getSelectedItem().toString();
        String contactName = addAppointmentContactComboBx.getSelectionModel().getSelectedItem().toString();
        Query qs = new Query();
        qs.findContactId(contactName);

    }


    @FXML
    void displayCustomerId(ActionEvent event) {

    }

    /** This method cancels the add appointment procedure and return you to the main screen. */
    @FXML
    void onActionCancelAddAppointment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }



    /** This method loads the contact data when accessing the screen.
     *
     * The displayAllContact method is called to display all contact on the combo box
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Query qs = new Query();
        try {
            qs.displayAllContacts();
            qs.displayAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addAppointmentContactComboBx.setItems(qs.getAllContactsNames());
        addAppointmentCustomerIdComboBx.setItems(qs.getAllCustomerId());


    }
}
