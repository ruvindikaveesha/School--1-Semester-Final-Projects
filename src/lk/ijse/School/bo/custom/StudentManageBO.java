package lk.ijse.School.bo.custom;

import lk.ijse.School.bo.SuperBO;
import lk.ijse.School.dto.StudentDTO;
import lk.ijse.School.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;


public interface StudentManageBO extends SuperBO {
    boolean addNewStudent(StudentDTO dto)throws SQLException, ClassNotFoundException ;
    boolean updateStudent(StudentDTO dto)throws SQLException, ClassNotFoundException ;
    boolean deleteStudent(String s) throws SQLException, ClassNotFoundException;
    StudentDTO searchStudent(String s) throws SQLException, ClassNotFoundException;
    ArrayList<Student> getAll() throws SQLException, ClassNotFoundException;

}
