package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import lk.ijse.School.bo.BOFactory;
import lk.ijse.School.bo.custom.HostalManageBO;
import lk.ijse.School.dto.HostalDTO;


import lk.ijse.School.util.ValidationUtil;


import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class HostalManageFormController {
    public AnchorPane HostalManageContext;

    public TableColumn colHostalID;
    public TableColumn colNoOfBed;
    public TextField txtHostalID;
    public TextField txtHostalName;
    public TextField txtHostalType;
    public TextField txtHostalContact;
    public TextField txtNoOfRoom;
    public JFXButton btnSearch;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton savebtn;
    public TableColumn colHostalName;
    public TableColumn colHostalType;
    public TableColumn colHostalContact;
    public TableView tblAllHostal;

    private final HostalManageBO hostalManageBO= (HostalManageBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.HOSTALMANAGE);

    LinkedHashMap<TextField, Pattern> allValidations = new LinkedHashMap<>();

    Pattern contactPattern = Pattern.compile("^(0)[0-9][-]?[0-9]{8}$");


    public void initialize(){

        setTableUsers();
        validateUnit();
    }


    public void SearchOnAction(ActionEvent actionEvent){
    try {
        search();
    } catch (ClassNotFoundException |SQLException e) {
        e.printStackTrace();
    }
}

    private void search() throws ClassNotFoundException, SQLException {

        HostalDTO hostalDTO = hostalManageBO.searchHostal(txtHostalID.getText());
        if (hostalDTO!=null) {
            txtHostalName.setText(hostalDTO.getHostalName());
            txtHostalType.setText(hostalDTO.getHostalType());
            txtNoOfRoom.setText(hostalDTO.getNoOfRoom());
            txtHostalContact.setText(hostalDTO.getHostalContact());

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }



    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = hostalManageBO.deleteHostal(txtHostalID.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                setTableUsers();
                txtHostalName.clear();txtHostalType.clear();txtNoOfRoom.clear();txtHostalContact.clear();

            } else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
            }
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }



    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String HostalID = txtHostalID.getText();
        String Hostalname = txtHostalName.getText();
        String NoOfRoom = txtNoOfRoom.getText();
        String HostalType =  txtHostalType.getText();
        String HostalContact = txtHostalContact.getText();


        HostalDTO hostal=new HostalDTO(HostalID, Hostalname,HostalType, NoOfRoom,  HostalContact);
        boolean isUpdated= hostalManageBO.updateHostal(hostal);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableUsers();

        }
    }

    private void setTableUsers() {
        colHostalID.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Hostal, String>("hostalID"));
        colHostalName.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Hostal, String>("hostalName" ));
        colHostalType.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Hostal, String>("hostalType"));
        colNoOfBed.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Hostal, String>("noOfRoom" ));
        colHostalContact.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Hostal, String>("hostalContact"));


        try {
            ObservableList<lk.ijse.School.entity.Hostal> hostal= FXCollections.observableArrayList(hostalManageBO.getAll());
            tblAllHostal.setItems(hostal);
            for(lk.ijse.School.entity.Hostal ob : hostal){
                System.out.println(ob);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    public void SaveOnAction(ActionEvent actionEvent) {
        String HostalID = txtHostalID.getText();
        String HostalName = txtHostalName.getText();
        String HostalType = txtHostalType.getText();
        String NoOfRoom = txtNoOfRoom.getText();
        String HostalContact = txtHostalContact.getText();

        HostalDTO hostal = new HostalDTO(HostalID, HostalName, HostalType, NoOfRoom, HostalContact);
        lk.ijse.School.entity.Hostal hostal1 = new lk.ijse.School.entity.Hostal("","","","","");
        try {
            boolean isAdded = hostalManageBO.addNewHostal(hostal);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Hostal Added!").show();
                txtHostalID.clear();
                txtHostalName.clear();
                txtHostalType.clear();
                txtNoOfRoom.clear();
                txtHostalContact.clear();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }

    private void validateUnit(){
        savebtn.setDisable(true);
        allValidations.put(txtHostalContact,contactPattern);

    }

    public void keyReleasedOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(allValidations, savebtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }
}
