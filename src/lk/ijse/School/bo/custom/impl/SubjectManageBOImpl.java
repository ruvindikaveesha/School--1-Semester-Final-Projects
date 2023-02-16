package lk.ijse.School.bo.custom.impl;

import lk.ijse.School.bo.custom.SubjectManageBO;
import lk.ijse.School.dao.DAOFactory;
import lk.ijse.School.dao.custom.SubjectDAO;
import lk.ijse.School.dto.SubjectDTO;
import lk.ijse.School.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SubjectManageBOImpl implements SubjectManageBO {

    private final SubjectDAO subjectDAO= (SubjectDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);

    @Override
    public boolean addNewSubject(SubjectDTO dto) throws SQLException, ClassNotFoundException {
        return subjectDAO.add(new Subject(dto.getSubjectID(), dto.getSubjectName(), dto.getMeadium()));
    }

    @Override
    public boolean updateSubject(SubjectDTO dto) throws SQLException, ClassNotFoundException {
        return subjectDAO.update(new Subject(dto.getSubjectID(), dto.getSubjectName(), dto.getMeadium()));
    }

    @Override
    public boolean deleteSubject(String s) throws SQLException, ClassNotFoundException {
        return subjectDAO.delete(s);
    }

    @Override
    public SubjectDTO searchSubject(String s) throws SQLException, ClassNotFoundException {
        Subject subject = subjectDAO.search(s);

        return new SubjectDTO(subject.getSubjectID(), subject.getSubjectName(), subject.getMeadium());
    }

    @Override
    public ArrayList<Subject> getAll() throws SQLException, ClassNotFoundException {
        return subjectDAO.getAll();
    }

    @Override
    public List<String> getSubjectId() throws SQLException, ClassNotFoundException {
        return subjectDAO.getSubjectId();
    }
}
