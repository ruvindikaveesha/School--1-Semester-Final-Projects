package lk.ijse.School.dao.custom.impl;

import lk.ijse.School.dao.CrudDAO;
import lk.ijse.School.dao.custom.UserDAO;
import lk.ijse.School.entity.NewUser;
import lk.ijse.School.entity.Teacher;
import lk.ijse.School.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(NewUser newUser) throws SQLException, ClassNotFoundException {
      return CrudUtil.execute("INSERT INTO user VALUES (?, ?, ?,?)",
                newUser.getUserNIC(), newUser.getUserName(), newUser.getPassword(), newUser.getUserType());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM user WHERE UserNIC=?",s);
    }

    @Override
    public boolean update(NewUser newUser) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE user SET UserName=? , UserPW=?, UserType=?  WHERE UserNIC=?",
                newUser.getUserName(), newUser.getPassword(), newUser.getUserType(), newUser.getUserNIC());
    }

    @Override
    public NewUser search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM user WHERE userName=?",s);
        if(rs.next()){
            return new NewUser(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3));
        }else {
            return null;
        }

    }

    @Override
    public ArrayList<NewUser> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("SELECT * FROM user");
        ArrayList<NewUser> getAllUser=new ArrayList<>();
        new NewUser("","","","");
        while (rs.next()){
            getAllUser.add(new NewUser(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3)));
        }
        return getAllUser;
    }
}
