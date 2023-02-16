package lk.ijse.School.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DashBoardFormController {
    public Label lblUserName1;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane context;
    public AnchorPane DashBoardFullContext;


    public void initialize(){
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy:MM:dd");
            lblTime.setText(LocalDateTime.now().format(formatter));
            lblDate.setText(LocalDateTime.now().format(formatter1));
        }),new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }



    public void HomeOnAction(MouseEvent mouseEvent) {
    }

    public void StudentManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/StudentManageForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);
    }

    public void TeacherManageOnAction(ActionEvent actionEvent) throws IOException {
            Parent parent = FXMLLoader.
                    load(getClass().getResource("../view/TeacherManageForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);

    }

    public void ClassManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/ClassRoomManageForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);

    }



    public void SubjectManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/SubjectManageForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);

    }


    public void HostalManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/HostalManageForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);

    }


    public void SportManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/SportManageForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);

    }


    public void ExamManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/StudentExamDetails.fxml"));
       context.getChildren().clear();
        context.getChildren().add(parent);
    }





    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/DashBoardForm.fxml"));
        DashBoardFullContext.getChildren().clear();
        DashBoardFullContext.getChildren().add(parent);

    }


    public void LogOutOnAction(ActionEvent actionEvent)  throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to log out", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if(buttonType.get().equals(ButtonType.YES)){
            URL resource = getClass().getResource("../view/LoginForm.fxml");
            Parent load = FXMLLoader.load(resource);
            DashBoardFullContext.getChildren().clear();
            DashBoardFullContext.getChildren().add(load);
        }



    }






    public void NewUserOnAction(ActionEvent actionEvent)  throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/NewUserForm.fxml"));
        //context.getChildren().clear();
        Stage  window = (Stage) context.getScene().getWindow();
        window.setScene(new Scene(parent));

    }




}
