package lk.ijse.School.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.School.dao.DAOFactory;
import lk.ijse.School.dao.custom.TeacherDAO;
import lk.ijse.School.dao.custom.UserDAO;

import lk.ijse.School.entity.NewUser;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane LoginDashboardContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public Button btnLogin;
    public Label lblLoginText;

    static String  userName;
    private final UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    public void LoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
            if (actionEvent.getSource()==btnLogin) {
                if (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()) {
                    lblLoginText.setText("Please enter your data.");
                }
                 userName = txtUserName.getText();
                NewUser newUser = userDAO.search(userName);
                System.out.println(newUser.toString());
                if(newUser==null){
                    new Alert(Alert.AlertType.ERROR,"User Not Found").show();
                    return;
                }
                String password = txtPassword.getText();
                if (password.equals(newUser.getPassword())) {
                    System.out.println("login success");
                    Stage window = (Stage)LoginDashboardContext.getScene().getWindow();
                    window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));


                } else  {
                    lblLoginText.setText("Wrong password!");
                }

            }
        }
    }


