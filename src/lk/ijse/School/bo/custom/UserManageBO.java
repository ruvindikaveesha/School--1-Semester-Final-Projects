package lk.ijse.School.bo.custom;

import lk.ijse.School.bo.SuperBO;
import lk.ijse.School.entity.NewUser;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserManageBO extends SuperBO {
    boolean add(NewUser newUser) throws SQLException, ClassNotFoundException;
    boolean delete(String s) throws SQLException, ClassNotFoundException;
    boolean update(NewUser newUser) throws SQLException, ClassNotFoundException;
    NewUser search(String s) throws SQLException, ClassNotFoundException;
    ArrayList<NewUser> getAll() throws SQLException, ClassNotFoundException;

}
