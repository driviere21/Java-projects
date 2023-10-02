package Controller;


import DAO.Query;
import Model.PrintWriterOutput;
import com.example.schedulepluspro2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/** This class manages the log-in screen. */
public class LogInFrController implements Initializable {
    Stage stage;
    Parent scene;

    private String userName;
    private String passWord;

    String location = String.valueOf(ZoneId.systemDefault());

    @FXML
    private Button LogInLogInBtn;

    @FXML
    private Button logInCancelBtn;

    @FXML
    private TextField logInPasswordTxt;

    @FXML
    private TextField logInUsernameTxt;

    @FXML
    public Label logInMessageLbl;

    @FXML
    private Label logInZoneId;

    @FXML
    void onActionCancel(ActionEvent event) {
        System.exit(0);

    }

    public  static String eUser, ePassword;


    /** This method retrieves the username and password entered by the user.
     *
     * @param event userName
     *
     * @param event passWord
     *
     *
     * This also validates that the username and password entered on the log-in form
     * is in the users table in the database.
     *
     * This method also logs all the log-in attempts by the user.
     *
     */
    @FXML
    void onActionLogIn(ActionEvent event) throws SQLException, IOException {

        userName = logInUsernameTxt.getText();
        passWord = logInPasswordTxt.getText();

        boolean status = Query.userLogIn(userName, passWord);

        if (userName.isEmpty() || passWord.isEmpty()) {
            logInMessageLbl.setText("Veuillez entrer un nom d'utilisateur et un mot de passe");
        }
        else if (status){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1400, 900);
            stage.setTitle("Add New Customer");
            stage.setScene(scene);
            stage.show();

            eUser = Query.logUsers(userName);
            LogInController.eUser = eUser;

            try{
                LocalDateTime localDateTime = LocalDateTime.now();
                PrintWriter pw = new PrintWriter(new FileOutputStream(new File("login_activity.txt"), true));
                pw.append(userName + " successfully logged in on " + localDateTime + "\n");
                pw.close();
            }catch (FileNotFoundException var2){
                Logger.getLogger(PrintWriterOutput.class.getName()).log(Level.SEVERE, (String) null, var2);
            }
        }
        else {
            logInMessageLbl.setText("l'identifiant ou le mot de passe esr incorrect");

            try{
                LocalDateTime localDateTime = LocalDateTime.now();
                PrintWriter pw = new PrintWriter(new FileOutputStream(new File("login_activity.txt"), true));
                pw.append(userName + " log-in attempt failed " + localDateTime + "\n");
                pw.close();
            }catch (FileNotFoundException var2){
                Logger.getLogger(PrintWriterOutput.class.getName()).log(Level.SEVERE, (String) null, var2);
            }

        }

    }



    /** This  method identify the time zone ID and displays it. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logInZoneId.setText(location);

    }
}
