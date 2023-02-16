package lk.ijse.School.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.School.bo.BOFactory;
import lk.ijse.School.bo.custom.SubjectManageBO;
import lk.ijse.School.bo.custom.TeacherManageBO;
import lk.ijse.School.db.DBConnection;
import lk.ijse.School.dto.TeacherDTO;
import lk.ijse.School.util.ValidationUtil;
import lk.ijse.School.view.tm.TeacherSubjectTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class TeacherManageFormController {

    public AnchorPane TeacherManagementContext;
    public TableView tblTeacher;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colGender;
    public TableColumn colReligion;
    public TableColumn colContact;
    public TextField txtTeacherFirstName;
    public TextField txtTeacherLastName;
    public TextField txtAddress;
    public TextField txtContact;
    public JFXTextField txtTeacherID;
    public TextField txtGender;
    public TextField txtReligion;
    public TableView tblTeacherSubject;
    public TableColumn colSubject;
    public JFXComboBox cmbSubjectID;
    public Label lblSubjectName;
    public Label lblAlreadyAdded;
    public JFXButton savebtn;
    public JFXButton btnSearch;
    public JFXButton btnUpdateStudent;
    public JFXButton btnDeleteStudent;
    public AnchorPane context;

    private final TeacherManageBO teacherManageBO = (TeacherManageBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.TEACHERMANAGE);
    private final SubjectManageBO subjectManageBO = (SubjectManageBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.SUBJECTMANAGE);

    LinkedHashMap<TextField, Pattern> allValidations = new LinkedHashMap<>();

    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/, ]{6,30}$");
    Pattern genderPattern = Pattern.compile("^[A-z0-9/, ]{6,10}$");
    Pattern religionPattern = Pattern.compile("^[A-z0-9/, ]{6,10}$");
    Pattern contactPattern = Pattern.compile("^(0)[0-9][-]?[0-9]{8}$");

    public void initialize(){
        savebtn.setDisable(false);
//        savebtn.setDisable(true);
        colSubject.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Subject, String>("subjectName"));

        setTableUsers();
       // validateUnit();

        try {
            loadSubjectIds();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbSubjectID.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        getSubjectName((String)newValue);
                        lblAlreadyAdded.setVisible(false);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
    }

    ObservableList<TeacherSubjectTM>obList= FXCollections.observableArrayList();
    public void addSubjectForTeacher(ActionEvent actionEvent){
        TeacherSubjectTM subjectTM=new TeacherSubjectTM((String) cmbSubjectID.getValue(),lblSubjectName.getText());
        for (TeacherSubjectTM temp:obList) {
            if(cmbSubjectID.getValue().equals(temp.getSubjectID())) {
                lblAlreadyAdded.setVisible(true);
                return;
            }
        }

        obList.add(subjectTM);
        tblTeacherSubject.setItems(obList);

    }

    public void teacherSaveOnAction(ActionEvent actionEvent) throws RuntimeException, SQLException, ClassNotFoundException {

        if(obList.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please Select subject").show();
            return;
        }

        ArrayList<TeacherSubjectTM> details=new ArrayList<>();

        for(TeacherSubjectTM temp:obList){
            details.add(new TeacherSubjectTM(temp.getSubjectID(),temp.getSubjectName()));
        }

//        String teacherID = txtTeacherID.getText();
//        String name=txtTeacherFirstName.getText()+" "+txtTeacherLastName.getText();
//        String address =  txtAddress.getText();
//        String gender =  txtGender.getText();
//
//        String religion =  txtReligion.getText();
//        String contact = txtContact.getText();



        TeacherDTO t1=new TeacherDTO(txtTeacherID.getText(),txtTeacherFirstName.getText(),txtAddress.getText(),(String) txtGender.getText(),
                (String)txtReligion.getText(),txtContact.getText(),details);
        lk.ijse.School.entity.Teacher teacher1 = new lk.ijse.School.entity.Teacher("","","","","","");



        boolean isAdded = teacherManageBO.addNewTeacher(t1);
        if(isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Teacher Added!").show();
            setTableUsers();
            txtTeacherID.clear();txtTeacherFirstName.clear();txtAddress.clear();txtGender.clear();txtReligion.clear();txtContact.clear();

        }


    }

    private void setTableUsers() {
        colID.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Teacher, String>("teacherID"));
        colName.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Teacher, String>("teacherName" ));
        colAddress.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Teacher, String>("address"));
        colGender.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Teacher, String>("gender" ));
        colReligion.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Teacher, String>("religion"));
        colContact.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Teacher, String>("contact"));

        try {
            ObservableList<lk.ijse.School.entity.Teacher> teacher= FXCollections.observableArrayList(teacherManageBO.getAll());
            tblTeacher.setItems(teacher);
            for(lk.ijse.School.entity.Teacher ob : teacher){
                System.out.println(ob);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void validateUnit(){
//        savebtn.setDisable(true);
        allValidations.put(txtTeacherFirstName,namePattern);
        allValidations.put(txtTeacherLastName,namePattern);
        allValidations.put(txtAddress,addressPattern);
        allValidations.put(txtContact,contactPattern);
        allValidations.put(txtGender,genderPattern);
        allValidations.put(txtReligion,religionPattern);
    }




    public void teacherSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void search() throws ClassNotFoundException, SQLException {

        TeacherDTO teacherDTO = teacherManageBO.searchTeacher(txtTeacherID.getText());
        if (teacherDTO!=null) {
            txtTeacherFirstName.setText(teacherDTO.getTeacherName());
            txtAddress.setText(teacherDTO.getAddress());
            txtGender.setText(teacherDTO.getGender());
            txtReligion.setText(teacherDTO.getReligion());
            txtContact.setText(teacherDTO.getContact());
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }



    public void teacherUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String TeacherID = txtTeacherID.getText();
        String teacherFirstname = txtTeacherFirstName.getText();
        String Address = txtAddress.getText();
        String Gender =  txtGender.getText();
        String Religion =  txtReligion.getText();
        String Contact = txtContact.getText();

        TeacherDTO teacher=new TeacherDTO(TeacherID,teacherFirstname,Address, Gender, Religion, Contact);
        boolean isUpdated= teacherManageBO.updateTeacher(teacher);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableUsers();

        }
    }



    public void textField_key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(allValidations,savebtn);

        if (keyEvent.getCode() == KeyCode.ENTER) {

            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    public void teacherDeleteOnAction(ActionEvent actionEvent)
            throws SQLException, ClassNotFoundException {
        System.out.println(txtTeacherID.getText());
    try {
            boolean isDeleted = teacherManageBO.deleteTeacher(txtTeacherID.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                setTableUsers();
                txtTeacherFirstName.clear();
                txtAddress.clear();
                txtGender.clear();
                txtReligion.clear();
                txtContact.clear();

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Delete Fail").show();
            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

    public void loadSubjectIds () throws SQLException, ClassNotFoundException {
        List<String> subIds= subjectManageBO.getSubjectId();
        cmbSubjectID.getItems().addAll(subIds);
    }

    public void getSubjectName(String  id) throws SQLException, ClassNotFoundException {

        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM subject " +
                        "WHERE SubjectID=?");



        pstm.setString(1,id);
        ResultSet rst=pstm.executeQuery();

        if(rst.next()){
            lblSubjectName.setText(rst.getString(2));
        }
    }

    public void printTacherDetailsOnAction(ActionEvent actionEvent) {
    }
}
