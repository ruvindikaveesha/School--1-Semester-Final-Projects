package lk.ijse.School.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainFormController {
    public Button btnStart;
    public AnchorPane context;

    public void StartOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/LoginForm.fxml"));
        context.getChildren().clear();
        context.getChildren().add(parent);
    }


    }
