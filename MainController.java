package Controller;

import DAO.JDBC;
import DAO.Query;
import Model.Appointment;
import Model.Customer;
import Model.DeleteMessage;
import Model.Message;
import com.example.schedulepluspro2.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/** This class start the application.
 *
 * The lambda expression used on the deleteAppointment event assisted in the reduction in the amount of code
 *
 *
 * */
public class MainController implements Initializable {
    Stage stage;
    Parent scene;




    @FXML
    private ToggleGroup appointmentDisplayTg;

    @FXML
    private Button mainAddAppointmentBtn;

    @FXML
    private Button mainAddCustomerBtn;

    @FXML
    private RadioButton mainAllAppointmentRbtn;


    @FXML
    private TableColumn<Appointment, Timestamp> mainAppCreateDate;


    @FXML
    private TableColumn<Appointment, Integer> mainAppContactId;

    @FXML
    private TableColumn<Appointment, String> mainAppCreatedBy;

    @FXML
    private TableColumn<Appointment, Integer> mainAppCustomerId;

    @FXML
    private TableColumn<Appointment, String> mainAppDescription;

    @FXML
    private TableColumn<Appointment, Timestamp> mainAppEnd;

    @FXML
    private TableColumn<Appointment, Integer> mainAppID;

    @FXML
    private TableColumn<Appointment, Timestamp> mainAppLastUpdate;

    @FXML
    private TableColumn<Appointment, String> mainAppLastUpdatedBy;

    @FXML
    private TableColumn<Appointment, String> mainAppLocation;

    @FXML
    private TableColumn<Appointment, Timestamp> mainAppStart;

    @FXML
    private TableColumn<Appointment, String> mainAppTitle;

    @FXML
    private TableColumn<Appointment, String> mainAppType;

    @FXML
    private TableColumn<Appointment, Integer> mainAppUserId;

    @FXML
    private TableView<Appointment> mainAppointmentsTableView;

    @FXML
    private RadioButton mainCurrentMonthRbtn;

    @FXML
    private RadioButton mainCurrentWeekRbtn;

    @FXML
    private Button mainExitBtn;


    @FXML
    private TableColumn<Customer, String> mainCusAddress;

    @FXML
    private TableColumn<Customer, String> mainCusCreateDate;

    @FXML
    private TableColumn<Customer, Timestamp> mainCusCreatedBy;

    @FXML
    private TableColumn<Customer, Integer> mainCusDivisionId;

    @FXML
    private TableColumn<Customer, Timestamp> mainCusLastUpdated;

    @FXML
    private TableColumn<Customer, String> mainCusLastUpdatedBY;

    @FXML
    private TableColumn<Customer, String> mainCusPhone;

    @FXML
    private TableColumn<Customer, String> mainCusPostalCode;

    @FXML
    private TableColumn<Customer, Integer> mainCustomerID;

    @FXML
    private TableColumn<Customer, String> mainCustomerName;

    @FXML
    private Button mainDeleteCustomerBtn;

    @FXML
    private Button mainUpdateCustomerBtn;

    @FXML
    private Button mainViewDeleteAppointmentBtn;

    @FXML
    private Button mainViewUpdateAppointmentBtn;

    @FXML
    private Button mainViewViewReportsAppointmentBtn;

    @FXML
    private Label mainAppointmentMessageLbl;

    @FXML
    private TextField mainSearchByCustomerTxtBx;



