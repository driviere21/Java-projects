package Controller;


import DAO.Query;
import Model.AppointmentReport;
import Model.AppointmentSchedule;
import Model.CustomerReport;
import com.example.schedulepluspro2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/** This class populates the reports. */
public class ReportController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<AppointmentSchedule> ReportAppScheduleTableView;

    @FXML
    private TableView<AppointmentReport> ReportAppSummaryTableView;

    @FXML
    private TableView<CustomerReport> ReportCustomerSummaryTableView;

    @FXML
    private TableColumn<CustomerReport, String> customerSummaryCustomersCol;

    @FXML
    private TableColumn<CustomerReport, String> customerSummaryStatesCol;

    @FXML
    private TableColumn<AppointmentSchedule, Integer> appScheduleAppointmentIdCol;

    @FXML
    private TableColumn<AppointmentSchedule, String> appScheduleContactCol;

    @FXML
    private TableColumn<AppointmentSchedule, Integer> appScheduleCustomerIdCol;

    @FXML
    private TableColumn<AppointmentSchedule, String> appScheduleDescriptionCol;

    @FXML
    private TableColumn<AppointmentSchedule, String> appScheduleEndCol;

    @FXML
    private TableColumn<AppointmentSchedule, String> appScheduleStartCol;

    @FXML
    private TableColumn<AppointmentSchedule, String> appScheduleTitleCol;

    @FXML
    private TableColumn<AppointmentSchedule, String> appScheduleTypeCol;

    @FXML
    private TableColumn<AppointmentReport, String> appSummaryMonthCol;

    @FXML
    private TableColumn<AppointmentReport, Integer> appSummaryNoAppCol;

    @FXML
    private TableColumn<AppointmentReport, String> appSummaryTypeCol;

    @FXML
    private Button reportExitBtn;

    @FXML
    private ComboBox<String> reportContactBx;

    @FXML
    void selectContact(ActionEvent event) throws SQLException {
        String contactName2 = reportContactBx.getSelectionModel().getSelectedItem().toString();

        Query qs = new Query();
        qs.findContactId(contactName2);
        qs.displayAppointmentSchedule(Query.newContactId);

        ReportAppScheduleTableView.setItems(qs.getAllAppointmentSchedule());
        appScheduleAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appScheduleContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appScheduleCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appScheduleDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appScheduleEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        appScheduleStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        appScheduleTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appScheduleTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));


    }


    /** This method navigates back to the main screen. */
    @FXML
    void onActionDisplayMainView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }



    /** This displays all the details the report. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Query query = new Query();
        try {
            query.displayAppointmentReport();
            query.displayCustomerReport();
            query.displayAllContacts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        reportContactBx.setItems(query.getAllContactsNames());
        reportContactBx.setValue(String.valueOf(query.getAllContactsNames().get(0)));

        String contactName = reportContactBx.getSelectionModel().getSelectedItem();

        try {
            query.findContactId(contactName);
            query.displayAppointmentSchedule(Query.newContactId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        ReportAppSummaryTableView.setItems(query.getAllAppointmentReport());
        AppointmentReport ap = new AppointmentReport();
        String month = ap.getMonth();
        appSummaryMonthCol.setCellValueFactory(new PropertyValueFactory<>("Month"));
        //appSummaryMonthCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appSummaryTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appSummaryNoAppCol.setCellValueFactory(new PropertyValueFactory<>("amount"));


        ReportAppScheduleTableView.setItems(query.getAllAppointmentSchedule());
        appScheduleAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appScheduleContactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appScheduleCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appScheduleDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appScheduleEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        appScheduleStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        appScheduleTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appScheduleTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));


        ReportCustomerSummaryTableView.setItems(query.getAllCustomerReport());
        customerSummaryCustomersCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerSummaryStatesCol.setCellValueFactory(new PropertyValueFactory<>("division"));


    }
}
