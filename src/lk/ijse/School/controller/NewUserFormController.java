package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import lk.ijse.School.bo.custom.UserManageBO;
import lk.ijse.School.bo.custom.impl.UserManageBOImpl;
import lk.ijse.School.entity.NewUser;

import lk.ijse.School.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class NewUserFormController {
    public AnchorPane addUserContext;
    public JFXTextField txtUserNIC;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public TableView tblUser;
    public TableColumn colNIC;
    public TableColumn colName;
    public TableColumn colType;
    public JFXTextField txtUserType;
    public JFXButton btnUserDelete;
    public JFXButton btnUserUpdate;
    public JFXButton btnUserSave;
    public JFXButton btnUserSearch;
    public Button btnBack;
    public TableColumn colPassword;
    private Object Stage;
    private UserManageBO userManageBO = new UserManageBOImpl();

    LinkedHashMap<TextField, Pattern> allValidations = new LinkedHashMap<>();

    Pattern contactPattern = Pattern.compile("^(0)[0-9][-]?[0-9]{8}$");


    public void initialize() throws SQLException, ClassNotFoundException {
        setTableUsers();
    }

    public void UserDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = userManageBO.delete(txtUserNIC.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                setTableUsers();
                txtUserName.clear();txtPassword.clear();txtUserType.clear();

            } else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
            }
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }


    public void UserUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String UserNIC = txtUserNIC.getText();
            String UserName= txtUserName.getText();
            String Password = txtPassword.getText();
            String UserType =  txtUserType.getText();


           NewUser newUser=new NewUser(UserNIC, UserName, Password,UserType);
            boolean isUpdated= userManageBO.update(newUser);
            if(isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
                setTableUsers();

            }
        }

    private void setTableUsers() throws SQLException, ClassNotFoundException {
        colNIC.setCellValueFactory(new PropertyValueFactory<NewUser, String>("UserNIC"));
        colName.setCellValueFactory(new PropertyValueFactory<NewUser, String>("UserName" ));
        colPassword.setCellValueFactory(new PropertyValueFactory<NewUser, String>("Password"));
        colType.setCellValueFactory(new PropertyValueFactory<NewUser, String>("UserType"));

        try {

            ObservableList<NewUser> newUsers = FXCollections.observableArrayList(userManageBO.getAll());
            tblUser.setItems(newUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void UserSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
            String UserNIC = txtUserNIC.getText();
            String UserName = txtUserName.getText();
            String Password= txtPassword.getText();
            String UserType= txtUserType.getText();

            NewUser newUser = new NewUser(UserNIC, UserName, UserType, Password);
            NewUser newUser1 = new NewUser("","","","");
            try {
                boolean isAdded = userManageBO.add(newUser);
                ;

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "user Added!").show();
                    setTableUsers();
                    txtUserNIC.clear();
                    txtUserName.clear();
                    txtPassword.clear();
                    txtUserType.clear();

                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);


            }
    }

    public void UserSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    private void search() throws ClassNotFoundException, SQLException {

        ResultSet result = CrudUtil.execute("SELECT * FROM user WHERE UserNIC=?",txtUserNIC.getText());
        if (result.next()) {
            txtUserName.setText(result.getString(2));
            txtPassword.setText(result.getString(3));
            txtUserType.setText(result.getString(4));

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }


    private void setUi(String ui) throws IOException {
        Stage stage=(Stage) addUserContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+ui+".fxml"))));
                stage.centerOnScreen();
    }
    public void tableMouseClicked(MouseEvent mouseEvent) {

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");
    }


}
