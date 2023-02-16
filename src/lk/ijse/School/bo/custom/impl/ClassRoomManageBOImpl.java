package lk.ijse.School.bo.custom.impl;

import lk.ijse.School.bo.custom.ClassRoomManageBO;
import lk.ijse.School.dao.DAOFactory;
import lk.ijse.School.dao.custom.ClassRoomDAO;
import lk.ijse.School.dto.ClassRoomDTO;
import lk.ijse.School.entity.ClassRoom;

import java.sql.SQLException;
import java.util.ArrayList;


public class ClassRoomManageBOImpl implements ClassRoomManageBO {

    private final ClassRoomDAO classRoomDAO= (ClassRoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CLASSROOM);

    @Override
    public boolean addNewClassRoom(ClassRoomDTO dto) throws SQLException, ClassNotFoundException {
        return classRoomDAO.add(new ClassRoom(dto.getClssID(), dto.getClassName(), dto.getSectionID()));
    }

    @Override
    public boolean updateClassRoom(ClassRoomDTO dto) throws SQLException, ClassNotFoundException {
        return classRoomDAO.update(new ClassRoom(dto.getClssID(), dto.getClassName(), dto.getSectionID()));
    }

    @Override
    public boolean deleteClassRoom(String s) throws SQLException, ClassNotFoundException {
        return classRoomDAO.delete(s);
    }

    @Override
    public ClassRoomDTO searchClassRoom(String s) throws SQLException, ClassNotFoundException {
        ClassRoom classRoom = classRoomDAO.search(s);

        return new ClassRoomDTO(classRoom.getClssID(), classRoom.getClassName(), classRoom.getSectionID());
    }

    @Override
    public ArrayList<ClassRoom> getAll() throws SQLException, ClassNotFoundException {
        return classRoomDAO.getAll();
    }
}
