package lk.ijse.School.dao.custom.impl;

import lk.ijse.School.dao.custom.ClassRoomDAO;
import lk.ijse.School.db.DBConnection;
import lk.ijse.School.entity.ClassRoom;
import lk.ijse.School.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ClassRoomDAOImpl implements ClassRoomDAO {
    @Override
    public boolean add(ClassRoom classRoom) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO class VALUES (?, ?, ?)");

        pstm.setString(1, classRoom.getClssID());
        pstm.setString(2, classRoom.getClassName());
        pstm.setString(3, classRoom.getSectionID().toString());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From class where ClassID='" + id + "'") > 0;
    }

    @Override
    public boolean update(ClassRoom classRoom) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Class SET  ClassName=? , SectionID=? WHERE ClassID=?",
                classRoom.getClassName(), classRoom.getSectionID(),  classRoom.getClssID());
    }

    @Override
    public ClassRoom search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * FROM Class WHERE ClassID=?",s);
        rst.next();
        return new ClassRoom(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3)
        );

    }

    @Override
    public ArrayList<ClassRoom> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * FROM class");
        ArrayList<ClassRoom> allClassRoom=new ArrayList<>();
        while (rst.next()){
            allClassRoom.add(new ClassRoom(
                    rst.getString(1), rst.getString(2),
                    rst.getString(3)
               ));
        }
        return allClassRoom;
    }
}
