package lk.ijse.School.bo.custom;

import lk.ijse.School.bo.SuperBO;
import lk.ijse.School.dto.TeacherDTO;
import lk.ijse.School.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;


public interface TeacherManageBO extends SuperBO {
    boolean addNewTeacher(TeacherDTO dto)throws SQLException, ClassNotFoundException ;
    boolean updateTeacher(TeacherDTO dto)throws SQLException, ClassNotFoundException ;
    boolean deleteTeacher(String s) throws SQLException, ClassNotFoundException;
    TeacherDTO searchTeacher(String s) throws SQLException, ClassNotFoundException;
    ArrayList<Teacher> getAll() throws SQLException, ClassNotFoundException;
}
