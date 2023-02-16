package lk.ijse.School.dao.custom.impl;

import lk.ijse.School.dao.CrudDAO;
import lk.ijse.School.dao.custom.TeacherDAO;
import lk.ijse.School.entity.Student;
import lk.ijse.School.entity.Teacher;
import lk.ijse.School.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public boolean add(Teacher t1) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO teachers VALUES (?,?,?,?,?,?)",
                t1.getTeacherID(), t1.getTeacherName(), t1.getAddress(), t1.getGender(), t1.getReligion(), t1.getContact());

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM teachers WHERE TeacherID=?",s);
    }

    @Override
    public boolean update(Teacher teacher) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE teachers SET TeacherName=? ,TeacherAddress=?, Gender=?, Religion=?, Contact=? WHERE TeacherID=?",
                teacher.getTeacherName(), teacher.getAddress(), teacher.getGender(),teacher.getReligion(),teacher.getContact(),teacher.getTeacherID());
    }

    @Override
    public Teacher search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM teachers WHERE TeacherID=?",s);
        rs.next();
        return new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(5), rs.getString(6));
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM teachers");
        ArrayList<Teacher> getAllTeachers=new ArrayList<>();

        while (rs.next()){
            getAllTeachers.add(new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6)));
        }
        return getAllTeachers;
    }
}
