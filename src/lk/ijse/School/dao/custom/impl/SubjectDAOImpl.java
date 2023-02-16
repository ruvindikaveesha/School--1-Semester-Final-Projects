package lk.ijse.School.dao.custom.impl;

import lk.ijse.School.dao.custom.SubjectDAO;
import lk.ijse.School.entity.Subject;
import lk.ijse.School.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    @Override
    public boolean add(Subject subject) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO subject VALUES (?, ?, ?)",subject.getSubjectID(),subject.getSubjectName(),
                subject.getMeadium());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM subject WHERE SubjectID=?",s);
    }

    @Override
    public boolean update(Subject subject) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Subject SET  SubjectName=? , SubjectMeadium=? WHERE SubjectID = ?",
                subject.getSubjectName(), subject.getMeadium(),subject.getSubjectID());
    }

    @Override
    public Subject search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * FROM Subject WHERE SubjectID=?",s);
        rst.next();
        return new Subject(rst.getString(1),rst.getString(2),rst.getString(3));
    }

    @Override
    public ArrayList<Subject> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * FROM Subject");
        ArrayList<Subject> getAllSubject=new ArrayList<>();

        while (rst.next()){
            getAllSubject.add(new Subject(rst.getString(1), rst.getString(2),rst.getString(3)));
        }
        return getAllSubject;
    }

    @Override
    public List<String> getSubjectId() throws SQLException, ClassNotFoundException {
        List <String> ids=new ArrayList<>();
        ResultSet rs = CrudUtil.execute("SELECT * from subject");

        while (rs.next()){
            ids.add(rs.getString(1));
        }
        return ids;
    }
}
