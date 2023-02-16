package lk.ijse.School.dao.custom.impl;

import lk.ijse.School.dao.custom.TeacherAndSubjectDAO;
import lk.ijse.School.entity.TeacherSubject;
import lk.ijse.School.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;


public class TeacherAndSubjectDAOImpl implements TeacherAndSubjectDAO {
    @Override
    public boolean add(TeacherSubject teacherSubject) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Teacher and subject` VALUES (?, ?)",
                teacherSubject.getTeacherId(),teacherSubject.getSubjectId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(TeacherSubject teacherSubject) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public TeacherSubject search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<TeacherSubject> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
