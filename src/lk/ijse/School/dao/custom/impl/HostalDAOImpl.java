package lk.ijse.School.dao.custom.impl;

import lk.ijse.School.dao.custom.HostalDAO;
import lk.ijse.School.entity.Hostal;
import lk.ijse.School.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HostalDAOImpl implements HostalDAO {
    @Override
    public boolean add(Hostal hostal) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO hostal VALUES (?, ?, ?, ?, ?)",hostal.getHostalID(), hostal.getHostalName(),hostal.getHostalType(),hostal.getNoOfRoom(), hostal.getHostalContact());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM hostal WHERE HostalID=?",s);
    }

    @Override
    public boolean update(Hostal hostal) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE hostal SET HostalName=? , HostalType=? , NoOFRoom=?, HostalContact=? WHERE HostalID=?",
                hostal.getHostalName(), hostal.getHostalType(), hostal.getNoOfRoom(), hostal.getHostalContact(), hostal.getHostalID());
    }

    @Override
    public Hostal search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * FROM hostal WHERE HostalID=?",s);
        rst.next();
        return new Hostal( rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5)
                );
    }

    @Override
    public ArrayList<Hostal> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * FROM hostal");
        ArrayList<Hostal> allHostals=new ArrayList<>();
        while (rst.next()){
            allHostals.add(new Hostal( rst.getString(1), rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
                   ));
        }
        return allHostals;
    }
}
