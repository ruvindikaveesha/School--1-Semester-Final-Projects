package lk.ijse.School.dao.custom.impl;

import lk.ijse.School.dao.custom.StudentDAO;
import lk.ijse.School.entity.Student;
import lk.ijse.School.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO student VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                student.getStudentIndexNumber(),
                student.getStudentFirstName(),
                student.getStudentLastName(),
                student.getGender(),
                student.getReligion(),
                student.getBirthDay(),
                student.getMotherFirstName(),
                student.getMotherLastName(),
                student.getFatherFirstName(),
                student.getFatherLastName(),
                student.getFatherOccupation(),
                student.getStudentAddress(),
                student.getContact()

                );
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM student WHERE StudentIndexNUmber=?",s);
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Student SET StudentFirstname=? , StudentLastName=? , Gender=?, Religion=?, BirthDay=?, MotherFirstName=?, MotherLastName=?,FatherFirstName= ?, FatherLastName=?, FartherOccupation=?, StudentAddress=?, Contact=? WHERE StudentIndexNUmber=?",
                student.getStudentFirstName(), student.getStudentLastName(), student.getGender(), student.getReligion(), student.getBirthDay(), student.getMotherFirstName(), student.getMotherLastName(), student.getFatherFirstName(), student.getFatherLastName(), student.getFatherOccupation(), student.getStudentAddress(), student.getContact(), student.getStudentIndexNumber());
    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM Student WHERE StudentIndexNUmber=?",s);
        rs.next();
        return new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(5), rs.getString(7), rs.getString(6), rs.getString(8), rs.getString(9),
                rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));

    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM student");
        ArrayList<Student> getAllStudent=new ArrayList<>();

        while (rs.next()){
            getAllStudent.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(7), rs.getString(6), rs.getString(8), rs.getString(9),
                    rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
        }
        return getAllStudent;
    }
}