    /** This method displays all the appointments in the appointments table.
     *
     * This method call the displayAllAppointments method.
     *
     * The appointments information is then displayed in the appointments table on the main form.
     *
     *
     */
    @FXML
    void whenSelectedDisplayAllAppointments(ActionEvent event) throws SQLException {

        Query query = new Query();
        query.displayAllAppointments();

        mainAppointmentsTableView.setItems(query.getAllAppointments());

        mainAppID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        mainAppTitle.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        mainAppDescription.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        mainAppLocation.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        mainAppType.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        mainAppStart.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("start"));
        mainAppEnd.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("end"));
        mainAppCreateDate.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("createDate"));
        mainAppCreatedBy.setCellValueFactory(new PropertyValueFactory<Appointment, String>("createdBy"));
        mainAppLastUpdate.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("lastUpdate"));
        mainAppLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<Appointment, String>("lastUpdatedBy"));
        mainAppCustomerId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerId"));
        mainAppUserId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));
        mainAppContactId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));


    }

    /** This method displays the appointments for the current month.
     *
     * This method calls the displayMonthAppointments method and
     * displays the result in the appointment table on the main form
     * when the month radio button is selected.
     *
     */
    @FXML
    void whenSelectedDisplayMonthAppointments(ActionEvent event) throws SQLException {
        Query query = new Query();
        query.displayMonthAppointments();

        mainAppointmentsTableView.setItems(query.getMonthAppointments());

        mainAppID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        mainAppTitle.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        mainAppDescription.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        mainAppLocation.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        mainAppType.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        mainAppStart.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("start"));
        mainAppEnd.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("end"));
        mainAppCreateDate.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("createDate"));
        mainAppCreatedBy.setCellValueFactory(new PropertyValueFactory<Appointment, String>("createdBy"));
        mainAppLastUpdate.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("lastUpdate"));
        mainAppLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<Appointment, String>("lastUpdatedBy"));
        mainAppCustomerId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerId"));
        mainAppUserId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));
        mainAppContactId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));

    }


    /** This method displays the appointments for the current week.
     *
     * This method calls the displayWeekAppointments method and
     * displays the result in the appointment table on the main form
     * when the week radio button is selected.
     *
     */
    @FXML
    void whenSelectedDisplayWeekAppointments(ActionEvent event) throws SQLException {

        Query query = new Query();
        query.displayWeekAppointments();

        mainAppointmentsTableView.setItems(query.getWeekAppointments());

        mainAppID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        mainAppTitle.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        mainAppDescription.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        mainAppLocation.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        mainAppType.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        mainAppStart.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("start"));
        mainAppEnd.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("end"));
        mainAppCreateDate.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("createDate"));
        mainAppCreatedBy.setCellValueFactory(new PropertyValueFactory<Appointment, String>("createdBy"));
        mainAppLastUpdate.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("lastUpdate"));
        mainAppLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<Appointment, String>("lastUpdatedBy"));
        mainAppCustomerId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerId"));
        mainAppUserId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));
        mainAppContactId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));

    }






    @FXML
    private TableView<Customer> mainCustomersTableView;



    @FXML
    void searchByCustomer(ActionEvent event) throws SQLException {

        String searchName = mainSearchByCustomerTxtBx.getText();

        Query qs = new Query();
        qs.findCustomerName(searchName);

        mainCustomersTableView.setItems(qs.getSearchCustomerName());
        mainCustomerID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        mainCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        mainCusAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        mainCusPostalCode.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        mainCusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        mainCusCreateDate.setCellValueFactory(new PropertyValueFactory<Customer, String>("createDate"));
        mainCusCreatedBy.setCellValueFactory(new PropertyValueFactory<Customer, Timestamp>("createdBy"));
        mainCusLastUpdated.setCellValueFactory(new PropertyValueFactory<Customer, Timestamp>("lastUpdate"));
        mainCusLastUpdatedBY.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastUpdateBy"));
        mainCusDivisionId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("divisionId"));





    }



    /** This method navigates to the add appointment screen. */
    @FXML
    void onActionDisplayAddAppointment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddAppointment.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1100, 450);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }

    /** This method navigates to the add customer screen. */
    @FXML
    void onActionDisplayAddCustomer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddCustomer.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1100, 450);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }

    /** This method navigates to the reports screen. */
    @FXML
    void onActionDisplayReports(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Report.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1100, 900);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }


    /** This method delete the selected customer.
     *
     * This method also validates that the selected customer does not have
     * an appointment. Also, a confirmation is displayed when the customer
     * was deleted.
     *
     */
    @FXML
    void onActionDisplayDeleteCustomer(ActionEvent event) throws SQLException {
        Customer cTableView = mainCustomersTableView.getSelectionModel().getSelectedItem();
        String alertMsg = cTableView.getCustomerName().toString() + " " + "was delete";
        int iD = cTableView.getCustomerId();
        Query qs = new Query();
        qs.validateDelete(iD);

        if (qs.getAppointmentIdList().isEmpty()) {
            Query.deleteCustomer(iD);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, alertMsg);
            alert.show();

        } else {

            Query.deleteAppointment2(iD);
            Query.deleteCustomer(iD);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, alertMsg);
            alert.show();



        }

        qs.displayAllCustomers();
        qs.displayAllAppointments();
        mainCustomersTableView.setItems(qs.getAllCustomers());
        mainAppointmentsTableView.setItems(qs.getAllAppointments());







        /*
        try {
            Customer cTableView = mainCustomersTableView.getSelectionModel().getSelectedItem();
            int iD = cTableView.getCustomerId();
             String alertMsg = cTableView.getCustomerName().toString() + " " + "was delete";

            Query qs = new Query();
            qs.validateDelete(iD);

            Query.deleteCustomer(iD);


            Query query = new Query();
            try {
                query.displayAllCustomers();
                query.getAllCustomers();
                mainCustomersTableView.setItems(query.getAllCustomers());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, alertMsg);
            alert.show();


        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please Delete the assigned Appointment first");
            alert.showAndWait();
        }

 */
    }

    /** This method navigates to the update customer screen. */
    @FXML
    void onActionDisplayUpdateCustomer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UpdateCustomer.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1100, 800);
        stage.setTitle("Update Customer");
        stage.setScene(scene);
        stage.show();

        try {

            UpdateCustomerController MPController = fxmlLoader.getController();
            MPController.sendCustomer(mainCustomersTableView.getSelectionModel().getSelectedItem());
        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer to edit");
          alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /** This method delete the selected appointment.
     *
     * A confirmation is displayed when the appointment
     * was deleted.
     *
     * The lambda expression below assisted in the reduction in the amount of code
     *
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) throws SQLException {
        Appointment aTableView = mainAppointmentsTableView.getSelectionModel().getSelectedItem();
        int iD = aTableView.getAppointmentId();
        String appointmentType = aTableView.getType();

        Query.deleteAppointment(iD);

        Query query = new Query();
        try {
           // query.displayAllCustomers();
            query.displayAllAppointments();
            query.getAllAppointments();
            mainAppointmentsTableView.setItems(query.getAllAppointments());
            DeleteMessage msg = (appId, appType ) -> "Appointment# " + iD + "Type: " + appointmentType + " was cancelled.";
            mainAppointmentMessageLbl.setText(msg.deleteMessage(iD, appointmentType));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /** This method navigates to the update appointment screen. */
    @FXML
    void onActionDisplayUpdateAppointment(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UpdateAppointment.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1100, 450);
        stage.setTitle("Update Appointment");
        stage.setScene(scene);
        stage.show();


        try {

            UpdateAppointmentController MPController = fxmlLoader.getController();
            MPController.sendAppointment(mainAppointmentsTableView.getSelectionModel().getSelectedItem());
        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an appointment to edit");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /** This method exits the application. */
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);

    }



    /** This method displays the data for both the appointments and customers when the screen starts.
     *
     * The lambda expression in this method helps in code re-usability where I can just call the lambda expression
     * instead of re-writing the string output message.
     * It also assisted in the use of functional programming.
     *
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainAllAppointmentRbtn.setSelected(true);






        Query query = new Query();
        try {
            query.displayAllCustomers();
            query.displayAllAppointments();
            query.displayDueAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        mainCustomersTableView.setItems(query.getAllCustomers());

        mainCustomerID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
        mainCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        mainCusAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        mainCusPostalCode.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        mainCusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        mainCusCreateDate.setCellValueFactory(new PropertyValueFactory<Customer, String>("createDate"));
        mainCusCreatedBy.setCellValueFactory(new PropertyValueFactory<Customer, Timestamp>("createdBy"));
        mainCusLastUpdated.setCellValueFactory(new PropertyValueFactory<Customer, Timestamp>("lastUpdate"));
        mainCusLastUpdatedBY.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastUpdateBy"));
        mainCusDivisionId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("divisionId"));




        mainAppointmentsTableView.setItems(query.getAllAppointments());

        mainAppID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentId"));
        mainAppTitle.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        mainAppDescription.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        mainAppLocation.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        mainAppType.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        mainAppStart.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("start"));
        mainAppEnd.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("end"));
        mainAppCreateDate.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("createDate"));
        mainAppCreatedBy.setCellValueFactory(new PropertyValueFactory<Appointment, String>("createdBy"));
        mainAppLastUpdate.setCellValueFactory(new PropertyValueFactory<Appointment, Timestamp>("lastUpdate"));
        mainAppLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<Appointment, String>("lastUpdatedBy"));
        mainAppCustomerId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerId"));
        mainAppUserId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));
        mainAppContactId.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userId"));

        Message notification = () -> "NO UPCOMING APPOINTMENTS";


        if (!query.getFifteenMinutesDueAppointments().isEmpty()) {
            Appointment upcomingAppointment = query.getFifteenMinutesDueAppointments().get(0);

            int upcomingAppointmentId = upcomingAppointment.getAppointmentId();
            String upcomingAppointmentDate = upcomingAppointment.getStart();


            if (upcomingAppointmentId == 0) {
                mainAppointmentMessageLbl.setText(notification.displayMessage());
            } else {
                mainAppointmentMessageLbl.setText("Appointment " + upcomingAppointmentId + " on " + upcomingAppointmentDate.toString().substring(0, 10) + " at " + upcomingAppointmentDate.toString().substring(11) +" is coming due");
            }
        } else {
            mainAppointmentMessageLbl.setText(notification.displayMessage());
        }



    }

}
