package com.example.schedulepluspro2;

import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
/** This class start the main application
 *
 * @author Dwayne Riviere
 * */
public class Main extends Application {

    public String resourceEng = "LogIn.fxml";
    public String resourceFr = "LogInFr.fxml";
    public String logInScreen = Locale.getDefault().getLanguage();


/**
 * This method check for the language selection on the user system.
 */
    @Override
    public void start(Stage stage) throws IOException {
        String resourceLng = resourceEng;
        if (logInScreen.equals("fr")) {
             resourceLng = resourceFr;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(resourceLng));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 400);
        stage.setTitle("Schedule Pro Plus");
        stage.setScene(scene);
        stage.show();


    }

    /** This is the main method.
     * This is the first method that gets called when you run the java application.*/
    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();





        launch();

        JDBC.closeConnection();
    }
}