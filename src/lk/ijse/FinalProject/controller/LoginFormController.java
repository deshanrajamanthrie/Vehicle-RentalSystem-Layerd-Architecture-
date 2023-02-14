package lk.ijse.FinalProject.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginFormController {
    public ImageView lblCloseEye;
    public ImageView lblOpenEye;
    public Label lblTime;
    public TextField txtUserName;
    public TextField txtShowPasseword;
    public PasswordField txtHiddenPasseword;
    String password;

    public void initialize() {
        txtShowPasseword.setVisible(false);
        lblOpenEye.setVisible(false);
        RunningTime();
    }
    public void HiddenPassewordOnAction(ActionEvent keyEvent) {
        password =txtHiddenPasseword.getText();
        txtShowPasseword.setText(password);

    }
    public void ShowPasswordOnAction(ActionEvent keyEvent) {
        password =txtShowPasseword.getText();
        txtHiddenPasseword.setText(password);
    }
    public void Close_Eye_ClickOnAction(MouseEvent mouseEvent) {
        txtShowPasseword.setVisible(true);
        lblOpenEye.setVisible(true);
        lblCloseEye.setVisible(false);
        txtHiddenPasseword.setVisible(false);
    }
    public void Open_Eye_ClickOnAction(MouseEvent mouseEvent) {
        txtShowPasseword.setVisible(false);
        lblOpenEye.setVisible(false);
        lblCloseEye.setVisible(true);
        txtHiddenPasseword.setVisible(true);
    }
    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("deshan_r") && txtHiddenPasseword.getText().equalsIgnoreCase("1234")) {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("DashBoard");
            stage.centerOnScreen();
            stage.show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Loging Failed,Incorrect Passeword or User Name").show();
        }
    }
    private void RunningTime() {
        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                });
            }
        });
        thread.start();
    }


}
