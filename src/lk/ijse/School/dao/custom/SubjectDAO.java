package lk.ijse.School.dao.custom;

import lk.ijse.School.dao.CrudDAO;
import lk.ijse.School.entity.Subject;

import java.sql.SQLException;
import java.util.List;


public interface SubjectDAO extends CrudDAO <Subject ,String > {
    List<String> getSubjectId() throws SQLException, ClassNotFoundException;
}
