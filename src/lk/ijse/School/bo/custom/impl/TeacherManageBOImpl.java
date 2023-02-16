package lk.ijse.School.bo.custom.impl;

import lk.ijse.School.bo.custom.TeacherManageBO;
import lk.ijse.School.dao.DAOFactory;
import lk.ijse.School.dao.custom.TeacherAndSubjectDAO;
import lk.ijse.School.dao.custom.TeacherDAO;
import lk.ijse.School.db.DBConnection;
import lk.ijse.School.dto.TeacherDTO;
import lk.ijse.School.entity.Teacher;
import lk.ijse.School.entity.TeacherSubject;
import lk.ijse.School.view.tm.TeacherSubjectTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class TeacherManageBOImpl implements TeacherManageBO {

    private final TeacherDAO teacherDAO= (TeacherDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TEACHER);
    private final TeacherAndSubjectDAO teacherAndSubjectDAO= (TeacherAndSubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TEACHERSUBJECT);

    @Override
    public boolean addNewTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException {
        Connection con=null;
        try {
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            if(teacherDAO.add(new Teacher(dto.getTeacherID(),dto.getTeacherName(),dto.getAddress(),dto.getGender(), dto.getReligion(), dto.getContact()))){
                for (TeacherSubjectTM temp:dto.getDetails()) {
                    TeacherSubject teacherSubject=new TeacherSubject(dto.getTeacherID(),temp.getSubjectID());
                    if(teacherAndSubjectDAO.add(teacherSubject)){
                        con.commit();
                        return true;
                    }else {
                        con.rollback();
                        return false;
                    }
                }
            }else{
                con.rollback();
                return false;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateTeacher(TeacherDTO dto) throws SQLException, ClassNotFoundException {
        return teacherDAO.update(new Teacher(dto.getTeacherID(), dto.getTeacherName(), dto.getAddress(), dto.getGender(), dto.getReligion(), dto.getContact()));
    }

    @Override
    public boolean deleteTeacher(String s) throws SQLException, ClassNotFoundException {
        return teacherDAO.delete(s);
    }

    @Override
    public TeacherDTO searchTeacher(String s) throws SQLException, ClassNotFoundException {
        Teacher teacher = teacherDAO.search(s);
        return new TeacherDTO(teacher.getTeacherID(), teacher.getTeacherName(),teacher.getAddress(),teacher.getGender(),teacher.getReligion(),teacher.getContact());
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException, ClassNotFoundException {
        return teacherDAO.getAll();
    }
}
