package lk.ijse.School.bo.custom.impl;

import lk.ijse.School.bo.custom.StudentManageBO;
import lk.ijse.School.dao.DAOFactory;
import lk.ijse.School.dao.custom.StudentDAO;
import lk.ijse.School.dto.StudentDTO;
import lk.ijse.School.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;


public class StudentManageBOImpl implements StudentManageBO {

    private final StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean addNewStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.add(new Student(dto.getStudentIndexNumber(), dto.getStudentFirstName(), dto.getStudentLastName(), dto.getGender(), dto.getReligion(), dto.getMotherFirstName(), dto.getBirthDay(), dto.getMotherLastName(), dto.getFatherFirstName(), dto.getFatherLastName(), dto.getFatherOccupation(), dto.getStudentAddress(), dto.getContact()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(dto.getStudentIndexNumber(), dto.getStudentFirstName(), dto.getStudentLastName(), dto.getGender(), dto.getReligion(), dto.getMotherFirstName(), dto.getBirthDay(), dto.getMotherLastName(), dto.getFatherFirstName(), dto.getFatherLastName(), dto.getFatherOccupation(), dto.getStudentAddress(), dto.getContact()));
    }

    @Override
    public boolean deleteStudent(String s) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(s);
    }

    @Override
    public StudentDTO searchStudent(String s) throws SQLException, ClassNotFoundException {
        Student student=studentDAO.search(s);
        return new StudentDTO(student.getStudentIndexNumber(), student.getStudentFirstName(), student.getStudentLastName(), student.getGender(), student.getReligion(), student.getMotherFirstName(), student.getBirthDay(), student.getMotherLastName(), student.getFatherFirstName(), student.getFatherLastName(), student.getFatherOccupation(), student.getStudentAddress(), student.getContact());
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        return studentDAO.getAll();
    }
}
