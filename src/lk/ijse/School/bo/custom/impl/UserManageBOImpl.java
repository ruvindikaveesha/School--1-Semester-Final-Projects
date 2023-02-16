package lk.ijse.School.bo.custom.impl;

import lk.ijse.School.bo.custom.UserManageBO;
import lk.ijse.School.dao.custom.UserDAO;
import lk.ijse.School.dao.custom.impl.UserDAOImpl;
import lk.ijse.School.entity.NewUser;

import java.sql.SQLException;
import java.util.ArrayList;


public class UserManageBOImpl implements UserManageBO {
    UserDAO usr = new UserDAOImpl();
    @Override
    public boolean add(NewUser newUser) throws SQLException, ClassNotFoundException {
        return usr.add(newUser);
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return usr.delete(s);
    }

    @Override
    public boolean update(NewUser newUser) throws SQLException, ClassNotFoundException {
        return usr.update(newUser);
    }

    @Override
    public NewUser search(String s) throws SQLException, ClassNotFoundException {
        return usr.search(s);
    }

    @Override
    public ArrayList<NewUser> getAll() throws SQLException, ClassNotFoundException {
        return usr.getAll();
    }
}
