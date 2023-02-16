package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.School.bo.BOFactory;
import lk.ijse.School.bo.custom.ClassRoomManageBO;
import lk.ijse.School.dto.ClassRoomDTO;

import java.sql.SQLException;

public class ClassRoomManageFormController {

    public AnchorPane ClassManageContext;
    public TableView tblClassDetails;
    public TableColumn colId;
    public TableColumn colSection;
    public TableColumn colDetails;
    public TableColumn colName;
    public JFXTextField txtClassID;
    public JFXTextField txtClassName;
    public JFXTextField txtSectionID;
    public JFXTextField txtClassDetails;
    public JFXButton btnSubjectSearch;
    public JFXButton btnSubjectDelete;
    public JFXButton btnUpdateSubject;
    public JFXButton btnSubjectSave;
    public JFXComboBox cbSection;


    private final ClassRoomManageBO classRoomManageBO= (ClassRoomManageBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.CLASSROOMMANAGE);

    public void initialize(){
        setComboBox();
        setTableUsers();
    }

    public void ClassSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    public void setComboBox(){
        cbSection.getItems().add("Upper_Section");
        cbSection.getItems().add("Lower_Section");
        //cbSection.getValue().toString();
    }

    private void search() throws ClassNotFoundException, SQLException {

        ClassRoomDTO classRoomDTO = classRoomManageBO.searchClassRoom(txtClassID.getText());

        if(classRoomDTO!=null){
            txtClassName.setText(classRoomDTO.getClassName());
            cbSection.getSelectionModel().select(classRoomDTO.getSectionID());
            txtClassDetails.setText(classRoomDTO.getClassName());
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }


    public void ClassDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = classRoomManageBO.deleteClassRoom(txtClassID.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();

                txtClassName.clear();txtSectionID.clear();txtClassDetails.clear();;

            } else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
            }
        }catch (ClassNotFoundException ex){



        }
    }




    public void ClassUpdateOnAction(ActionEvent actionEvent)  throws SQLException, ClassNotFoundException {
        String ClassID = txtClassID.getText();
        String ClassName = txtClassName.getText();
        String SectionID = cbSection.getValue().toString();//txtSectionID.getText();


        ClassRoomDTO classRoom=new ClassRoomDTO(ClassID, ClassName, SectionID);
        boolean isUpdated= classRoomManageBO.updateClassRoom(classRoom);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableUsers();

        }
    }

    private void setTableUsers() {
        colId.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.ClassRoom, String>("clssID"));
        colName.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.ClassRoom, String>("className"));
        colSection.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.ClassRoom, String>("sectionID"));
        //colDetails.setCellValueFactory(new PropertyValueFactory<ClassRoom, String>("classDetails"));


        try {
            ObservableList<lk.ijse.School.entity.ClassRoom> classRooms= FXCollections.observableArrayList(classRoomManageBO.getAll());
            tblClassDetails.setItems(classRooms);
            for(lk.ijse.School.entity.ClassRoom ob : classRooms){
                System.out.println(ob.getClssID()+"-"+ob.getClassName()+"-"+ob.getSectionID());
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




    public void ClassSaveOnAction(ActionEvent actionEvent) throws RuntimeException {
            String ClassID = txtClassID.getText();
            String ClassName = txtClassName.getText();
            if(cbSection.getSelectionModel().getSelectedIndex()==-1){
                new Alert(Alert.AlertType.ERROR,"Select Section From Combo Box").show();
                return;
            }
            String SectionID = cbSection.getValue().toString();


            ClassRoomDTO classRoom = new ClassRoomDTO(ClassID,ClassName,SectionID);

            try {
                System.out.println(SectionID);
                boolean isAdded =  classRoomManageBO.addNewClassRoom(classRoom);

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Class Added!").show();
                    txtClassID.clear();
                    txtClassName.clear();
                    cbSection.getSelectionModel().select(null);
                    txtClassDetails.clear();


                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);


            }

        }

    public void sectionOnAction(ActionEvent actionEvent) {
    }
}
