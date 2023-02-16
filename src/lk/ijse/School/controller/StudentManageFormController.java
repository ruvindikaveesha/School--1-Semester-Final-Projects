package lk.ijse.School.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.School.bo.BOFactory;
import lk.ijse.School.bo.custom.StudentManageBO;
import lk.ijse.School.db.DBConnection;
import lk.ijse.School.dto.StudentDTO;
import lk.ijse.School.util.CrudUtil;
import lk.ijse.School.util.ValidationUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class StudentManageFormController {
    public AnchorPane StudentManageContext;
    public TableView tblStudent;
    public TableColumn colIndexNumber;
    public TableColumn colAddress;
    public TableColumn colGender;
    public TableColumn colName;
    public TableColumn colReligion;
    public TableColumn colbirthDay;
    public TableColumn colContact;

    public AnchorPane studentContext;

    public Label lblDate;
    public TextField txtStudentFirstName;
    public TextField txtStudentLastName;
    public ComboBox cmbBirthYear;
    public ComboBox cmbBirthMonth;
    public ComboBox cmbBirthDay;

    public ComboBox cmbClassID;
    public TextField txtMotherFirstName;

    public TextField txtAddress;
    public TextField txtContact;
    public JFXButton savebtn;
    public JFXButton btnSearch;
    public JFXButton btnUpdateStudent;
    public JFXButton btnDeleteStudent;
    public JFXTextField txtstudentIndex;
    public TextField txtReligion;
    public TextField txtGender;
    public JFXTextField txtStudentIndex;
    public TextField txtMotherLastName;
    public TextField txtFatherFirstName;
    public TextField txtFatherLastName;
    public TextField txtFatherOccupation;
    public DatePicker datePiker1;
    private JasperCompileManager JasperCompilerManager;
    private final StudentManageBO studentManageBO = (StudentManageBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENTMANAGE);

    LinkedHashMap<TextField, Pattern> allValidations = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(S00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/, ]{6,30}$");
    Pattern contactPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");


    public void initialize() throws SQLException, ClassNotFoundException {

        validateInit();
        setTableUsers();
    }
    public void setTableUsers() throws SQLException, ClassNotFoundException {
        colIndexNumber.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Student, String>("studentIndexNumber"));
        colName.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Student, String>("studentFirstName" ));
        colGender.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Student, String>("gender"));
        colReligion.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Student, String>("religion"));
        colAddress.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Student, String>("studentAddress"));
        colbirthDay.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Student, String>("birthDay"));
        colContact.setCellValueFactory(new PropertyValueFactory<lk.ijse.School.entity.Student, String>("contact"));

        try {
            ObservableList<lk.ijse.School.entity.Student> students = FXCollections.observableArrayList(studentManageBO.getAll());
            tblStudent.setItems(students);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void StudentRejisterOnAction(ActionEvent actionEvent)throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/StudentRejisterForm.fxml"));
        studentContext.getChildren().clear();
        studentContext.getChildren().add(parent);
    }


    public void StudentDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/StudentDetailsForm.fxml"));
        studentContext.getChildren().clear();
        studentContext.getChildren().add(parent);
    }


    public void textKeyReleased (KeyEvent keyEvent) {
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
    private void validateInit() {
        savebtn.setDisable(true);
        allValidations.put(txtStudentFirstName, namePattern);
        allValidations.put(txtStudentLastName, namePattern);
        allValidations.put(txtMotherFirstName, namePattern);
        allValidations.put(txtAddress, addressPattern);
        allValidations.put(txtContact, contactPattern);
    }

    public void studentSaveOnAction(ActionEvent actionEvent)   throws RuntimeException {

        String studentIndexnumber = txtStudentIndex.getText();
        String studentFirstname = txtStudentFirstName.getText();
        String studentLastname = txtStudentLastName.getText();
        String gender =  txtGender.getText();
        String religion =  txtReligion.getText();
        String birthDay = String.valueOf(datePiker1.getValue());//cmbBirthYear.getValue() + "-" + cmbBirthMonth.getValue() + "-" + cmbBirthDay.getValue();
        String motherFirstname = txtMotherFirstName.getText();
        String motherLastname = txtMotherLastName.getText();
        String fatherFirstname = txtFatherFirstName.getText();
        String fatherLastname = txtFatherLastName.getText();
        String fartherOccupation = txtFatherOccupation.getText();

        String studentAddress = txtAddress.getText();
        String contact = txtContact.getText();


        StudentDTO student = new StudentDTO(studentIndexnumber, studentFirstname, studentLastname, gender, religion,motherFirstname, birthDay,   motherLastname, fatherFirstname,fatherLastname, fartherOccupation, studentAddress, contact);

        try {
            boolean isAdded = studentManageBO.addNewStudent(student);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Added!").show();
                 setTableUsers();
                txtStudentFirstName.clear();txtStudentLastName.clear();datePiker1.setValue(null);txtGender.clear();txtReligion.clear();txtMotherLastName.clear();txtMotherFirstName.clear();txtFatherFirstName.clear();txtFatherLastName.clear();txtFatherOccupation.clear();txtAddress.clear();txtContact.clear();cmbClassID.getSelectionModel().clearSelection();

            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void studentSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    private void search() throws ClassNotFoundException, SQLException {

        ResultSet result = CrudUtil.execute("SELECT * FROM student WHERE StudentIndexNumber=?",txtStudentIndex.getText());
        if (result.next()) {
            txtStudentFirstName.setText(result.getString(2));
            txtStudentLastName.setText(result.getString(3));
            txtGender.setText(result.getString(4));
            txtReligion.setText(result.getString(5));
            datePiker1.setValue(LocalDate.parse(result.getString(6)));
            //cmbBirthYear.setValue(result.getString(6));
            txtMotherFirstName.setText(result.getString(7));
            txtMotherLastName.setText(result.getString(8));
            txtFatherFirstName.setText(result.getString(9));
            txtFatherLastName.setText(result.getString(10));
            txtFatherOccupation.setText(result.getString(11));
            txtAddress.setText(result.getString(12));
            txtContact.setText(result.getString(13));
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }


    public void studentUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String StudentIndexnumber = txtStudentIndex.getText();
        String StudentFirstname = txtStudentFirstName.getText();
        String StudentLastname = txtStudentLastName.getText();
        String Gender =  txtGender.getText();
        String Religion =  txtReligion.getText();
        String BirthDay = String.valueOf(datePiker1.getValue());//cmbBirthYear.getValue() + "-" + cmbBirthMonth.getValue() + "-" + cmbBirthDay.getValue();
        String MotherFirstname = txtMotherFirstName.getText();

        String MotherLastname = txtMotherLastName.getText();
        String FatherFirstname = txtFatherFirstName.getText();
        String FatherLastname = txtFatherLastName.getText();
        String FartherOccupation = txtFatherOccupation.getText();

        String StudentAddress = txtAddress.getText();
        String Contact = txtContact.getText();

        StudentDTO student=new StudentDTO(StudentIndexnumber, StudentFirstname, StudentLastname, Gender, Religion, BirthDay, MotherFirstname, MotherLastname, FatherFirstname, FatherLastname, FartherOccupation, StudentAddress, Contact);
        boolean isUpdated= studentManageBO.updateStudent(student);
        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            setTableUsers();

        }
    }

    public void studentDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = studentManageBO.deleteStudent(txtStudentIndex.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                setTableUsers();
                txtStudentFirstName.clear();txtStudentLastName.clear();datePiker1.setValue(null);txtGender.clear();txtReligion.clear();txtMotherLastName.clear();txtMotherFirstName.clear();txtFatherFirstName.clear();txtFatherLastName.clear();txtFatherOccupation.clear();txtAddress.clear();txtContact.clear();cmbClassID.getSelectionModel().clearSelection();

            } else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Fail").show();
            }
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }

    public void printStudentDetailsOnAction(ActionEvent actionEvent)throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasdi= JRXmlLoader.load("C:\\Users\\ASUS\\Desktop\\School\\src\\lk\\ijse\\School\\report\\StudentReport2.jrxml");;
        String sql="select * from student where StudentIndexNumber = '"+txtStudentIndex.getText()+"'";

        JRDesignQuery newQuery=new JRDesignQuery();
        newQuery.setText(sql);
        jasdi.setQuery(newQuery);

        JasperReport js= JasperCompileManager.compileReport(jasdi);
        JasperPrint jp= JasperFillManager.fillReport(js,null, DBConnection.getInstance().getConnection());

        //String path =;
        //JasperExportManager.exportReportToPdfFil(jp,path);

        JasperViewer viewer=new JasperViewer(jp,false);
        viewer.show();
        //return path;/

    }
}
