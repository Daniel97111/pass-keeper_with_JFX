package sample;

import apk.SymmetricKey;
import apk.controlPanel.FileSafeController;
import apk.model.PasswordEntry;
import apk.model.PasswordSafe;
import com.sun.org.apache.xml.internal.security.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;

public class Controller2 {

    private SymmetricKey symmetricKey;
    private File dir = new File("src/main/resources/pass");
    private File[] filelist = dir.listFiles();

    private FileSafeController fileSafe = new FileSafeController();
    private PasswordSafe passwordSafe = fileSafe.init("src/main/resources/pass/new-pass-keeper-file.pwm");

    @FXML
    private AnchorPane firstAnchor;
    @FXML
    private TextField display1;
    @FXML
    private TextField display2;
    @FXML
    private TextField display3;
    @FXML
    private TextField display1Book;
    @FXML
    private TextArea bigTextArea;
    @FXML
    private TextField displayIdToRemove;


    @FXML
    void lockButton_saveToFile(ActionEvent event) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Alert Wind");
        al.setHeaderText(null);
        Stage stage = (Stage) al.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/icons/Key1.png"));


        String daneServ = display1.getText();
        String daneLog = display2.getText();
        String danePass = display3.getText();

        PasswordEntry entry = new PasswordEntry(0, daneServ, daneLog, danePass);
        if (!daneServ.equals("") && !daneLog.equals("") && !danePass.equals("")) {
            if (!passwordSafe.exists(daneServ)) {
                entry = passwordSafe.addEntries(entry.getService(), entry.getLogin(), entry.getPassword());
                fileSafe.saveToFile(Arrays.asList(entry), true);
                symmetricKey = new SymmetricKey("*#@#$!@Egg", 16, "AES");
                Arrays.asList(filelist).forEach(file -> {
                    try {
                        symmetricKey.encryptFile(file);
                    } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
                        System.err.println("Couldn't encrypt " + file.getName() + " : " + e.getMessage());
                    }
                });
                //Show on the console only! ->
                System.out.println("Ser: " + daneServ + "\nLog: " + daneLog + "\nPass: " + danePass + "\n");
                //Clear displays out of everything
                display1.clear();
                display2.clear();
                display3.clear();
                al = new Alert(Alert.AlertType.INFORMATION);
                al.setHeaderText(null);
                al.setTitle("Success!");
                al.setContentText("Service : " + entry.getService() + "\n\nAdded and Encrypted successfully\n:)");

                al.show();
            } else {
                System.out.println("Service: " + entry.getService() + " already exist\n");
                /*display1.clear();
                display2.clear();
                display3.clear();*/
                al.setContentText("Service: " + entry.getService() + " already exist");
                al.show();
            }
        } else {
            System.out.println("Blank space\n");
            al.setContentText("You have not fulfilled all text fields!\nTry again");
            al.show();
        }
    }

    @FXML
    void unlockedButton(ActionEvent event) {
        String existServer = display1Book.getText();

        bigTextArea.setText(passwordSafe.show(existServer));

        System.out.println("podany serv: " + existServer + "\notrzymano: " + bigTextArea.getText() + "\n");
        display1Book.clear();
    }

    @FXML
    void deleteButton(ActionEvent event) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        int idToRemove = Integer.parseInt(displayIdToRemove.getText());
        passwordSafe.removeEntries(idToRemove);
        fileSafe.saveToFile(new ArrayList<>(passwordSafe.all()), false);

        symmetricKey = new SymmetricKey("*#@#$!@Egg", 16, "AES");
        Arrays.asList(filelist).forEach(file -> {
            try {
                symmetricKey.encryptFile(file);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                    | IOException e) {
                System.err.println("Couldn't encrypt " + file.getName() + ": " + e.getMessage());
            }
        });

        System.out.println("id to remove: " + idToRemove + "\n");
        displayIdToRemove.clear();
    }

    @FXML
    void handleCancle() {
        System.exit(0);
    }
}
