package lk.ijse.School.bo.custom;

import lk.ijse.School.bo.SuperBO;
import lk.ijse.School.dto.ClassRoomDTO;
import lk.ijse.School.entity.ClassRoom;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ClassRoomManageBO extends SuperBO {
    boolean addNewClassRoom(ClassRoomDTO dto)throws SQLException, ClassNotFoundException ;
    boolean updateClassRoom(ClassRoomDTO dto)throws SQLException, ClassNotFoundException ;
    boolean deleteClassRoom(String s) throws SQLException, ClassNotFoundException;
    ClassRoomDTO searchClassRoom(String s) throws SQLException, ClassNotFoundException;
    ArrayList<ClassRoom> getAll() throws SQLException, ClassNotFoundException;
}
