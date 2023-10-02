package Controller;


import DAO.Query;
import Model.Country;
import com.example.schedulepluspro2.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/** This class add new customers. */
public class AddCustomerController implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    private Button AdCustomerAddCustomerBtn;

    @FXML
    private Button AddCustomerCancelBtn;

    @FXML
    private TextField addCustomerAddressTxt;

    @FXML
    private ComboBox<String> addCustomerCountryComboBx;


    @FXML
    private TextField addCustomerCustomerNametxt;

    @FXML
    private ComboBox<String> addCustomerDivisionComboBx;

    @FXML
    private TextField addCustomerPhoneTxt;

    @FXML
    private TextField addCustomerPostalCodeTxt;

    @FXML
    private CheckBox addCustomerGoldMemberCheckBx;




    @FXML
    void addCustomerSelectGoldMember(ActionEvent event) {

    }


    /** This method retrieves the country ID.
     *
     * This method retrieves the country name and uses the that name to retrieves the country ID.
     *
     */
    @FXML
    void selectCountry(ActionEvent event) throws SQLException {
       String countryName = addCustomerCountryComboBx.getSelectionModel().getSelectedItem().toString();
       Query qs = new Query();
       qs.retrieveCountryId(countryName);

       int countryId = qs.getnCountryId();
       qs.retrieveFirstLevelDId(countryId);
       addCustomerDivisionComboBx.setItems(qs.getFirstLevelDivision());




    }

    /** This method retrieves the first level division ID. */
    @FXML
    void selectDivision(ActionEvent event) throws SQLException {
        String divisionName = addCustomerDivisionComboBx.getSelectionModel().getSelectedItem().toString();
        Query qs = new Query();
        qs.findFirstLevelId(divisionName);

    }


    /** This method adds the customer to the customers table.
     *
     * The customer information is retrieved from the form and is then added to the customers table.
     * When the customer is successfully added the users is then navigated to the main screen.
     *
     */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws SQLException, IOException {
        String customerName = addCustomerCustomerNametxt.getText();
        String address = addCustomerAddressTxt.getText();
        String postalCode = addCustomerPostalCodeTxt.getText();
        String phone = addCustomerPhoneTxt.getText();


        if (addCustomerGoldMemberCheckBx.isSelected()) {
            String newCustomerName = customerName + "   (GM)";
            Query qs = new Query();
            qs.addCustomer(newCustomerName, address,postalCode , phone);
        } else {
            Query qs = new Query();
            qs.addCustomer(customerName, address,postalCode , phone);

        }
/*
        Query qs = new Query();
        qs.addCustomer(customerName, address,postalCode , phone);

 */


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }

    /** This method cancels the add customer procedure and return you to the main screen. */
    @FXML
    void onActionCancelAddCustomer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();

    }

    /** This method loads the countries data when accessing the screen.
     *
     * The displayAllCountries method is called to display all countries on the combo box
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Query qs = new Query();
        try {
            qs.displayAllCountries();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        addCustomerCountryComboBx.setItems(qs.getAllCountries());


    }
}
