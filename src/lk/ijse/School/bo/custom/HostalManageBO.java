package lk.ijse.School.bo.custom;

import lk.ijse.School.bo.SuperBO;
import lk.ijse.School.dto.HostalDTO;
import lk.ijse.School.entity.Hostal;

import java.sql.SQLException;
import java.util.ArrayList;


public interface HostalManageBO extends SuperBO {
    boolean addNewHostal(HostalDTO dto)throws SQLException, ClassNotFoundException ;
    boolean updateHostal(HostalDTO dto)throws SQLException, ClassNotFoundException ;
    boolean deleteHostal(String s) throws SQLException, ClassNotFoundException;
    HostalDTO searchHostal(String s) throws SQLException, ClassNotFoundException;
    ArrayList<Hostal> getAll() throws SQLException, ClassNotFoundException;
}
