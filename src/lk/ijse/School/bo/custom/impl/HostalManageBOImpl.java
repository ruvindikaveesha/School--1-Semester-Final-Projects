package lk.ijse.School.bo.custom.impl;

import lk.ijse.School.bo.custom.HostalManageBO;
import lk.ijse.School.dao.DAOFactory;
import lk.ijse.School.dao.custom.HostalDAO;
import lk.ijse.School.dto.HostalDTO;
import lk.ijse.School.entity.Hostal;

import java.sql.SQLException;
import java.util.ArrayList;


public class HostalManageBOImpl implements HostalManageBO {

    private final HostalDAO hostalDAO= (HostalDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HOSTAL);

    @Override
    public boolean addNewHostal(HostalDTO dto) throws SQLException, ClassNotFoundException {
        return hostalDAO.add(new Hostal(dto.hostalID, dto.hostalName, dto.hostalType, dto.noOfRoom, dto.getHostalContact()));
    }

    @Override
    public boolean updateHostal(HostalDTO dto) throws SQLException, ClassNotFoundException {
        return hostalDAO.update(new Hostal(dto.hostalID, dto.hostalName, dto.hostalType, dto.noOfRoom, dto.getHostalContact()));
    }

    @Override
    public boolean deleteHostal(String s) throws SQLException, ClassNotFoundException {
        return hostalDAO.delete(s);
    }

    @Override
    public HostalDTO searchHostal(String s) throws SQLException, ClassNotFoundException {
        Hostal hostal=hostalDAO.search(s);
        return new HostalDTO(hostal.hostalID, hostal.hostalName, hostal.hostalType, hostal.noOfRoom, hostal.getHostalContact());
    }

    @Override
    public ArrayList<Hostal> getAll() throws SQLException, ClassNotFoundException {
        return hostalDAO.getAll();
    }
}
