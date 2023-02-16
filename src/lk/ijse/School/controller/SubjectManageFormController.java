package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import lk.ijse.School.bo.BOFactory;
import lk.ijse.School.bo.custom.SubjectManageBO;
import lk.ijse.School.dto.SubjectDTO;



import java.sql.SQLException;

public class SubjectManageFormController {


    public AnchorPane SubjectContext;
    public TableColumn colSubjectID;
    public TableColumn colSubjectName;
    public TableColumn colSubjectMedium;
    public JFXButton btnSubjectSearch;
    public JFXButton btnSubjectDelete;
    public JFXButton btnUpdateSubject;
    public JFXButton btnSubjectSave;
    public TextField txtSubjectId;
    public TextField txtSubjectName;
    public TextField txtSubjectMeadium;
    public ComboBox cmbMeadium;
    public TableView tblAllSubject;
    private final SubjectManageBO subjectManageBO = (SubjectManageBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.SUBJECTMANAGE);

    public void initialize(){
        cmbMeadium.getItems().setAll("sinhala","English");
        setTableSubjects();

    }

    public void SubjectSearchOnAction(ActionEvent actionEvent)  {
        try {
            search();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    private void search() throws ClassNotFoundException, SQLException {

        SubjectDTO subjectDTO = subjectManageBO.searchSubject(txtSubjectId.getText());
        if (subjectDTO!=null) {
           txtSubjectName.setText(subjectDTO.getSubjectName());
            cmbMeadium.setValue(subjectDTO.getMeadium());
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }


    public void SubjectDeleteOnAction(ActionEvent actionEvent)throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = subjectManageBO.deleteSubject(txtSubjectId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                setTableSubjects();
                txtSubjectName.clear();txtSubjectMeadium.clear();

            } else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
            }
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }





    public void SubjectUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String SubjectID = txtSubjectId.getText();
        String SubjectName = txtSubjectName.getText();
        String Meadium = cmbMeadium.getAccessibleText();


        SubjectDTO subject=new SubjectDTO(SubjectID, SubjectName, Meadium);
        boolean isUpdated= subjectManageBO.updateSubject(subject);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableSubjects();

        }
    }

    private void setTableSubjects()  {
        colSubjectID.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Subject, String>("subjectID"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Subject, String>("subjectName" ));
        colSubjectMedium.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Subject, String>("meadium"));


        try {
            ObservableList<lk.ijse.School.entity.Subject> subject= FXCollections.observableArrayList(subjectManageBO.getAll());
            tblAllSubject.setItems(subject);
            for(lk.ijse.School.entity.Subject ob : subject){
                System.out.println(ob);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




    public void SubjectSaveOnAction(ActionEvent actionEvent)throws RuntimeException {
        String SubjectID = txtSubjectId.getText();
        String SubjectName = txtSubjectName.getText();
        String Meadium = (String) cmbMeadium.getValue();

        SubjectDTO subject = new SubjectDTO(SubjectID,SubjectName,Meadium);
        try {
            boolean isAdded =  subjectManageBO.addNewSubject(subject);;

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Subject Added!").show();
                txtSubjectId.clear();
                txtSubjectName.clear();
                cmbMeadium.getSelectionModel().clearSelection();


            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);


        }


    }
}



