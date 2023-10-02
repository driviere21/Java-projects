package Controller;


import DAO.Query;
import Model.Customer;
import com.example.schedulepluspro2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** This class updates the customer. */
public class UpdateCustomerController implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    private TextField UpdatedCustomerCustomerPhoneTxt;

    @FXML
    private Button updateCustomerCancelBtn;

    @FXML
    private TextField updateCustomerCreatedByTxt;

    @FXML
    private TextField updateCustomerCustomerAddressTxt;

    @FXML
    private ComboBox<String> updateCustomerCustomerCountryComboBx;

    @FXML
    private Label updateCustomerCustomerIdLbl;

    @FXML
    private TextField updateCustomerLastUpdatedTxt;

    @FXML
    private Button updateCustomerUpdateCustomerBtn;

    @FXML
    private TextField updateCustomerUpdatedByTxt;

    @FXML
    private TextField updatedCustomerCreatedDateTxt;

    @FXML
    private ComboBox<String> updatedCustomerDivisionComboBx;

    @FXML
    private Label updateCustomerDivisionIdLbl;

    @FXML
    private TextField updatedCustomerPostalCodeTxt;

    @FXML
    private TextField updateCustomerCustomerNameTextView;

    /** This method obtains the country ID using the country name. */
    @FXML
    void selectCountry(ActionEvent event) throws SQLException {
        String countryName = updateCustomerCustomerCountryComboBx.getSelectionModel().getSelectedItem().toString();
        Query qs = new Query();
        qs.retrieveCountryId(countryName);

        int countryId = qs.getnCountryId();
        qs.retrieveFirstLevelDId(countryId);
        updatedCustomerDivisionComboBx.setItems(qs.getFirstLevelDivision());

    }

    /** This method obtain the first level division ID using the division listed in the combo box. */
    @FXML
    void selectDivision(ActionEvent event) throws SQLException {
        String divisionName = updatedCustomerDivisionComboBx.getSelectionModel().getSelectedItem().toString();
        Query qs = new Query();
        qs.findFirstLevelId(divisionName);

    }

    /** This method cancels the update customer process and navigates back to the main screen. */
    @FXML
    void onActionCancelUpdateCustomer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }

    /** This method obtains the date form the update customer form and update the customer.
     *
     * After the customer is updated the method then navigate back to the main screen.
     *
     */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws SQLException, IOException {
        int customerId = Integer.parseInt(updateCustomerCustomerIdLbl.getText());
        String customerName  = updateCustomerCustomerNameTextView.getText();
        String address = updateCustomerCustomerAddressTxt.getText();
        String postalCode = updatedCustomerPostalCodeTxt.getText();
        String phone = UpdatedCustomerCustomerPhoneTxt.getText();
        String createDate = updatedCustomerCreatedDateTxt.getText();
        String createBy = updateCustomerCreatedByTxt.getText();
        String lastUpdate = String.valueOf(Timestamp.valueOf(LocalDateTime.now()));
        String lastUpdateBy = updateCustomerUpdatedByTxt.getText();
        int divisionId = Integer.parseInt(updateCustomerDivisionIdLbl.getText());

        Query.updateCustomer( customerId, customerName, address, postalCode, phone, createDate, createBy, lastUpdate, lastUpdateBy, divisionId);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }


    /** This method transfers the data from the customer selected to
     * the update customer screen to be pre-populated. */
    public void sendCustomer(Customer customer) throws SQLException {
        updateCustomerCustomerIdLbl.setText(String.valueOf(customer.getCustomerId()));
        updateCustomerCustomerNameTextView.setText(customer.getCustomerName());
        updateCustomerCustomerAddressTxt.setText(customer.getAddress());
        updatedCustomerPostalCodeTxt.setText(customer.getPostalCode());
        UpdatedCustomerCustomerPhoneTxt.setText(customer.getPhone());
        updatedCustomerCreatedDateTxt.setText(customer.getCreateDate());
        updateCustomerCreatedByTxt.setText(customer.getCreatedBy());
        updateCustomerLastUpdatedTxt.setText(customer.getLastUpdate());
        updateCustomerUpdatedByTxt.setText(customer.getLastUpdateBy());
        updateCustomerDivisionIdLbl.setText(String.valueOf(customer.getDivisionId()));

        Query qs = new Query();
        qs.findDivisionName(customer.getDivisionId());

        updateCustomerCustomerCountryComboBx.setValue(qs.getAllCountryNames().toString());
        updatedCustomerDivisionComboBx.setValue(qs.getAllDivisionNames().toString());




    }

    /** This method displays all the countries in the combo box. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Query qs = new Query();
        try {
            qs.displayAllCountries();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        updateCustomerCustomerCountryComboBx.setItems(qs.getAllCountries());



    }
}
