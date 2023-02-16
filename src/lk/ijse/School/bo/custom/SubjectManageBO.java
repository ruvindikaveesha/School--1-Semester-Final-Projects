package lk.ijse.School.bo.custom;

import lk.ijse.School.bo.SuperBO;
import lk.ijse.School.dto.SubjectDTO;
import lk.ijse.School.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface SubjectManageBO extends SuperBO {

    boolean addNewSubject(SubjectDTO dto)throws SQLException, ClassNotFoundException ;
    boolean updateSubject(SubjectDTO dto)throws SQLException, ClassNotFoundException ;
    boolean deleteSubject(String s) throws SQLException, ClassNotFoundException;
    SubjectDTO searchSubject(String s) throws SQLException, ClassNotFoundException;
    ArrayList<Subject> getAll() throws SQLException, ClassNotFoundException;
    List<String> getSubjectId() throws SQLException, ClassNotFoundException;
}
