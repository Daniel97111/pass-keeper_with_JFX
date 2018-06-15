package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
//import org.apache.commons.codec.digest.DigestUtils;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.getKeyCode;

public class Controller implements Initializable {

    @FXML
    private Pane firstPane;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label hello;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        username.setFocusTraversable(false);
        password.setFocusTraversable(false);
    }

    @FXML
    public void handleLogin(ActionEvent eventw) throws IOException {
        String userName = username.getText();
        String pass = password.getText();

        if (userName.equals("admin12") && pass.equals("pass12")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/sample2.fxml"));
            firstPane.getChildren().setAll(pane);
        } else hello.setText("Wrong username or password !!!!");
    }

    @FXML
    void handleCancle(ActionEvent event) {
        System.exit(0);
    }
}
